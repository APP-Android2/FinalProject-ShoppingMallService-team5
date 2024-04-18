package kr.co.lion.mungnolza.ui.intro.activity


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.firestore
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityLoginBinding
import kr.co.lion.mungnolza.model.User
import kr.co.lion.mungnolza.ui.main.MainActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()



        val currentUser = auth.currentUser

        if (currentUser != null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val signInButton = findViewById<Button>(R.id.button_login_google)
        signInButton.setOnClickListener {
            signIn()
        }



        // 현재 로그인한 유저가 있을 경우, MainActivity로 이동
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        kakaoLoginFunction()


    }
    private fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(this, "구글 로그인 실패: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "${user?.displayName}로 로그인 하시겠습니까?", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "인증 실패", Toast.LENGTH_SHORT).show()
                }
            }
    }


    fun kakaoLoginFunction(){
        binding.apply {
            buttonLoginKakao.setOnClickListener {
                kakaoLogin()
            }
        }
    }

    fun kakaoLogin() {

        val TAG = "test1234"

        // Kakao SDK 초기화
        KakaoSdk.init(this, "af569264fe25d2489534a85f816d1ca9")

        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                // 이 부분에는 로그인이 실패했을 때의 처리를 해주세요
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
                // android key hash 값을 출력하고 이를 등록해야 한다.
                Log.d("test1234", Utility.getKeyHash(this@LoginActivity))
            } else if (token != null) {
                // 이 부분에는 로그인에 성공했을 때의 처리를 해주세요
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")


                updateUIWithUser()

                // 로그인한 사용자 정보를 가져온다.
                // 이 때 accessToken 을 카카오 서버로 전달해야 해야하는데 알아서해준다.
                UserApiClient.instance.me { kakaoUser, error ->
                    if (error != null) {
                        Log.e(TAG, "사용자 정보를 가져오는데 실패하였습니다", error)
                    } else if (kakaoUser != null) {
                        Log.d(TAG, "회원번호 : ${kakaoUser.id}")
                        Log.d(TAG, "이메일 : ${kakaoUser.kakaoAccount?.email}")
                        Log.d(TAG, "닉네임 : ${kakaoUser.kakaoAccount?.profileNicknameNeedsAgreement}")
                        Log.d(TAG, "프로필사진 : ${kakaoUser.kakaoAccount?.profile?.thumbnailImageUrl}")

                        val user = User(
                            uniqueNumber = kakaoUser.id.toString(),
                            userAddress = "",
                            userAgeRange = "",
                            userEmail = kakaoUser.kakaoAccount?.email ?: "",
                            userGender= "",
                            userName= "",
                            userNickname = kakaoUser.kakaoAccount?.profile?.nickname ?: "",
                            userPhone= "",
                            userProfileImgPath = kakaoUser.kakaoAccount?.profile?.thumbnailImageUrl ?: "",
                        )

                        // Firebase에 사용자 정보 등록
                        registerUserToFirebase(user)
                    }
                }


            }
        }



        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {
            UserApiClient.instance.loginWithKakaoTalk(this@LoginActivity) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)


                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(
                        this@LoginActivity,
                        callback = callback
                    )
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    updateUIWithUser()
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this@LoginActivity, callback = callback)
        }
    }

    private fun updateUIWithUser() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    // Firebase에 사용자 정보를 등록하는 함수
    private fun registerUserToFirebase(user: User) {
        // Firebase Realtime Database에 접근하여 사용자 정보 저장
        val database = FirebaseDatabase.getInstance()

        val collectionReference = Firebase.firestore.collection("User")
        val usersRef = database.getReference("users")
        collectionReference.add(user)
    }
}

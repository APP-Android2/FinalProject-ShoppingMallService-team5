package kr.co.lion.mungnolza.ui.intro.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.databinding.ActivityLoginBinding
import kr.co.lion.mungnolza.ext.repeatOnStarted
import kr.co.lion.mungnolza.model.UserModel
import kr.co.lion.mungnolza.ui.intro.vm.LoginViewModel
import kr.co.lion.mungnolza.ui.intro.vm.LoginViewModelFactory
import kr.co.lion.mungnolza.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels { LoginViewModelFactory(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            lifecycleScope.launch {
                if (viewModel.checkFistFlag()) {
                    delay(1000)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    buttonLoginKakao.visibility = View.VISIBLE
                }
            }

            buttonLoginKakao.setOnClickListener {
                kakaoLogin()
            }
        }
    }

    private fun kakaoLogin() {

        //val keyHash = Utility.getKeyHash(this)
        val TAG = "KLogin"

        KakaoSdk.init(this, "a3f0344e66edd54dc79db6320b708c36")
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오 계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i(TAG, "카카오 계정으로 로그인 성공 ${token.accessToken}")
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

                    UserApiClient.instance.me { user, _ ->
                        if (user != null) {
                            repeatOnStarted {
                                val userId = user.id.toString()

                                if (viewModel.isExistUser(userId)) {
                                    viewModel.onLoginSuccess(userId) {
                                        if (it) {
                                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                            finish()
                                        }
                                    }
                                } else {
                                    val newUser = UserModel(
                                        uniqueNumber = user.id.toString(),
                                        userNickname = user.kakaoAccount?.name.toString(),
                                        userName = user.kakaoAccount?.name.toString(),
                                        userEmail = user.kakaoAccount?.email.toString(),
                                        userPhone = user.kakaoAccount?.phoneNumber.toString(),
                                        userAddress = "",
                                        userProfileImgPath = user.kakaoAccount?.profile?.profileImageUrl.toString(),
                                        userAgeRange = user.kakaoAccount?.ageRange.toString(),
                                        userGender = user.kakaoAccount?.gender.toString()
                                    )
                                    val intent = Intent(this@LoginActivity, JoinActivity::class.java)
                                    intent.putExtra("user", newUser)
                                    startActivity(intent)
                                }
                            }
                        }
                    }
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this@LoginActivity, callback = callback)
        }
    }
}
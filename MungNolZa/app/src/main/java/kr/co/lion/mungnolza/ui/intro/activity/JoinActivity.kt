package kr.co.lion.mungnolza.ui.intro.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kr.co.lion.mungnolza.databinding.ActivityJoinBinding
import kr.co.lion.mungnolza.model.UserModel
import kr.co.lion.mungnolza.ui.dialog.PositiveCustomDialog
import kr.co.lion.mungnolza.ui.intro.vm.JoinViewModel
import kr.co.lion.mungnolza.ui.intro.vm.JoinViewModelFactory
import kr.co.lion.mungnolza.ui.onboarding.OnBoardingActivity
import kr.co.lion.mungnolza.util.Tools

class JoinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJoinBinding
    private val viewModel: JoinViewModel by viewModels{ JoinViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent: UserModel? = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableArrayExtra("user", UserModel::class.java)?.let { it[0] as UserModel }
        } else {
            intent.getParcelableExtra("user")
        }

        val contracts = ActivityResultContracts.StartActivityForResult()
        val launcherForActivity = registerForActivityResult(contracts) { result ->
            val callback = result.data
            if (callback != null){
                if (result.resultCode == Tools.ADDR_RESULT_CODE){
                    val data = callback.getStringExtra("data")
                    binding.editTextAddr.setText(data)
                }
            }
        }

        with(binding){
            btnAddr.setOnClickListener {
                launcherForActivity.launch(Intent(this@JoinActivity, AddressActivity::class.java))
            }

            btnNext.setOnClickListener {
                val address = editTextAddr.text.toString()
                val detailAddress = editTextDetailAddr.text.toString()

                if (address.isEmpty() || detailAddress.isEmpty()){
                    val dialog = PositiveCustomDialog(
                        title = "주소를 입력해 볼까요?",
                        message = "주소를 입력해 주세요",
                        positiveButtonClick = { }
                    )
                    dialog.show(supportFragmentManager, "PositiveCustomDialog")
                }else{
                    val newUser = intent?.copy(
                        userAddress = "$address $detailAddress"
                    )

                    if (newUser != null) {
                        viewModel.userJoin(newUser)
                        viewModel.setUpDataStore(newUser.uniqueNumber)
                    }

                    startActivity(Intent(this@JoinActivity, OnBoardingActivity::class.java))
                    finish()
                }
            }
        }
    }
}
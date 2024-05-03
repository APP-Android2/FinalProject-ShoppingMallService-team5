package kr.co.lion.mungnolza.ui.intro.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import kotlinx.coroutines.delay
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityStartBinding
import kr.co.lion.mungnolza.ext.repeatOnStarted
import kr.co.lion.mungnolza.ui.dialog.RequestPermissionDialog
import kr.co.lion.mungnolza.ui.intro.vm.StartViewModel
import kr.co.lion.mungnolza.ui.intro.vm.StartViewModelFactory
import kr.co.lion.mungnolza.ui.main.MainActivity

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    private val viewModel: StartViewModel by viewModels { StartViewModelFactory(this) }
    private val dialog = RequestPermissionDialog(
        buttonClick = {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        binding = ActivityStartBinding.inflate(layoutInflater)

        repeatOnStarted {
            delay(450)
            if (viewModel.checkFistFlag()) {
                dialog.show(supportFragmentManager, "RequestPermissionDialog")
            } else {
                viewModel.onLoginSuccess {
                    if (it) {
                        startActivity(Intent(this@StartActivity, MainActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }
}
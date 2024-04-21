package kr.co.lion.mungnolza.ui.intro.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityStartBinding
import kr.co.lion.mungnolza.ui.dialog.RequestPermissionDialog
import kr.co.lion.mungnolza.ui.intro.vm.StartViewModel
import kr.co.lion.mungnolza.ui.intro.vm.StartViewModelFactory
import kr.co.lion.mungnolza.ui.main.MainActivity

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    private val viewModel: StartViewModel by viewModels{ StartViewModelFactory() }
    private val dialog = RequestPermissionDialog(
        buttonClick = {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        initView()
    }

    private fun initView() {
        binding = ActivityStartBinding.inflate(layoutInflater)

        lifecycleScope.launch {
            delay(2000)
            if (!viewModel.checkFistFlag()){
                dialog.show(supportFragmentManager, "RequestPermissionDialog")
            }else{
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.myPetData.collect {
                            val intent = Intent(this@StartActivity, MainActivity::class.java)
                            intent.putExtra("myPet", it.toTypedArray())
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }
    }
}
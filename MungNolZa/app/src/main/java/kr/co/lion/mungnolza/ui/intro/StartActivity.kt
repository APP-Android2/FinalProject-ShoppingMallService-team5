package kr.co.lion.mungnolza.ui.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        initView()
    }

    private fun initView(){
        lifecycleScope.launch {
            delay(2000)
            startActivity(Intent(this@StartActivity, IntroActivity::class.java))
        }
    }
}
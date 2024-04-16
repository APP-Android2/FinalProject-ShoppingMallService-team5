package kr.co.lion.mungnolza.ui.intro.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.ui.onboarding.OnBoardingActivity

class JoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        lifecycleScope.launch {
            delay(2000)
            startActivity(Intent(this@JoinActivity, OnBoardingActivity::class.java))
            finish()
        }
    }
}
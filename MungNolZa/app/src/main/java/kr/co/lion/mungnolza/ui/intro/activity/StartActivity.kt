package kr.co.lion.mungnolza.ui.intro.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.ui.intro.RequestPermissionDialog

class StartActivity : AppCompatActivity() {
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

    private fun initView(){
        lifecycleScope.launch {
            //delay(2000)
            dialog.show(supportFragmentManager, "RequestPermissionDialog")
        }
    }

}
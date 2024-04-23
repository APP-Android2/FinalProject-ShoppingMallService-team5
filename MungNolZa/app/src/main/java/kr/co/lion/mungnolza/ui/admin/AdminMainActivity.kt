package kr.co.lion.mungnolza.ui.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityAdminMainBinding

class AdminMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 여기에서 다른 액티비티를 시작
        val contentIntent = Intent(this, AdminContentActivity::class.java)
        startActivity(contentIntent)
        finish()
    }
}
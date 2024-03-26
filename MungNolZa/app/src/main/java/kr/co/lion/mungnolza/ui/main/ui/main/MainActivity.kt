package kr.co.lion.mungnolza.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityMainBinding
import kr.co.lion.mungnolza.ui.freeboard.BoardActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)

        binding.buttonTest2.setOnClickListener {
            val intent = Intent(this, BoardActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)

    }

}
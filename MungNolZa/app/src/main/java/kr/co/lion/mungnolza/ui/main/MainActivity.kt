package kr.co.lion.mungnolza.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.mungnolza.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}



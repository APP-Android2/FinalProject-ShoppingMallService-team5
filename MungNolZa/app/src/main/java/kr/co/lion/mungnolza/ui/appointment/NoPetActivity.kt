package kr.co.lion.mungnolza.ui.appointment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.mungnolza.databinding.ActivityNoPetBinding

class NoPetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoPetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoPetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView(){
        with(binding){
            toolbar.setNavigationOnClickListener { finish() }
        }
    }
}
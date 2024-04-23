package kr.co.lion.mungnolza.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityTestBinding
import kr.co.lion.mungnolza.ui.chat.ChatActivity
import kr.co.lion.mungnolza.ui.freeboard.BoardActivity

class TestActivity : AppCompatActivity() {

    lateinit var binding:ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }
}
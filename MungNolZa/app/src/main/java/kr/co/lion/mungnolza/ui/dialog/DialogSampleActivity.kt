package kr.co.lion.mungnolza.ui.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.co.lion.mungnolza.databinding.ActivityDialogSampleBinding
import kr.co.lion.mungnolza.ui.dialog.PositiveCustomDialog

class DialogSampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDialogSampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val customDialog = PositiveCustomDialog(
                title = "안녕",
                message = "나는 찬호야",
                positiveButtonClick = { Toast.makeText(this, "다이얼로그 종료", Toast.LENGTH_SHORT).show() }
            )
            customDialog.show(supportFragmentManager, "PositiveCustomDialog")
        }

        binding.button2.setOnClickListener {

        }
    }
}
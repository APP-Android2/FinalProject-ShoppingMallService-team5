package kr.co.lion.mungnolza.ui.intro.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityLoginBinding
import kr.co.lion.mungnolza.util.Tools.REQUEST_IMAGE_PERMISSIONS
import kr.co.lion.mungnolza.util.Tools.REQUEST_LOCATION_PERMISSIONS

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
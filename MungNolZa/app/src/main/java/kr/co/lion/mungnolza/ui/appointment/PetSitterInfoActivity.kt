package kr.co.lion.mungnolza.ui.appointment

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityPetSitterInfoBinding
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.model.PetSitterModel
import kr.co.lion.mungnolza.ui.appointment.vm.PetSitterInfoViewModel
import kr.co.lion.mungnolza.ui.appointment.vm.PetSitterInfoViewModelFactory

class PetSitterInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPetSitterInfoBinding
    private val viewModel: PetSitterInfoViewModel by viewModels { PetSitterInfoViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetSitterInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val petSitterIdx = intent.getStringExtra("petSitterIdx")
        if (petSitterIdx != null) {
            viewModel.getPetSitterInfo(petSitterIdx)
        }

        with(binding) {
            toolbar.setNavigationOnClickListener { finish() }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.petSitter.collect {
                        if (it != null) {
                            with(binding) {
                                Glide.with(this@PetSitterInfoActivity)
                                    .load(it.profileImg.toString())
                                    .into(imgPetSitter)

                                val career = StringBuilder()

                                it.petSitter.petSitterCareer.map { petSitterCareer ->
                                    career.append(petSitterCareer).append("\n")
                                }

                                val certification = StringBuilder()
                                it.petSitter.petSitterCertificate.map { certificate ->
                                    certification.append(certificate).append("\n")
                                }

                                val introduce =
                                    it.petSitter.petSitterIntroduce.split("\\.".toRegex())
                                        .joinToString("\n\n") { str ->
                                            str.trim()
                                        }

                                petSitterName.text = it.petSitter.petSitterName
                                petSitterIntroduce.text = introduce
                                petSitterCertification.text = certification
                                this.petSitterCareer.text = career
                            }

                        }

                    }
                }
            }
        }
    }
}
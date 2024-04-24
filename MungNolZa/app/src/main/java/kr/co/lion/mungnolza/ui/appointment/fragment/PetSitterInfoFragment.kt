package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetSitterInfoBinding
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModelFactory

class PetSitterInfoFragment : Fragment(R.layout.fragment_pet_sitter_info) {
    private val args: PetSitterInfoFragmentArgs by navArgs()
    private val viewModel: AppointmentViewModel by activityViewModels { AppointmentViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPetSitterInfoBinding.bind(view)

        with(binding) {
            toolbar.setNavigationOnClickListener {
                val action = PetSitterInfoFragmentDirections.toMatchingFragment()
                Navigation.findNavController(view).navigate(action)
            }

            val petSitter = args.info
            if (petSitter != null) {

                val career = StringBuilder()
                petSitter.petSitterCareer.map {
                    career.append(it).append("\n")
                }

                val certification = StringBuilder()
                petSitter.petSitterCertificate.map {
                    certification.append(it).append("\n")
                }

                val introduce = petSitter.petSitterIntroduce.split("\\.".toRegex())
                    .joinToString("\n\n") {
                        it.trim()
                    }

                viewLifecycleOwner.lifecycleScope.launch {
                    val img = viewModel.fetchPetSitterImage(petSitter.petSitterIdx, petSitter.imgName)
                    Glide.with(binding.root)
                        .load(img.toString())
                        .into(binding.imgPetSitter)
                }

                petSitterName.text = petSitter.petSitterName
                petSitterIntroduce.text = introduce
                petSitterCertification.text = certification
                this.petSitterCareer.text = career
            }
        }
    }
}
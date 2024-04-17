package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kr.co.lion.mungnolza.util.MatchingPetsitterFragmentName

class PetSitterInfoFragment : Fragment() {
    private var _binding: FragmentPetSitterInfoBinding? = null
    private val binding get() = _binding!!
    private val args : PetSitterInfoFragmentArgs by navArgs()
    private val viewModel: AppointmentViewModel by activityViewModels { AppointmentViewModelFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPetSitterInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View){
        initToolbar(view)

        val petSitter = args.info
        if (petSitter != null){

            val career = StringBuilder()
            petSitter.petSitterCareer.map {
                career.append(it).append("\n")
            }

            val certification = StringBuilder()
            petSitter.petSitterCertificate.map {
                certification.append(it).append("\n")
            }

            val introduce = petSitter.petSitterIntroduce.
                            split("\\.".toRegex())
                            .joinToString("\n\n") {
                                it.trim()
                            }

            viewLifecycleOwner.lifecycleScope.launch {
                val img = viewModel.fetchPetSitterImage(petSitter.petSitterIdx, petSitter.imgName)
                Glide.with(binding.root)
                    .load(img.toString())
                    .into(binding.imgPetSitter)
            }

            with(binding){
                petSitterName.text = petSitter.petSitterName
                petSitterIntroduce.text = introduce
                petSitterCertification.text = certification
                this.petSitterCareer.text = career
            }
        }


        binding.reviewCnt.setOnClickListener{
            val action = PetSitterInfoFragmentDirections.toPetSitterReviewFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun initToolbar(view: View){
        with(binding.toolbar){
            setNavigationOnClickListener {
                val action = PetSitterInfoFragmentDirections.toMatchingFragment()
                Navigation.findNavController(view).navigate(action)
            }
        }
    }
}
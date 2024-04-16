package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetSitterInfoBinding
import kr.co.lion.mungnolza.util.MatchingPetsitterFragmentName

class PetSitterInfoFragment : Fragment() {
    private var _binding: FragmentPetSitterInfoBinding? = null
    private val binding get() = _binding!!

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

        binding.btnPetSitterReview.setOnClickListener{
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
package kr.co.lion.mungnolza.ui.onboarding.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import kr.co.lion.mungnolza.databinding.FragmentOnBoarding1Binding
import kr.co.lion.mungnolza.ui.onboarding.vm.OnBoardingViewModel
import kr.co.lion.mungnolza.ui.onboarding.vm.OnBoardingViewModelFactory

class OnBoardingFragment1 : Fragment() {
    private var _binding: FragmentOnBoarding1Binding? = null
    private val binding get() = _binding!!
    private val viewModel: OnBoardingViewModel by activityViewModels { OnBoardingViewModelFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentOnBoarding1Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMyAllPetData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
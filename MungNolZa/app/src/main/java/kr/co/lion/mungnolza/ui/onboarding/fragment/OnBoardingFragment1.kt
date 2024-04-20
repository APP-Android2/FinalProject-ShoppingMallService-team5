package kr.co.lion.mungnolza.ui.onboarding.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.ui.onboarding.vm.OnBoardingViewModel
import kr.co.lion.mungnolza.ui.onboarding.vm.OnBoardingViewModelFactory

class OnBoardingFragment1 : Fragment(R.layout.fragment_on_boarding1) {
    private val viewModel: OnBoardingViewModel by activityViewModels { OnBoardingViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMyAllPetData()
    }
}
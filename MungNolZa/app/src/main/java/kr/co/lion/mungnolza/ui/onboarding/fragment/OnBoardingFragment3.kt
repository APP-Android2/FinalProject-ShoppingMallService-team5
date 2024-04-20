package kr.co.lion.mungnolza.ui.onboarding.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentOnBoarding3Binding
import kr.co.lion.mungnolza.ui.main.MainActivity
import kr.co.lion.mungnolza.ui.onboarding.vm.OnBoardingViewModel
import kr.co.lion.mungnolza.ui.onboarding.vm.OnBoardingViewModelFactory

class OnBoardingFragment3 : Fragment(R.layout.fragment_on_boarding3) {
    private val viewModel: OnBoardingViewModel by activityViewModels { OnBoardingViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentOnBoarding3Binding.bind(view)
        binding.btnNext.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.myPetData.collect {
                        val intent = Intent(requireActivity(), MainActivity::class.java)
                        intent.putExtra("myPet", it.toTypedArray())
                        startActivity(intent)
                    }
                }
            }
        }
    }
}
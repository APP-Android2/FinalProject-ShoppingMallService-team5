package kr.co.lion.mungnolza.ui.onboarding.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.databinding.FragmentOnBoarding3Binding
import kr.co.lion.mungnolza.ui.main.MainActivity
import kr.co.lion.mungnolza.ui.main.vm.MainViewModel
import kr.co.lion.mungnolza.ui.main.vm.MainViewModelFactory
import kr.co.lion.mungnolza.ui.onboarding.vm.OnBoardingViewModel
import kr.co.lion.mungnolza.ui.onboarding.vm.OnBoardingViewModelFactory

class OnBoardingFragment3 : Fragment() {
    private var _binding: FragmentOnBoarding3Binding? = null
    private val binding get() = _binding!!
    private val viewModel: OnBoardingViewModel by activityViewModels { OnBoardingViewModelFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentOnBoarding3Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
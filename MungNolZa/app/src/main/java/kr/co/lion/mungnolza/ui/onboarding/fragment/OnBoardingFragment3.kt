package kr.co.lion.mungnolza.ui.onboarding.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentOnBoarding3Binding
import kr.co.lion.mungnolza.ui.main.MainActivity

class OnBoardingFragment3 : Fragment(R.layout.fragment_on_boarding3) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentOnBoarding3Binding.bind(view)
        binding.btnNext.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }
    }
}
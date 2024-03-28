package kr.co.lion.mungnolza.ui.intro.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentOnBoarding1Binding

class OnBoardingFragment1 : Fragment() {
    private var _binding: FragmentOnBoarding1Binding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentOnBoarding1Binding.inflate(layoutInflater)
        return inflater.inflate(R.layout.fragment_on_boarding1, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
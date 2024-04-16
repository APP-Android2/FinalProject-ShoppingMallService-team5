package kr.co.lion.mungnolza.ui.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.lion.mungnolza.databinding.ActivityOnBoardingBinding
import kr.co.lion.mungnolza.ui.intro.adapter.OnBoardingViewPagerAdapter
import kr.co.lion.mungnolza.ui.onboarding.fragment.OnBoardingFragment1
import kr.co.lion.mungnolza.ui.onboarding.fragment.OnBoardingFragment2
import kr.co.lion.mungnolza.ui.onboarding.fragment.OnBoardingFragment3

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView(){
        val fragments = listOf(OnBoardingFragment1(), OnBoardingFragment2(), OnBoardingFragment3())
        val vAdapter = OnBoardingViewPagerAdapter(fragments, this)
        binding.viewpager.adapter = vAdapter
    }
}
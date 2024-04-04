package kr.co.lion.mungnolza.ui.intro.activity

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityOnBoardingBinding
import kr.co.lion.mungnolza.ui.intro.adapter.OnBoardingViewPagerAdapter
import kr.co.lion.mungnolza.ui.intro.fragment.OnBoardingFragment1
import kr.co.lion.mungnolza.ui.intro.fragment.OnBoardingFragment2
import kr.co.lion.mungnolza.ui.intro.fragment.OnBoardingFragment3

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView(){
        val fragments = listOf(OnBoardingFragment1(), OnBoardingFragment2() ,  OnBoardingFragment3())
        val vAdapter = OnBoardingViewPagerAdapter(fragments, this)
        binding.viewpager.adapter = vAdapter


    }
}
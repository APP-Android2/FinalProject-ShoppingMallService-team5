package kr.co.lion.mungnolza.ui.petsitter_main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityPetsitterMainBinding
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.ui.petsitter_main.fragment.PetsitterHomeFragment
import kr.co.lion.mungnolza.ui.petsitter_main.fragment.PetsitterReservationListFragment

class PetsitterMainActivity : AppCompatActivity() {

    lateinit var activityPetsitterMainBinding: ActivityPetsitterMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityPetsitterMainBinding = ActivityPetsitterMainBinding.inflate(layoutInflater)
        setContentView(activityPetsitterMainBinding.root)

        initView()
    }

    private fun initView(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_petsitter) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(activityPetsitterMainBinding.navigationView, navController)

    }
}
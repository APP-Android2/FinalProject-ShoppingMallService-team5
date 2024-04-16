package kr.co.lion.mungnolza.ui.petsitter_main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityPetsitterMainBinding
import kr.co.lion.mungnolza.model.PetImgModel

class PetsitterMainActivity : AppCompatActivity() {

    private lateinit var activityPetsitterMainBinding: ActivityPetsitterMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityPetsitterMainBinding = ActivityPetsitterMainBinding.inflate(layoutInflater)
        setContentView(activityPetsitterMainBinding.root)
    }

    private fun initView(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(activityPetsitterMainBinding.navigationView, navController)

    }
}
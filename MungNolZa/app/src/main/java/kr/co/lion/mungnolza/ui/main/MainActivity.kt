package kr.co.lion.mungnolza.ui.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityMainBinding
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.ui.main.viewmodel.MainViewModel
import kr.co.lion.mungnolza.ui.main.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView(){
        val myPet: List<PetImgModel>? = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableArrayExtra("myPet", PetImgModel::class.java)?.mapNotNull { it }?.toTypedArray()?.toList()
        } else {
            intent.getParcelableArrayExtra("myPet")?.mapNotNull { it as? PetImgModel }?.toTypedArray()?.toList()
        }

        if (myPet != null) {
            viewModel.setMyPetData(myPet)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.navigationView, navController)

    }
}
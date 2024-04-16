package kr.co.lion.mungnolza.ui.onboarding.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.mungnolza.repository.pet.PetRepositoryImpl
import java.lang.IllegalArgumentException

class OnBoardingViewModelFactory: ViewModelProvider.Factory {
    private val petRepositoryImpl = PetRepositoryImpl()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OnBoardingViewModel::class.java)){
            return OnBoardingViewModel(petRepositoryImpl) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}
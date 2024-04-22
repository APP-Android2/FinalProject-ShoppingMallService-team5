package kr.co.lion.mungnolza.ui.intro.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.mungnolza.repository.pet.PetRepositoryImpl

class StartViewModelFactory: ViewModelProvider.Factory {
    private val petRepositoryImpl = PetRepositoryImpl()
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StartViewModel::class.java)){
            return StartViewModel(petRepositoryImpl) as T
        }
        return super.create(modelClass)
    }
}
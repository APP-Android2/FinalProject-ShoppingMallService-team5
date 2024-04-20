package kr.co.lion.mungnolza.ui.appointment.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.mungnolza.repository.petsitter.PetSitterRepositoryImpl
import java.lang.IllegalArgumentException

class PetSitterInfoViewModelFactory: ViewModelProvider.Factory {
    private val petSitterRepositoryImpl = PetSitterRepositoryImpl()
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetSitterInfoViewModel::class.java)){
            return PetSitterInfoViewModel(petSitterRepositoryImpl) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}
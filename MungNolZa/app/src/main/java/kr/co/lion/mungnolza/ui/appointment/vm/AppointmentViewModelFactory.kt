package kr.co.lion.mungnolza.ui.appointment.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.mungnolza.repository.pet.PetRepositoryImpl
import java.lang.IllegalArgumentException

class AppointmentViewModelFactory: ViewModelProvider.Factory {
    private val petRepositoryImpl = PetRepositoryImpl()
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppointmentViewModel::class.java)){
            return AppointmentViewModel(petRepositoryImpl) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}
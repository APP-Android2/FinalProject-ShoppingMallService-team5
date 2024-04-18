package kr.co.lion.mungnolza.ui.appointment.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.mungnolza.repository.petsitter.PetSitterRepositoryImpl
import kr.co.lion.mungnolza.repository.reservation.ReservationRepositoryImpl
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl
import java.lang.IllegalArgumentException

class AppointmentViewModelFactory: ViewModelProvider.Factory {
    private val userRepositoryImpl = UserRepositoryImpl()
    private val petSitterRepositoryImpl = PetSitterRepositoryImpl()
    private val reservationRepositoryImpl = ReservationRepositoryImpl()
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppointmentViewModel::class.java)){
            return AppointmentViewModel(
                userRepositoryImpl,
                petSitterRepositoryImpl,
                reservationRepositoryImpl)
            as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}
package kr.co.lion.mungnolza.ui.appointment.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kr.co.lion.mungnolza.repository.petsitter.PetSitterRepositoryImpl
import kr.co.lion.mungnolza.repository.reservation.ReservationRepositoryImpl
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl
import java.lang.IllegalArgumentException

class AppointmentViewModelFactory: ViewModelProvider.Factory {
    private val userRepositoryImpl = UserRepositoryImpl(
        Firebase.firestore.collection("User"),
        Firebase.storage.reference
    )
    private val petSitterRepositoryImpl = PetSitterRepositoryImpl(
        Firebase.firestore.collection("Petsitter"),
        Firebase.storage.reference
    )
    private val reservationRepositoryImpl = ReservationRepositoryImpl(
        Firebase.firestore.collection("Reservation")
    )
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
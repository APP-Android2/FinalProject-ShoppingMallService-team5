package kr.co.lion.mungnolza.ui.appointment.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kr.co.lion.mungnolza.repository.petsitter.PetSitterRepositoryImpl
import kr.co.lion.mungnolza.repository.review.ReviewRepositoryImpl
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl
import java.lang.IllegalArgumentException

class PetSitterInfoViewModelFactory: ViewModelProvider.Factory {
    private val userRepository = UserRepositoryImpl(
        Firebase.firestore.collection("User"),
        Firebase.storage.reference
    )

    private val reviewRepository = ReviewRepositoryImpl(
        Firebase.firestore.collection("petsitterReviewModel")
    )

    private val petSitterRepositoryImpl = PetSitterRepositoryImpl(
        Firebase.firestore.collection("Petsitter"),
        Firebase.storage.reference
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetSitterInfoViewModel::class.java)){
            return PetSitterInfoViewModel(userRepository, reviewRepository, petSitterRepositoryImpl) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}
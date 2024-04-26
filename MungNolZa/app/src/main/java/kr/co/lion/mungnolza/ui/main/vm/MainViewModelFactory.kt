package kr.co.lion.mungnolza.ui.main.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kr.co.lion.mungnolza.datasource.local.MainDataBase
import kr.co.lion.mungnolza.repository.freeboard.FreeBoardRepositoryImpl
import kr.co.lion.mungnolza.repository.pet.PetRepositoryImpl
import kr.co.lion.mungnolza.repository.review.ReviewRepositoryImpl
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl
import java.lang.IllegalArgumentException

class MainViewModelFactory(context: Context): ViewModelProvider.Factory{
    private val boardRepository = FreeBoardRepositoryImpl(
        Firebase.firestore.collection("Board"),
        Firebase.storage.reference
    )
    private val userRepository = UserRepositoryImpl(
        Firebase.firestore.collection("User"),
        Firebase.storage.reference
    )
    private val petRepositoryImpl = PetRepositoryImpl(
        Firebase.firestore.collection("Pet"),
        Firebase.storage.reference,
        MainDataBase.getDatabase(context).myPetDao()
    )
    private val reviewRepository = ReviewRepositoryImpl(
        Firebase.firestore.collection("petsitterReviewModel")
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(boardRepository, userRepository, petRepositoryImpl, reviewRepository) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}
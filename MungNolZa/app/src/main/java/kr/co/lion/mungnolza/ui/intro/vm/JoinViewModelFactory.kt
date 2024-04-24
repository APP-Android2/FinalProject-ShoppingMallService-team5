package kr.co.lion.mungnolza.ui.intro.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl

class JoinViewModelFactory : ViewModelProvider.Factory {
    private val userRepositoryImpl = UserRepositoryImpl(
        Firebase.firestore.collection("User"),
        Firebase.storage.reference
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JoinViewModel::class.java)) {
            return JoinViewModel(userRepositoryImpl) as T
        }
        return super.create(modelClass)
    }
}
package kr.co.lion.mungnolza.ui.intro.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kr.co.lion.mungnolza.datasource.local.MainDataBase
import kr.co.lion.mungnolza.repository.pet.PetRepositoryImpl

class StartViewModelFactory(context: Context): ViewModelProvider.Factory {
    private val petRepositoryImpl = PetRepositoryImpl(
        Firebase.firestore.collection("Pet"),
        Firebase.storage.reference,
        MainDataBase.getDatabase(context).myPetDao()
    )
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StartViewModel::class.java)){
            return StartViewModel(petRepositoryImpl) as T
        }
        return super.create(modelClass)
    }
}
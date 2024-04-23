package kr.co.lion.mungnolza.ui.main.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.mungnolza.datasource.local.MainDataBase
import kr.co.lion.mungnolza.repository.freeboard.FreeBoardRepositoryImpl
import kr.co.lion.mungnolza.repository.pet.PetRepositoryImpl
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl
import java.lang.IllegalArgumentException

class MainViewModelFactory(context: Context): ViewModelProvider.Factory{
    private val boardRepository = FreeBoardRepositoryImpl()
    private val userRepository = UserRepositoryImpl()
    private val petRepositoryImpl = PetRepositoryImpl(MainDataBase.getDatabase(context).myPetDao())

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(boardRepository, userRepository, petRepositoryImpl) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}
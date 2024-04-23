package kr.co.lion.mungnolza.ui.intro.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.mungnolza.datasource.local.MainDataBase
import kr.co.lion.mungnolza.repository.pet.PetRepositoryImpl
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl

class LoginViewModelFactory(context: Context): ViewModelProvider.Factory {
    private val userRepositoryImpl = UserRepositoryImpl()
    private val petRepositoryImpl = PetRepositoryImpl(MainDataBase.getDatabase(context).myPetDao())

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(userRepositoryImpl, petRepositoryImpl) as T
        }
        return super.create(modelClass)
    }
}
package kr.co.lion.mungnolza.ui.intro.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.mungnolza.repository.pet.PetRepositoryImpl
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl

class LoginViewModelFactory: ViewModelProvider.Factory {
    private val userRepositoryImpl = UserRepositoryImpl()
    private val petRepositoryImpl = PetRepositoryImpl()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(userRepositoryImpl, petRepositoryImpl) as T
        }
        return super.create(modelClass)
    }
}
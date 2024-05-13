package kr.co.lion.mungnolza.ui.intro.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.datasource.MainDataStore
import kr.co.lion.mungnolza.model.UserModel
import kr.co.lion.mungnolza.repository.user.UserRepository
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl

class JoinViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    fun userJoin(userModel: UserModel) = viewModelScope.launch{
        userRepository.userJoin(userModel)
    }

    fun setUpDataStore(userNumber: String) = viewModelScope.launch {
        MainDataStore.setupFirstData()
        MainDataStore.setUserNumber(userNumber)
    }

}
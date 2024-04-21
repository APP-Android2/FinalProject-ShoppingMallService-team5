package kr.co.lion.mungnolza.ui.intro.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.datasource.MainDataStore
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl

class LoginViewModel(
    private val userRepositoryImpl: UserRepositoryImpl
): ViewModel() {

    suspend fun isExistUser(userId: String): Boolean{
        return userRepositoryImpl.fetchAllUserId().contains(userId)
    }

    suspend fun checkFistFlag(): Boolean{
        return MainDataStore.getFirstFlag()
    }

    fun setUpDataStore(userNumber: String) = viewModelScope.launch {
        MainDataStore.setupFirstData()
        MainDataStore.setUserNumber(userNumber)
    }
}
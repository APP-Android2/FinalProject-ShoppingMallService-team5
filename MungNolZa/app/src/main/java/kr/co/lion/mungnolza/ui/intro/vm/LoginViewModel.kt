package kr.co.lion.mungnolza.ui.intro.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.datasource.MainDataStore
import kr.co.lion.mungnolza.datasource.local.MyPetEntity
import kr.co.lion.mungnolza.repository.pet.PetRepositoryImpl
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl

class LoginViewModel(
    private val userRepositoryImpl: UserRepositoryImpl,
    private val petRepositoryImpl: PetRepositoryImpl
): ViewModel() {

    fun onLoginSuccess(ownerIdx: String, callback: (Boolean) -> Unit) = viewModelScope.launch {
        val success = try{
            MainDataStore.setupFirstData()
            MainDataStore.setUserNumber(ownerIdx)

            val result = petRepositoryImpl.fetchMyPetData(ownerIdx)

            result.map{
                petRepositoryImpl.insertMyPetData(
                    MyPetEntity(
                    petName = it.petName,
                    ownerIdx = it.ownerIdx,
                    petBreed = it.petBreed,
                    petGender = it.petGender,
                    petWeight = it.petWeight,
                    petAge = it.petAge,
                    isNeutering = it.isNeutering,
                    petSignificant = it.petSignificant,
                    imgPath = it.imgName
                ))
            }
            true
        }catch (e: Exception){
            Log.d("FireBaseResult", "Error Message : $e")
            false
        }
        callback(success)
    }


    suspend fun isExistUser(userId: String): Boolean{
        return userRepositoryImpl.fetchAllUserId().contains(userId)
    }

    suspend fun checkFistFlag(): Boolean{
        return MainDataStore.getFirstFlag()
    }

}
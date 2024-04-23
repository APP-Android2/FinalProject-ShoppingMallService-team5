package kr.co.lion.mungnolza.ui.intro.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.datasource.MainDataStore
import kr.co.lion.mungnolza.datasource.local.MyPetEntity
import kr.co.lion.mungnolza.repository.pet.PetRepositoryImpl

class StartViewModel(
    private val petRepositoryImpl: PetRepositoryImpl
): ViewModel() {

    fun onLoginSuccess(callback: (Boolean) -> Unit) = viewModelScope.launch {
        val success = try{
            val myUserNumber = MainDataStore.getUserNumber().stateIn(this).value
            val result = petRepositoryImpl.fetchMyPetData(myUserNumber)

            result.map{
                petRepositoryImpl.insertMyPetData(MyPetEntity(
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
            false
        }
        callback(success)
    }

    suspend fun checkFistFlag(): Boolean{
        return MainDataStore.getFirstFlag()
    }
}
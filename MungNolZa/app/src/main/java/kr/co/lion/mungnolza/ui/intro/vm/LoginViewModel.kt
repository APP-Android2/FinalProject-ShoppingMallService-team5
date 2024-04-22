package kr.co.lion.mungnolza.ui.intro.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.datasource.MainDataStore
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.repository.pet.PetRepositoryImpl
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl
import java.net.URI

class LoginViewModel(
    private val userRepositoryImpl: UserRepositoryImpl,
    private val petRepositoryImpl: PetRepositoryImpl
): ViewModel() {

    private val _myPetData = MutableStateFlow<List<PetImgModel>>(emptyList())
    val myPetData = _myPetData.asStateFlow()

    fun fetchMyAllPetData(ownerIdx: String,callback: (Boolean) -> Unit) = viewModelScope.launch {
        val petList = ArrayList<PetImgModel>()

        val success = try{
            val result = petRepositoryImpl.fetchMyPetData(ownerIdx)

            result.map{
                val imgUri = fetchPetImg(it.ownerIdx, it.imgName)
                val pet = imgUri?.let { data -> PetImgModel(it, data) }

                if (pet != null) {
                    petList.add(pet)
                }
            }
            _myPetData.value = petList
            true
        }catch (e: Exception){
            false
        }
        callback(success)
    }

    private suspend fun fetchPetImg(ownerIdx: String, imgName: String): URI? {
        return petRepositoryImpl.fetchMyPetImage(ownerIdx, imgName)
    }

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
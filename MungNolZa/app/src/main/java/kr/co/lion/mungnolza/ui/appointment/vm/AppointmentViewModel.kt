package kr.co.lion.mungnolza.ui.appointment.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.datasource.MainDataStore
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.repository.pet.PetRepositoryImpl
import java.net.URI

class AppointmentViewModel(
    private val petRepositoryImpl: PetRepositoryImpl
) : ViewModel() {
    private val _careType = MutableStateFlow<Boolean?>(null)
    val careType = _careType.asStateFlow()

    private val _myUserNumber = MutableStateFlow<String?>(null)
    private val myUserNumber = _myUserNumber.asStateFlow()

    private val _myPetData = MutableStateFlow<List<PetImgModel>>(emptyList())
    val myPetData = _myPetData.asStateFlow()

    init {
        viewModelScope.launch {
            //MainDataStore.setUserNumber("1234")
            MainDataStore.getUserNumber().collect {
                _myUserNumber.value = it
            }
        }
    }

    suspend fun fetchMyAllPetData() = viewModelScope.launch {
        val petList = ArrayList<PetImgModel>()

        myUserNumber.collect { myUserNumber ->
            val result = myUserNumber?.let { data-> petRepositoryImpl.fetchMyPetData(data) }
            result?.map{
                val imgUri = fetchPetImg(it.ownerIdx, it.imgName)
                val pet = imgUri?.let { data -> PetImgModel(it, data) }

                if (pet != null) {
                    petList.add(pet)
                }
            }
            _myPetData.value = petList
        }
    }

    private suspend fun fetchPetImg(userIdx: String, petName: String): URI? {
        return petRepositoryImpl.fetchMyPetImage(userIdx, petName)
    }

    fun setCareType(state: Boolean) {
        _careType.value = state
    }


}
package kr.co.lion.mungnolza.ui.intro.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.datasource.MainDataStore
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.repository.pet.PetRepositoryImpl
import java.net.URI

class StartViewModel(
    private val petRepositoryImpl: PetRepositoryImpl
): ViewModel() {

    private val _myPetData = MutableStateFlow<List<PetImgModel>>(emptyList())
    val myPetData = _myPetData.asStateFlow()

    init {
        fetchMyAllPetData()
    }
    private fun fetchMyAllPetData() = viewModelScope.launch {
        val petList = ArrayList<PetImgModel>()
        MainDataStore.getUserNumber().collect { myUserNumber ->
            val result = myUserNumber.let { data-> petRepositoryImpl.fetchMyPetData(data) }
            result.map{
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
    suspend fun checkFistFlag(): Boolean{
        return MainDataStore.getFirstFlag()
    }
}
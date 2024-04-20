package kr.co.lion.mungnolza.ui.appointment.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.model.PetSitterModel
import kr.co.lion.mungnolza.model.PetSitterModelWithImg
import kr.co.lion.mungnolza.repository.petsitter.PetSitterRepositoryImpl
import java.net.URI

class PetSitterInfoViewModel(private val petSitterRepositoryImpl: PetSitterRepositoryImpl) : ViewModel() {
    private val _petSitter = MutableStateFlow<PetSitterModelWithImg?>(null)
    val petSitter get() = _petSitter.asStateFlow()

    fun getPetSitterInfo(petSitterIdx: String) = viewModelScope.launch {
        val response = petSitterRepositoryImpl.fetchPetSitterData(petSitterIdx)
        val imgUrl = fetchPetSitterImage(response.petSitterIdx, response.imgName)
        _petSitter.value = imgUrl?.let { PetSitterModelWithImg(response, it) }
    }

    private suspend fun fetchPetSitterImage(petSitterIdx: String, imgName: String): URI? {
        return petSitterRepositoryImpl.fetchPetSitterImage(petSitterIdx, imgName)
    }
}
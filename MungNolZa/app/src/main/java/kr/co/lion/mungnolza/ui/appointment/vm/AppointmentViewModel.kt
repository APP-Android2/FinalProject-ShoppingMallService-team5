package kr.co.lion.mungnolza.ui.appointment.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.datasource.MainDataStore
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.model.SelectScheduleModel
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl

class AppointmentViewModel(
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel() {

    private val _myUserNumber = MutableStateFlow<String?>(null)
    private val myUserNumber = _myUserNumber.asStateFlow()

    private val _myPetData = MutableStateFlow<List<PetImgModel>>(emptyList())
    val myPetData = _myPetData.asStateFlow()

    private val _selectedPet = MutableStateFlow<List<PetImgModel>>(emptyList())
    val selectedPet = _selectedPet.asStateFlow()

    private val _serviceType: MutableStateFlow<String?> = MutableStateFlow(null)
    val serviceType = _serviceType.asStateFlow()

    private val _careType: MutableStateFlow<String?> = MutableStateFlow(null)
    val careType = _careType.asStateFlow()

    private val _userAddress: MutableStateFlow<String?> = MutableStateFlow(null)
    val userAddress = _userAddress.asStateFlow()

    private val _reserveSchedule: MutableStateFlow<SelectScheduleModel?> = MutableStateFlow(null)
    val reserveSchedule = _reserveSchedule.asStateFlow()

    init {
        viewModelScope.launch {
            MainDataStore.getUserNumber().collect {
                _myUserNumber.value = it
                getUserAddress(it)
            }
        }
    }

     private suspend fun getUserAddress(userNumber: String){
        _userAddress.value = userRepositoryImpl.fetchUserAddress(userNumber)
     }

    fun setSchedule(schedule: SelectScheduleModel){
        _reserveSchedule.value = schedule
    }

    fun setSelectedPet(selectedItem: List<PetImgModel>){
        _selectedPet.value = selectedItem
    }

    fun setServiceType(service: String){
        _serviceType.value = service
    }

    fun setMyPetData(myPets: List<PetImgModel>){
        _myPetData.value = myPets
    }

    fun setCareType(type: String?) {
        _careType.value = type
    }
}
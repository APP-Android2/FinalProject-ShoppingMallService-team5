package kr.co.lion.mungnolza.ui.appointment.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.datasource.MainDataStore
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.model.PetSitterModelWithImg
import kr.co.lion.mungnolza.model.SelectScheduleModel
import kr.co.lion.mungnolza.repository.petsitter.PetSitterRepository
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl
import java.net.URI

class AppointmentViewModel(
    private val userRepositoryImpl: UserRepositoryImpl,
    private val petSitterRepository: PetSitterRepository
) : ViewModel() {

    private val _myUserNumber = MutableStateFlow<String?>(null)
    private val myUserNumber = _myUserNumber.asStateFlow()

    private val _myPetData = MutableStateFlow<List<PetImgModel>>(emptyList())
    val myPetData = _myPetData.asStateFlow()

    private val _selectedPet = MutableStateFlow<List<PetImgModel>>(emptyList())
    val selectedPet = _selectedPet.asStateFlow()

    private val _fromWhere: MutableStateFlow<String> = MutableStateFlow("")
    val fromWhere = _fromWhere.asStateFlow()

    private val _serviceType: MutableStateFlow<String?> = MutableStateFlow(null)
    val serviceType = _serviceType.asStateFlow()

    private val _careType: MutableStateFlow<String?> = MutableStateFlow(null)
    val careType = _careType.asStateFlow()

    private val _userAddress: MutableStateFlow<String?> = MutableStateFlow(null)
    val userAddress = _userAddress.asStateFlow()

    private val _reserveSchedule: MutableStateFlow<SelectScheduleModel?> = MutableStateFlow(null)
    val reserveSchedule = _reserveSchedule.asStateFlow()

    private val _petSitterData: MutableStateFlow<ArrayList<PetSitterModelWithImg>?> = MutableStateFlow(null)
    val petSitterData = _petSitterData.asStateFlow()

    init {
        viewModelScope.launch {
            MainDataStore.getUserNumber().collect {
                _myUserNumber.value = it
                getUserAddress(it)
            }
        }
    }

    fun fetchAllPetSitterData() = viewModelScope.launch{
        val response = petSitterRepository.fetchAllPetSitterData()
        val petSitterList = ArrayList<PetSitterModelWithImg>()

        response.map{
            val imgUri = fetchPetSitterImage(it.petSitterIdx, it.imgName)
            val petSitter = imgUri?.let { uri -> PetSitterModelWithImg(it, uri) }
            if (petSitter != null) {
                petSitterList.add(petSitter)
            }
        }
        _petSitterData.value = petSitterList
    }

    suspend fun fetchPetSitterImage(petSitterIdx: String, imgName: String): URI? {
        return petSitterRepository.fetchPetSitterImage(petSitterIdx, imgName)
    }

     private suspend fun getUserAddress(userNumber: String){
        _userAddress.value = userRepositoryImpl.fetchUserAddress(userNumber)
     }

    fun setFlag(flag: String){
        _fromWhere.value = flag
    }

    fun setPayment(payment: PaymentTimeModel){
        _payment.value = payment
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
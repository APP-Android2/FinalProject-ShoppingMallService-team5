package kr.co.lion.mungnolza.ui.appointment.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.datasource.MainDataStore
import kr.co.lion.mungnolza.model.CareServiceModel
import kr.co.lion.mungnolza.model.PaymentTimeModel
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.model.PetSitterModelWithImg
import kr.co.lion.mungnolza.model.PetSitterWithReviewModel
import kr.co.lion.mungnolza.model.SelectScheduleModel
import kr.co.lion.mungnolza.model.WalkServiceModel
import kr.co.lion.mungnolza.repository.petsitter.PetSitterRepository
import kr.co.lion.mungnolza.repository.reservation.ReservationRepositoryImpl
import kr.co.lion.mungnolza.repository.review.ReviewRepositoryImpl
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl
import java.text.SimpleDateFormat
import java.util.Date

class AppointmentViewModel(
    private val userRepositoryImpl: UserRepositoryImpl,
    private val petSitterRepository: PetSitterRepository,
    private val reservationRepositoryImpl: ReservationRepositoryImpl,
    private val reviewRepository: ReviewRepositoryImpl,
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

    private val _payment: MutableStateFlow<PaymentTimeModel> = MutableStateFlow(PaymentTimeModel(0, ""))
    val payment = _payment.asStateFlow()

    private val _userAddress: MutableStateFlow<String?> = MutableStateFlow(null)
    val userAddress = _userAddress.asStateFlow()

    private val _reserveSchedule: MutableStateFlow<SelectScheduleModel> = MutableStateFlow(SelectScheduleModel())
    val reserveSchedule = _reserveSchedule.asStateFlow()

    private val _petSitterData: MutableStateFlow<List<PetSitterWithReviewModel>> = MutableStateFlow(emptyList())
    val petSitterData = _petSitterData.asStateFlow()

    init {
        viewModelScope.launch {
            MainDataStore.getUserNumber().collect {
                _myUserNumber.value = it
                getUserAddress(it)
            }
        }
    }

    fun onPaymentSuccess(petSitterIdx: String){
        when(serviceType.value.toString()){
            "JOGGING" -> {
                requestWalkService(petSitterIdx)
            }
            "CARE" -> {
                requestCareService(petSitterIdx)
            }
        }
    }

    private fun requestCareService(petSitterIdx: String) = viewModelScope.launch{
        val request = CareServiceModel(
            myUserNumber.value.toString(),
            reservationRepositoryImpl.findLastReservationIdx() + 1,
                reserveSchedule.value,
                careType.value.toString(),
                petSitterIdx,
                getCurrentDate(),
                true
                )
        reservationRepositoryImpl.careServiceRequest(request)
    }

    private fun requestWalkService(petSitterIdx: String) = viewModelScope.launch{
        val request = WalkServiceModel(
            myUserNumber.value.toString(),
            reservationRepositoryImpl.findLastReservationIdx() + 1,
            reserveSchedule.value,
            petSitterIdx,
            getCurrentDate(),
            true
        )
        reservationRepositoryImpl.walkServiceRequest(request)
    }

    fun onStartMatching() = viewModelScope.launch {
        val response = petSitterRepository.fetchAllPetSitterData()
        val petSitterList = ArrayList<PetSitterWithReviewModel>()

        response.map {
            val imgUri = petSitterRepository.fetchPetSitterImage(it.petSitterIdx, it.imgName)
            val review = reviewRepository.fetchPetSitterReview(it.petSitterIdx.toInt())

            val petSitter = PetSitterWithReviewModel(
                PetSitterModelWithImg(it, imgUri),
                review
            )
            petSitterList.add(petSitter)
        }
        _petSitterData.value = petSitterList
    }

    private suspend fun getUserAddress(userNumber: String) {
        _userAddress.value = userRepositoryImpl.fetchUserAddress(userNumber)
    }

    fun setFlag(flag: String) {
        _fromWhere.value = flag
    }

    fun setPayment(payment: PaymentTimeModel) {
        _payment.value = payment
    }

    fun onCompleteSchedule(schedule: SelectScheduleModel) {
        _reserveSchedule.value = schedule
    }

    fun onSelectPet(selectedItem: List<PetImgModel>) {
        _selectedPet.value = selectedItem
    }

    fun setServiceType(service: String) {
        _serviceType.value = service
    }

    fun setMyPetData(myPets: List<PetImgModel>) {
        _myPetData.value = myPets
    }

    fun setCareType(type: String?) {
        _careType.value = type
    }

    private fun getCurrentDate(): String{
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        return simpleDateFormat.format(Date())
    }
}
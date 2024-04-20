package kr.co.lion.mungnolza.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectScheduleModel(
    val address: String = "",
    val reserveTime: String = "",
    val customerRequests:String = "",
    val serviceType: String = "",
    val serviceTime: String = "",
    val reservedPets: List<PetImgModel> = emptyList(),
    val reserveDate: List<String> = emptyList(),
    val totalPrice: Int = 0
): Parcelable
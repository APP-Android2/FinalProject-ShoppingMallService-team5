package kr.co.lion.mungnolza.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectScheduleModel(
    val address: String,
    val reserveTime: String,
    val customerRequests:String,
    val visitType: String,
    val reservedPets: List<PetImgModel>,
    val reserveDate: List<String>,
    val totalPrice: Int
): Parcelable {
    constructor(): this(
        address = "",
        reserveTime = "",
        customerRequests = "",
        visitType = "",
        reservedPets = emptyList<PetImgModel>(),
        reserveDate = emptyList<String>(),
        totalPrice = 0
    )
}
package kr.co.lion.mungnolza.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PetModel (
    val ownerIdx: String,
    val petName: String,
    val petBreed: String,
    val petGender: String,
    val petWeight: String,
    val petAge: String,
    val isNeutering: Boolean,
    val petSignificant: String,
    val imgName: String
): Parcelable {
    constructor(): this(
        ownerIdx = "",
        petName = "",
        petBreed = "",
        petGender = "",
        petWeight = "",
        petAge = "",
        isNeutering = true,
        petSignificant = "",
        imgName = ""
    )
}
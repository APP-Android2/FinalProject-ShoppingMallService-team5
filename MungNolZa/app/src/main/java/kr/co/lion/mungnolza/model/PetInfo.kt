package kr.co.lion.mungnolza.model

import android.graphics.Bitmap

data class PetInfo (
    val petImg: Bitmap,
    val petName: String,
    val petKind: String,
    val petGender: String,
    val petAge: Int,
    val petWeight: String,
    val petNeutering: String,
    val petSignificant: String
)
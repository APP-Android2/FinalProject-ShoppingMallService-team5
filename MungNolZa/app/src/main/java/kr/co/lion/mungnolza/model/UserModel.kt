package kr.co.lion.mungnolza.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val uniqueNumber: String = "",
    val userNickname: String = "",
    val userName: String = "",
    val userEmail: String = "",
    val userPhone: String = "",
    val userAddress: String = "",
    val userProfileImgPath: String = "",
    val userAgeRange : String = "",
    val userGender: String = ""
): Parcelable
package kr.co.lion.mungnolza.model

data class UserModel(
    val uniqueNumber: String,
    val userNickname: String,
    val userName: String,
    val userEmail: String,
    val userPhone: String,
    val userAddress: String,
    val userProfileImgPath: String,
    val userAgeRange : String,
    val userGender: String
){
    constructor(): this(
        uniqueNumber = "",
        userNickname = "",
        userName = "",
        userEmail = "",
        userPhone = "",
        userAddress = "",
        userProfileImgPath = "",
        userAgeRange = "",
        userGender = ""
    )
}
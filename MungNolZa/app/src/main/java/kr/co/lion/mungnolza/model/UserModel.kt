package kr.co.lion.mungnolza.model

data class UserModel(
    var uniqueNumber: String,
    var userNickname: String,
    var userName: String,
    var userEmail: String,
    var userPhone: String,
    var userAddress: String,
    var userProfileImgPath: String,
    var userAgeRange : String,
    var userGender: String
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
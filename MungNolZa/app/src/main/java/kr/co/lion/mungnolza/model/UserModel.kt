package kr.co.lion.mungnolza.model

data class UserModel(

    var userIdx: Int,
    var userNickname: String,
    var userName: String,
    var userEmail: String,
    var userPhone: String,
    var userAddress: String,
    var userProfileImgPath: String,
    var userAgeRange: String,
    var userGender: String,
) {
    constructor() : this(
        userIdx = 0,
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
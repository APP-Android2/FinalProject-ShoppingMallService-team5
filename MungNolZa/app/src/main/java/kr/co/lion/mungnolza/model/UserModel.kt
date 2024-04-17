package kr.co.lion.mungnolza.model

data class UserModel(
    var userIdx:Int,
    var userId:String,
    var userPw:String,
    var userNickName:String,
    var userState:Int
)

data class KakaoUserModel(
    val userId: String,
    val email: String,
    val nickname: String,
    val profileImage: String
)

//data class User(
//    var uniqueNumber:String,
//    var userAgeRange:String,
//    var userEmail:String,
//    var userGender:String,
//    var userName:String,
//    var userNickname:String,
//    var userPhone:String,
//    var userProfileImgPath:String,
//)
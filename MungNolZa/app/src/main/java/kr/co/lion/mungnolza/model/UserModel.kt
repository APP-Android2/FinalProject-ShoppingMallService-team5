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
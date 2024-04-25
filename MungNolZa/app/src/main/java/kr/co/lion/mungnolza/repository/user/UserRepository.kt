package kr.co.lion.mungnolza.repository.user

import kr.co.lion.mungnolza.model.UserModel
import java.net.URI

interface UserRepository {
    suspend fun userJoin(userModel: UserModel)
    suspend fun fetchAllUserId() : List<String>
    suspend fun fetchAllUserData() : List<UserModel>
    suspend fun fetchUserAddress(uniqueNumber: String) : String
    suspend fun fetchUserNickName(uniqueNumber: String) : String
    suspend fun fetchUserProfileImage(path: String) : URI?
}
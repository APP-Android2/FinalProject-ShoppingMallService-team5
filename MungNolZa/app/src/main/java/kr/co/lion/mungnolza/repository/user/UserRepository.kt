package kr.co.lion.mungnolza.repository.user

import kr.co.lion.mungnolza.model.UserModel
import java.net.URI

interface UserRepository {
    suspend fun fetchAllUserData() : List<UserModel>
    suspend fun fetchAllUserNickName(uniqueNumber: String) : List<String>
    suspend fun fetchUserProfileImage(path: String) : URI?
}
package kr.co.lion.mungnolza.repository.user

import kr.co.lion.mungnolza.model.UserModel
import java.net.URI

interface UserRepository {
    suspend fun fetchAllUserData() : List<UserModel>
<<<<<<< HEAD
=======
    suspend fun fetchUserAddress(uniqueNumber: String) : String
>>>>>>> 0e73214e0419402e5d3d00412ff2905432afe7a8
    suspend fun fetchAllUserNickName(uniqueNumber: String) : List<String>
    suspend fun fetchUserProfileImage(path: String) : URI?
}
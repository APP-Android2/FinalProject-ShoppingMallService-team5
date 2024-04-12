package kr.co.lion.mungnolza.repository

import android.net.Uri
import kr.co.lion.mungnolza.model.UserModel

interface UserRepository {
    suspend fun fetchAllUserNickName(uniqueNumber: String) : String
    suspend fun fetchUserProfileImage(path: String) : Uri
}
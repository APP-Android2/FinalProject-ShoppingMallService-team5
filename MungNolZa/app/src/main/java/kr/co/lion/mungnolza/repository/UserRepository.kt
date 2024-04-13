package kr.co.lion.mungnolza.repository

import java.net.URI

interface UserRepository {
    suspend fun fetchAllUserNickName(uniqueNumber: String) : List<String>
    suspend fun fetchUserProfileImage(path: String) : URI?
}
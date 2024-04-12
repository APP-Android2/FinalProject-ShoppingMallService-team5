package kr.co.lion.mungnolza.data.repository

import kr.co.lion.mungnolza.model.UserModel

interface UserRepository {
    suspend fun getUserData(uniqueNumber:Int): UserModel?
}
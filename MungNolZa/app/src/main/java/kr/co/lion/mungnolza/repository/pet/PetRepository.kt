package kr.co.lion.mungnolza.repository.pet

import kotlinx.coroutines.flow.Flow
import kr.co.lion.mungnolza.datasource.local.MyPetEntity
import kr.co.lion.mungnolza.model.PetModel
import java.net.URI

interface PetRepository {
    suspend fun fetchMyPetData(ownerIdx: String): List<PetModel>
    suspend fun fetchMyPetImage(ownerIdx: String, imgName: String): URI
    suspend fun readMyPetData(): Flow<List<MyPetEntity>>
}
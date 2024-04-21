package kr.co.lion.mungnolza.repository.pet

import kr.co.lion.mungnolza.model.PetModel
import java.net.URI

interface PetRepository {
    suspend fun fetchMyPetData(ownerIdx: String): List<PetModel>
    suspend fun fetchMyPetImage(ownerIdx: String, imgName: String): URI?
}
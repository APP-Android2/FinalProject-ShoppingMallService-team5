package kr.co.lion.mungnolza.repository.petsitter

import kr.co.lion.mungnolza.model.PetSitterModel
import java.net.URI

interface PetSitterRepository {
    suspend fun fetchPetSitterData(petSitterIdx: String) : PetSitterModel
    suspend fun fetchAllPetSitterData() : List<PetSitterModel>
    suspend fun fetchPetSitterImage(petSitterIdx: String, imgName: String) : URI?
}

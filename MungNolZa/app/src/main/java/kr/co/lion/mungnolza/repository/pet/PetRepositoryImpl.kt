package kr.co.lion.mungnolza.repository.pet

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await
import kr.co.lion.mungnolza.model.PetModel
import java.net.URI

class PetRepositoryImpl: PetRepository {
    private val petStore = Firebase.firestore.collection("Pet")
    private val storage = Firebase.storage.reference
    override suspend fun fetchMyPetData(ownerIdx: String): List<PetModel> {
        return try {
            val querySnapshot = petStore.whereEqualTo("ownerIdx", ownerIdx).get().await()
            querySnapshot.map { it.toObject(PetModel::class.java) }
        }catch (e: Exception){
            Log.d("FirebaseResult", "Error fetching pet: ${e.message}")
            emptyList()
        }
    }

    override suspend fun fetchMyPetImage(userIdx: String, imgName: String): URI? {
        val path = "user/$userIdx/$imgName"

        return try {
            val response = storage.child(path).downloadUrl.await().toString()
            URI.create(response)
        }catch (e: Exception){
            Log.d("FirebaseResult", "Error fetching PetImage : ${storage.child(path)}")
            null
        }
    }

}
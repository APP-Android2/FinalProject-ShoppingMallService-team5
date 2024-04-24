package kr.co.lion.mungnolza.repository.petsitter

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kr.co.lion.mungnolza.model.PetSitterModel
import java.net.URI

class PetSitterRepositoryImpl(
    private val reference: CollectionReference,
    private val storage: StorageReference
) : PetSitterRepository {

    override suspend fun fetchAllPetSitterData(): List<PetSitterModel> {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot = reference.get().await()
                querySnapshot.map { it.toObject(PetSitterModel::class.java) }
            } catch (e: Exception) {
                Log.e("FirebaseResult", "Error fetching PetSitterData: ${e.message}")
                emptyList()
            }
        }
    }

    override suspend fun fetchPetSitterImage(petSitterIdx: String, imgName: String): URI {
        val path = "petSitter/$petSitterIdx/$imgName"
        return withContext(Dispatchers.IO) {
            try {
                val response = storage.child(path).downloadUrl.await().toString()
                URI.create(response)
            } catch (e: Exception) {
                Log.e("FirebaseResult", "Error fetching PetSitterImage : ${storage.child(path)}")
                URI.create("")
            }
        }
    }
}
package kr.co.lion.mungnolza.repository.petsitter

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kr.co.lion.mungnolza.model.PetSitterModel
import java.net.URI

class PetSitterRepositoryImpl : PetSitterRepository {
    private val petSitterStore = Firebase.firestore.collection("Petsitter")
    private val storage = Firebase.storage.reference
    override suspend fun fetchAllPetSitterData(): List<PetSitterModel> {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot = petSitterStore.get().await()
                querySnapshot.map { it.toObject(PetSitterModel::class.java) }
            } catch (e: Exception) {
                Log.e("FirebaseResult", "Error fetching users: ${e.message}")
                emptyList()
            }
        }
    }

    override suspend fun fetchPetSitterImage(petSitterIdx: String, imgName: String): URI? {
        val path = "petSitter/$petSitterIdx/$imgName"
        return withContext(Dispatchers.IO) {
            try {
                val response = storage.child(path).downloadUrl.await().toString()
                URI.create(response)
            } catch (e: Exception) {
                Log.e("FirebaseResult", "Error fetching UserProfileImage : ${storage.child(path)}")
                null
            }
        }
    }
}
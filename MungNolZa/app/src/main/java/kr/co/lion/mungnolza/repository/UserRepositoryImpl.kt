package kr.co.lion.mungnolza.repository

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await
import java.net.URI

class UserRepositoryImpl : UserRepository {
    private val userStore = Firebase.firestore.collection("User")
    private val storage = Firebase.storage.reference
    override suspend fun fetchAllUserNickName(uniqueNumber: String): List<String> {
        return try {
            val querySnapshot = userStore.whereEqualTo("uniqueNumber", uniqueNumber).get().await()

            querySnapshot.map { it.getString("userNickname").toString() }

        } catch (e: Exception) {
            Log.e("FirebaseResult", "Error fetching users: ${e.message}")
            emptyList()
        }
    }

    override suspend fun fetchUserProfileImage(path: String): URI? {
        var result: URI? = null
        try {
            val response = storage.child(path).downloadUrl.await().toString()
            result = URI.create(response)
        }catch (e: Exception){
            Log.e("FirebaseResult", "Error fetching UserProfileImage : ${storage.child(path)}")
        }
        return result
    }

}
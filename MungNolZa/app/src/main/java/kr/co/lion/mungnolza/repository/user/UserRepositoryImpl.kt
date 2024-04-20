package kr.co.lion.mungnolza.repository.user

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kr.co.lion.mungnolza.model.UserModel
import java.net.URI
class UserRepositoryImpl : UserRepository {
    private val userStore = Firebase.firestore.collection("User")
    private val storage = Firebase.storage.reference
    override suspend fun fetchAllUserData(): List<UserModel> {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot = userStore.get().await()
                querySnapshot.map { it.toObject(UserModel::class.java) }
            } catch (e: Exception) {
                Log.e("FirebaseResult", "Error fetching users: ${e.message}")
                emptyList()
            }
        }
    }

    override suspend fun fetchUserAddress(uniqueNumber: String): String {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot =
                    userStore.whereEqualTo("uniqueNumber", uniqueNumber).get().await()
                querySnapshot.documents.first().getString("userAddress").toString()

            } catch (e: Exception) {
                Log.e("FirebaseResult", "Error fetching users: ${e.message}")
                ""
            }
        }
    }

    override suspend fun fetchAllUserNickName(uniqueNumber: String): List<String> {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot = userStore.whereEqualTo("uniqueNumber", uniqueNumber).get().await()

                querySnapshot.map { it.getString("userNickname").toString() }

            } catch (e: Exception) {
                Log.e("FirebaseResult", "Error fetching users: ${e.message}")
                emptyList()
            }
        }
    }

    override suspend fun fetchUserProfileImage(path: String): URI? {
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
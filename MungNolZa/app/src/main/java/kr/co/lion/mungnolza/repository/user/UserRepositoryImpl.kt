package kr.co.lion.mungnolza.repository.user

import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl : UserRepository {
    private val userStore = Firebase.firestore.collection("User")
    private val storage = Firebase.storage.reference
    override suspend fun fetchAllUserNickName(uniqueNumber: String): String {
        var nickName = ""
        try {
            val querySnapshot = userStore.whereEqualTo("uniqueNumber", uniqueNumber).get().await()
            for (document in querySnapshot) {
                nickName = document.getString("userNickname").toString()
            }
        } catch (e: Exception) {
            Log.e("FirebaseResult", "Error fetching users: ${e.message}")
        }

        return nickName
    }

    override suspend fun fetchUserAddress(uniqueNumber: String): String {
        return try {
            val querySnapshot = userStore.document(uniqueNumber).get().await()
            val result = querySnapshot.getString("userAddress").toString()
            result
        }catch (e: Exception){
            Log.e("FirebaseResult", "Error fetching users: ${e.message}")
            ""
        }
    }

    override suspend fun fetchUserProfileImage(path: String): Uri {
        return storage.child(path).downloadUrl.await()
    }

}
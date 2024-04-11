package kr.co.lion.mungnolza.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await
import kr.co.lion.mungnolza.model.UserModel

class UserRepositoryImpl : UserRepository {
    private val store: FirebaseFirestore = Firebase.firestore
    private val storage = Firebase.storage.reference
    override suspend fun fetchAllUser(): ArrayList<UserModel> {
        val userList = ArrayList<UserModel>()

        try {
            val querySnapshot = store.collection("User").get().await()
            for (document in querySnapshot) {
                val user = document.toObject(UserModel::class.java)
                Log.e("FirebaseResult", "Error fetching users: ${user}")

                userList.add(user)
            }
        } catch (e: Exception) {
            Log.e("FirebaseResult", "Error fetching users: ${e.message}")
        }

        return userList
    }

    override suspend fun fetchUserProfileImage(path: String): Uri {
        return storage.child(path).downloadUrl.await()
    }

}
package kr.co.lion.mungnolza.repository

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kr.co.lion.mungnolza.model.UserModel

interface UserRepository {
    suspend fun fetchAllUser() : ArrayList<UserModel>
    suspend fun fetchUserProfileImage(path: String) : Uri
}
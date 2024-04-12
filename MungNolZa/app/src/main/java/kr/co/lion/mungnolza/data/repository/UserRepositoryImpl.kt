package kr.co.lion.mungnolza.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.mungnolza.model.UserModel

class UserRepositoryImpl : UserRepository {

    override suspend fun getUserData(uniqueNumber: Int): UserModel? {
        var userModel: UserModel? = null

        val job = CoroutineScope(Dispatchers.IO).launch {
            val collectionReference = Firebase.firestore.collection("User")
            val querySnapshot =
                collectionReference.whereEqualTo("uniqueNumber", uniqueNumber).get().await()

            if (querySnapshot.isEmpty == false) {
                userModel = querySnapshot.documents[0].toObject(UserModel::class.java)
            }
        }
        job.join()
        return userModel
    }
}
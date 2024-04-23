package kr.co.lion.mungnolza.repository.pet

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kr.co.lion.mungnolza.datasource.local.MyPetDao
import kr.co.lion.mungnolza.datasource.local.MyPetEntity
import kr.co.lion.mungnolza.model.PetModel
import java.net.URI

class PetRepositoryImpl(private val myPetDao: MyPetDao): PetRepository {
    private val petStore = Firebase.firestore.collection("Pet")
    private val storage = Firebase.storage.reference

    suspend fun getMyPetData(): Flow<List<MyPetEntity>> {
        return withContext(Dispatchers.IO){
            myPetDao.readMyPet()
        }
    }

    suspend fun insertMyPetData(myPets: MyPetEntity){
        withContext(Dispatchers.IO){
            myPetDao.insertMyPet(myPets)
        }
    }

    suspend fun updateMyPetData(myPets: MyPetEntity){
        withContext(Dispatchers.IO){
            myPetDao.updateMyPet(myPets)
        }
    }

    suspend fun deleteMyPetData(petName: String){
        withContext(Dispatchers.IO){
            myPetDao.deleteMyPet(petName)
        }
    }

    override suspend fun fetchMyPetData(ownerIdx: String): List<PetModel> {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot = petStore.whereEqualTo("ownerIdx", ownerIdx).get().await()
                querySnapshot.map { it.toObject(PetModel::class.java) }

            } catch (e: Exception) {
                Log.d("FirebaseResult", "Error fetching pet: ${e.message}")
                emptyList()
            }
        }
    }
    override suspend fun fetchMyPetImage(ownerIdx: String, imgName: String): URI {
        val path = "pet/$ownerIdx/$imgName"

        return withContext(Dispatchers.IO) {
            try {
                val response = storage.child(path).downloadUrl.await().toString()
                URI.create(response)
            } catch (e: Exception) {
                Log.d("FirebaseResult", "Error fetching PetImage : ${e.message}")
                URI.create("")
            }
        }
    }
}
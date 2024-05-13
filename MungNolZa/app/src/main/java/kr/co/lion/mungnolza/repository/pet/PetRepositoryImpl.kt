package kr.co.lion.mungnolza.repository.pet

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kr.co.lion.mungnolza.datasource.local.MyPetDao
import kr.co.lion.mungnolza.datasource.local.MyPetEntity
import kr.co.lion.mungnolza.model.PetModel
import java.net.URI

class PetRepositoryImpl(
    private val reference: CollectionReference,
    private val storage: StorageReference,
    private val myPetDao: MyPetDao
): PetRepository {

    override suspend fun readMyPetData(): Flow<List<MyPetEntity>> {
        return withContext(Dispatchers.IO){
            myPetDao.readMyPet()
        }
    }

    override suspend fun insertMyPetData(myPets: MyPetEntity){
        withContext(Dispatchers.IO){
            myPetDao.insertMyPet(myPets)
        }
    }

    override suspend fun updateMyPetData(myPets: MyPetEntity){
        withContext(Dispatchers.IO){
            myPetDao.updateMyPet(myPets)
        }
    }

    override suspend fun deleteMyPetData(petName: String){
        withContext(Dispatchers.IO){
            myPetDao.deleteMyPet(petName)
        }
    }

    override suspend fun fetchMyPetData(ownerIdx: String): List<PetModel> {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot = reference.whereEqualTo("ownerIdx", ownerIdx).get().await()
                querySnapshot.map { it.toObject(PetModel::class.java) }

            } catch (e: Exception) {
                Log.d("FirebaseResult", "Error fetching pet: ${e.message}")
                emptyList()
            }
        }
    }
    override suspend fun fetchMyPetImage(ownerIdx: String, imgName: String): URI {

        return withContext(Dispatchers.IO) {
            try {
                val path = "pet/$ownerIdx/$imgName"
                val response = storage.child(path).downloadUrl.await().toString()
                URI.create(response)
            } catch (e: Exception) {
                Log.d("FirebaseResult", "Error fetching PetImage : ${e.message}")
                URI.create("")
            }
        }
    }
}
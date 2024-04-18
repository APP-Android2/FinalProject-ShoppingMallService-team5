package kr.co.lion.mungnolza.repository.reservation

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kr.co.lion.mungnolza.model.CareServiceModel
import kr.co.lion.mungnolza.model.WalkServiceModel

class ReservationRepositoryImpl: ReservationRepository {
    private val userStore = Firebase.firestore.collection("Reservation")

    override suspend fun findLastReservationIdx(): Long {
        return withContext(Dispatchers.IO){
            try{
                val querySnapshot = userStore.get().await()
                val boardIdxList = querySnapshot.mapNotNull { document ->
                    document.getLong("reserveIdx")
                }
                if (boardIdxList.isEmpty()) 0L
                else boardIdxList.maxOrNull() ?: 0L
            }catch (e: Exception){
                Log.e("FirebaseResult", "Error find ReservationIdx: ${e.message}")
                0L
            }
        }
    }

    override suspend fun walkServiceRequest(request: WalkServiceModel) {
        userStore.document("${request.reserveIdx}")
            .set(request).addOnSuccessListener {
                Log.d("FirebaseResult","Success walkServiceRequest")
        }
        .addOnFailureListener {
                Log.e("FirebaseResult", "Error walkServiceRequest: ${it.message}")
        }
    }

    override suspend fun careServiceRequest(request: CareServiceModel) {
        userStore.document("${request.reserveIdx}")
            .set(request).addOnSuccessListener {
            Log.d("FirebaseResult","Success careServiceRequest")
        }
        .addOnFailureListener {
            Log.e("FirebaseResult", "Error walkServiceRequest: ${it.message}")
        }
    }
}
package kr.co.lion.mungnolza.repository.review

import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kr.co.lion.mungnolza.model.PetsitterReviewModel

class ReviewRepositoryImpl(
    private val reference: CollectionReference
): ReviewRepository {
    override suspend fun fetchPetSitterReview(petSitterIdx: Int): List<PetsitterReviewModel> {
        return withContext(Dispatchers.IO){
            val response = reference.whereEqualTo("petSitterIdx", petSitterIdx).get().await()
            response.map { it.toObject(PetsitterReviewModel::class.java) }
        }
    }

}
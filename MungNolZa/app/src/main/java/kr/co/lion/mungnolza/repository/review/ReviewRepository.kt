package kr.co.lion.mungnolza.repository.review

import kr.co.lion.mungnolza.model.PetsitterReviewModel

interface ReviewRepository{
    suspend fun fetchPetSitterReview(petSitterIdx: Int): List<PetsitterReviewModel>
}
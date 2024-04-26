package kr.co.lion.mungnolza.model

data class PetSitterWithReviewModel (
    val petSitterInfo : PetSitterModelWithImg,
    val review: List<PetsitterReviewModel>
)
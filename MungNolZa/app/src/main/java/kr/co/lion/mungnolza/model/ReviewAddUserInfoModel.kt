package kr.co.lion.mungnolza.model

import java.net.URI

data class ReviewAddUserInfoModel(
    val review: PetsitterReviewModel,
    val imgUri: URI
)
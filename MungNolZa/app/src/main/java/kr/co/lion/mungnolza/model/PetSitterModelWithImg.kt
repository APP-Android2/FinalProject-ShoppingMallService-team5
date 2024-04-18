package kr.co.lion.mungnolza.model

import java.net.URI

data class PetSitterModelWithImg (
    val petSitter: PetSitterModel,
    val profileImg: URI
)
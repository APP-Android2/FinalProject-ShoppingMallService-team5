package kr.co.lion.mungnolza.model

data class LocationRequestModel (
    val userIdx: String,
    val petSitterIdx: String,
    val reservationIdx: String,
    val location: Location
)
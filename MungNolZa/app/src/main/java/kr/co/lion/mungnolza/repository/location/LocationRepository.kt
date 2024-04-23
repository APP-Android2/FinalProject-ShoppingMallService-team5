package kr.co.lion.mungnolza.repository.location

import kr.co.lion.mungnolza.model.Location
import kr.co.lion.mungnolza.model.LocationRequestModel

interface LocationRepository {
    suspend fun insertLocation(locationRequest: LocationRequestModel)

    suspend fun readCurrentLocation(reservationIdx: String): List<Location>
}
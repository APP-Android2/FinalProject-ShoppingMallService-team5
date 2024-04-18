package kr.co.lion.mungnolza.repository.reservation

import kr.co.lion.mungnolza.model.CareServiceModel
import kr.co.lion.mungnolza.model.WalkServiceModel

interface ReservationRepository {
    suspend fun findLastReservationIdx(): Long

    suspend fun walkServiceRequest(request: WalkServiceModel)

    suspend fun careServiceRequest(request: CareServiceModel)
}
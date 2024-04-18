package kr.co.lion.mungnolza.model

data class WalkServiceModel (
    val userIdx: String = "",
    val reserveIdx: Long = 0L,
    val selectService: SelectScheduleModel = SelectScheduleModel(),
    val petSitterIdx: String = "",
    val reservationCompleteDate: String = "",
    val state: Boolean = true
)
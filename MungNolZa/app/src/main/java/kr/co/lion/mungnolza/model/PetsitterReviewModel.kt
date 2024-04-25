package kr.co.lion.mungnolza.model

data class PetsitterReviewModel(
    // 후기글번호 → reviewIdx
    // 펫시터 번호 → petsitterIdx
    // 사용자(작성자) 번호 → reviewWriterIdx
    // 사용자 이름 → reviewWriterName
    // 작성날짜 → reviewWriteDate
    // 별점 → reviewStarCount
    // 후기내용 → reviewText

    val reviewIdx: Int = 0,
    val petSitterIdx : Int = 0,
    val reviewWriterIdx : String = "",
    val reviewWriterName : String = "",
    val reviewWriteDate : String = "",
    val reviewStarCount: Float = 0F,
    val reviewText: String = ""
)
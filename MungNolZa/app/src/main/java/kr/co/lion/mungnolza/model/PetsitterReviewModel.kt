package kr.co.lion.mungnolza.model

data class PetsitterReviewModel(
    // 후기글번호 → reviewIdx
    // 펫시터 번호 → petsitterIdx
    // 사용자(작성자) 번호 → reviewWriterIdx
    // 사용자 이름 → reviewWriterName
    // 작성날짜 → reviewWriteDate
    // 별점 → reviewStarCount
    // 후기내용 → reviewText

    var reviewIdx: Int,
    /*var petsitterIdx : Int,
    var reviewWriterIdx : Int,
    var reviewWriterName : String,*/
    var reviewWriteDate : String,
    var reviewStarCount: Float?,
    var reviewText: String
)




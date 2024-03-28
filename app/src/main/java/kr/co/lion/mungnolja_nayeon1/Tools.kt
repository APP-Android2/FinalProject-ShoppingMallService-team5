package kr.co.lion.mungnolja_nayeon1

class Tools {
    companion object{

    }
}

// MainFragment 에서 보여줄 프래그먼트들의 이름
enum class MainFragmentName(var str:String){
    // 매칭화면
    MATCHING_FRAGMENT("MatchingFragment"),
    // 펫시터 프로필 화면
    PETSITTER_INFO_FRAGMENT("PetSitterInfoFragment"),
    // 펫시터 리뷰 목록 화면
    PETSITTER_REVIEW_FRAGMENT("PetSitterReviewFragment"),
    // 결제 화면
    PAYMENT_FRAGMENT("PaymentFragment"),
    // 예약 확정 화면
    RESERVATION_CONFIRMED_FRAGMENT("ReservationConfirmedFragment"),
}
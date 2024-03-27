package kr.co.lion.mungnolja_nayeon1

class Tools {
    companion object{

    }
}

// MainFragment 에서 보여줄 프래그먼트들의 이름
enum class MainFragmentName(var str:String){
    MATCHING_FRAGMENT("MatchingFragment"),
    PETSITTER_INFO_FRAGMENT("PetSitterInfoFragment"),
    PETSITTER_REVIEW_FRAGMENT("PetSitterReviewFragment"),
}
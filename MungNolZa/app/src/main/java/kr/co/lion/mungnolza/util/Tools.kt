package kr.co.lion.mungnolza.util

import android.Manifest
import android.os.Build

object Tools {
    val REQUEST_IMAGE_PERMISSIONS by lazy{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    val REQUEST_LOCATION_PERMISSIONS by lazy {
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    }
}


// MainFragment 에서 보여줄 프래그먼트들의 이름
enum class MatchingPetsitterFragmentName(var str:String){
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

enum class ReservationListFragmentName(var str:String){
    // 상위-예약 목록 화면
    RESERVATION_LIST_FRAGMENT("ReservationListFragment"),
    // 펫시터 후기 작성 화면
    PETSITTER_REVIEW_WRITE_FRAGMENT("PetSitterReviewWriteFragment"),
}

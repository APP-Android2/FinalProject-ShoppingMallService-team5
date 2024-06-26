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
    val ADDR_RESULT_CODE by lazy { 1002 }
}


enum class ReservationListFragmentName(var str:String){
    // 상위-예약 목록 화면
    RESERVATION_LIST_FRAGMENT("ReservationListFragment"),
    // 펫시터 후기 작성 화면
    PETSITTER_REVIEW_WRITE_FRAGMENT("PetSitterReviewWriteFragment"),
}

/*enum class PetsitterMainFragmentsName(var str:String) {
    // 펫시터 전용 홈화면
    PETSITTER_HOME_FRAGMEMT("PetsitterHomeFragment"),

    // 펫시터 전용 예약목록화면
    PETSITTER_RESERVATION_LIST_FRAGMENT("PetsitterReservationListFragmnet"),
}*/

enum class Week(val day: String){
    SUNDAY("sunday"),
    MONDAY("monday"),
    TUESDAY("tuesday"),
    WEDNESDAY("wednesday"),
    THURSDAY("thursday"),
    FRIDAY("friday"),
    SATURDAY("saturday")
}

enum class VisitType(val type: String) {
    COMMON_VISIT("common visit"),
    REGULAR_VISIT("regular visit"),
}

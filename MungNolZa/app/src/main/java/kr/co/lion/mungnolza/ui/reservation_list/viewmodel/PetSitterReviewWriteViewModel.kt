package kr.co.lion.mungnolza.ui.reservation_list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// 펫시터 후기 작성

class PetSitterReviewWriteViewModel : ViewModel() {
    // 펫시터 이름
    // 펫시터 프로필 사진
    // 펫시터 별점 (숫자)
    // 펫시터 별점 (래이팅바)
    // 후기 개수

    // 별점 → starCount
    val ratingBar2 = MutableLiveData<Float>()
    // 후기내용 → ReviewText
    val textfieldPetsitterReviewWrite = MutableLiveData<String>()
}
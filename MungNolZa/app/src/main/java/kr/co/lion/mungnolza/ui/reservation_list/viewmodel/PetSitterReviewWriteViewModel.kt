package kr.co.lion.mungnolza.ui.reservation_list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// 펫시터 후기 작성

class PetSitterReviewWriteViewModel : ViewModel() {
    // 별점 → starCount
    val ratingBar2 = MutableLiveData<Float>()
    // 후기내용 → ReviewText
    val textfieldPetsitterReviewWrite = MutableLiveData<String>()
}
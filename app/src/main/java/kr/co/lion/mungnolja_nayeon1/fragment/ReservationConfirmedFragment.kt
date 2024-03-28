package kr.co.lion.mungnolja_nayeon1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.mungnolja_nayeon1.MainActivity
import kr.co.lion.mungnolja_nayeon1.MainFragmentName
import kr.co.lion.mungnolja_nayeon1.R
import kr.co.lion.mungnolja_nayeon1.databinding.FragmentReservationConfirmedBinding

class ReservationConfirmedFragment : Fragment() {

    lateinit var fragmentReservationConfirmedBinding: FragmentReservationConfirmedBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentReservationConfirmedBinding = FragmentReservationConfirmedBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        setToolbar()
        setCardMatching()

        return fragmentReservationConfirmedBinding.root
    }

    // 툴바 설정
    fun setToolbar(){
        fragmentReservationConfirmedBinding.apply {
            toolbarReservationConfirmed.apply {
                // 타이틀
                title = "예약 확인"
                // 뒤로가기
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainFragmentName.RESERVATION_CONFIRMED_FRAGMENT)
                }
            }
        }
    }

    // cardMatchingReservationDetail의 내용을 설정해준다
    fun setCardMatching(){
        fragmentReservationConfirmedBinding.apply {
            // 펫 사진
            imageViewReservationConfirmedPet.setImageResource(R.drawable.nayeon)
            // 돌봄 서비스 유형 및 시간
            textViewReservationConfirmedPetCareType.setText("돌봄 30분")
            // 펫 이름
            textViewReservationConfirmedPetName.setText("뽀삐나연")
            // 예약 주소
            textViewReservationConfirmedAddress.setText("서울특별시 사랑구 행복동")

            // 펫시터 사진
            imageViewReservationConfirmedPetSitter.setImageResource(R.drawable.petsitter)
            // 펫시터 이름
            textViewReservationConfirmedPetSitterName.setText("이영주 시터")
            // 펫시터 별점
            ratingBarReservationConfirmedPetSitter.rating = 5f
            // 펫시터 경력
            textViewReservationConfirmedPetSitterCareer.setText("경력")
            // 예약 날짜-시간
            textViewReservationConfirmedDateTime.setText("3월29일 오후5:00-5:30")
        }
    }
}
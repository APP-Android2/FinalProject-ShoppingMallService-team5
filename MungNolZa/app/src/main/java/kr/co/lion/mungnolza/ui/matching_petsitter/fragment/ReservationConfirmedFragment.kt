package kr.co.lion.mungnolza.ui.matching_petsitter.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentReservationConfirmedBinding
import kr.co.lion.mungnolza.ui.main.MainActivity
import kr.co.lion.mungnolza.ui.matching_petsitter.MatchingPetsitterActivity
import kr.co.lion.mungnolza.util.MatchingPetsitterFragmentName

class ReservationConfirmedFragment : Fragment() {

    private var _binding: FragmentReservationConfirmedBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentReservationConfirmedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setCardMatching()
    }

    private fun initView(){
        binding.btnNext.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            activity?.finish()
        }
    }

    // cardMatchingReservationDetail의 내용을 설정해준다
    private fun setCardMatching(){
        with(binding) {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package kr.co.lion.mungnolza.ui.reservation_list.fragment

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.mungnolza.ui.reservation_list.ReservationListActivity
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetSitterReviewWriteBinding
import kr.co.lion.mungnolza.util.ReservationListFragmentName

class PetSitterReviewWriteFragment : Fragment() {

    lateinit var fragmentPetSitterReviewWriteBinding: FragmentPetSitterReviewWriteBinding
    lateinit var reservationListActivity: ReservationListActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentPetSitterReviewWriteBinding = FragmentPetSitterReviewWriteBinding.inflate(layoutInflater)
        reservationListActivity = activity as ReservationListActivity

        setToolbar()
        setEvent()
        initData()

        return fragmentPetSitterReviewWriteBinding.root
    }

    // 툴바 설정
    fun setToolbar(){
        fragmentPetSitterReviewWriteBinding.apply {
            toolbarPetSitterReviewWrite.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                setNavigationOnClickListener {
                    // PetSitterReviewWriteFragment를 사라지게 한다.
                    reservationListActivity.removeFragment(ReservationListFragmentName.PETSITTER_REVIEW_WRITE_FRAGMENT)
                }
            }
        }
    }

    // 이벤트 설정
    fun setEvent(){
        // buttonPetsitterReviewWriteDone 작성완료 버튼 설정
        fragmentPetSitterReviewWriteBinding.apply {
            buttonPetsitterReviewWriteDone.apply {
                // 버튼을 눌럿을 때
                setOnClickListener {
                    // 작성완료 다이어로그를 띄운다
                    val materialAlertDialogBuilder = MaterialAlertDialogBuilder(reservationListActivity)
                    materialAlertDialogBuilder.setTitle("작성 완료")
                    materialAlertDialogBuilder.setMessage("후기 작성이 완료되었습니다 !")
                    materialAlertDialogBuilder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                        // 메인화면으로 넘어간다.
                    }
                    materialAlertDialogBuilder.show()
                }
            }
        }
    }

    // 초기 데이터 셋팅
    fun initData(){
        fragmentPetSitterReviewWriteBinding.apply {
            imageViewPetSitterReviewWrite.setImageResource(R.drawable.petsitter)
            textViewPetSitterReviewWriteName.setText("이영주 펫시터")
            textViewPetSitterReviewWriteScore.setText("5점")
            ratingBarPetSitterReviewWrite.rating = 5f
            textViewPetSitterReviewWriteCountReview.setText("89개의 후기")
        }
    }
}
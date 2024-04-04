package kr.co.lion.mungnolza.ui.matching_petsitter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetSitterInfoBinding
import kr.co.lion.mungnolza.ui.matching_petsitter.MatchingPetsitterActivity
import kr.co.lion.mungnolza.ui.matching_petsitter.MatchingPetsitterFragmentName

class PetSitterInfoFragment : Fragment() {

    lateinit var fragmentPetSitterInfoBinding: FragmentPetSitterInfoBinding
    lateinit var matchingPetsitterActivity: MatchingPetsitterActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentPetSitterInfoBinding = FragmentPetSitterInfoBinding.inflate(layoutInflater)
        matchingPetsitterActivity = activity as MatchingPetsitterActivity

        setToolbar()
        setReviewButton()
        setCardPetSitterInfo()

        return fragmentPetSitterInfoBinding.root
    }

    // 툴바 설정
    fun setToolbar(){
        fragmentPetSitterInfoBinding.apply {
            toolbarPetSitterInfo.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                setNavigationOnClickListener {
                    // 코드 합치면 그때 작성할 것
                    matchingPetsitterActivity.removeFragment(MatchingPetsitterFragmentName.PETSITTER_INFO_FRAGMENT)
                }
            }
        }
    }

    // cardPetSitterInfo의 내용을 설정해준다
    fun setCardPetSitterInfo(){
        fragmentPetSitterInfoBinding.apply {
            // 펫시터 이름
            textViewPetSitterInfoName.setText("이영주 시터")
            // 펫시터 멍놀자 자격수료
            textViewPetSitterQualification.setText("멍놀자 펫시터 자격 수료")
            // 별 점수
            textViewPetSitterInfoScore.setText("5점")
            // 별 개수
            ratingBarPetSitterInfo.rating = 5f
            // 리뷰 개수 (ex, 00개의 후기)
            textViewPetSitterInfoCountReview.setText("89개의 후기")
            // 경력
            textViewPetSitterInfoCareerDetail.setText("반려동물관리학과 졸업 \n반려동물관리사 자격증 보유")
        }
    }

    // 리뷰 보기 버튼
    fun setReviewButton(){
        fragmentPetSitterInfoBinding.apply {
            // imageViewPetSitterInfoReviewButton 이미지 버튼 클릭시
            imageViewPetSitterInfoReviewButton.setOnClickListener {
                // PetSitterReviewFragment 가 보여지게 한다
                matchingPetsitterActivity.replaceFragment(MatchingPetsitterFragmentName.PETSITTER_REVIEW_FRAGMENT,true,true,null)
            }
        }
    }
}
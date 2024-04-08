package kr.co.lion.mungnolza.ui.matching_petsitter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetSitterReviewBinding
import kr.co.lion.mungnolza.databinding.RowPetSitterReviewBinding
import kr.co.lion.mungnolza.ui.matching_petsitter.MatchingPetsitterActivity
import kr.co.lion.mungnolza.util.MatchingPetsitterFragmentName

class PetSitterReviewFragment : Fragment() {

    lateinit var fragmentPetSitterReViewBinding : FragmentPetSitterReviewBinding
    lateinit var matchingPetsitterActivity: MatchingPetsitterActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentPetSitterReViewBinding = FragmentPetSitterReviewBinding.inflate(layoutInflater)
        matchingPetsitterActivity = activity as MatchingPetsitterActivity

        setToolbar()
        setReviewInfo()
        setRecyclerView()

        return fragmentPetSitterReViewBinding.root
    }

    // 툴바 설정
    fun setToolbar(){
        fragmentPetSitterReViewBinding.apply {
            toolbarPetSitterReview.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                setNavigationOnClickListener {
                    // PetSiiterInfoFragment 로 돌아가기
                    matchingPetsitterActivity.removeFragment(MatchingPetsitterFragmentName.PETSITTER_REVIEW_FRAGMENT)
                }
            }
        }
    }

    // 상단의 ReviewInfo 내용을 설정해준다
    fun setReviewInfo(){
        fragmentPetSitterReViewBinding.apply {
            // 점수
            textViewPetSitterReviewScore.setText("5.0점")
            // 별 개수
            ratingBarPetSitterReview.rating = 5f
            // 리뷰 개수 (ex, 00개의 후기)
            textViewPetSitterReviewCount.setText("89개의 후기")
        }
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        fragmentPetSitterReViewBinding.apply {
            recyclerPetSitterReview.apply {
                // 어뎁터 설정
                adapter = RecyclerMainAdapter()
                // 레이아웃
                layoutManager = LinearLayoutManager(matchingPetsitterActivity)
                // 데코
                val deco = MaterialDividerItemDecoration(matchingPetsitterActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerMainAdapter : RecyclerView.Adapter<RecyclerMainAdapter.RecyclerMainViewHolder>(){
        inner class RecyclerMainViewHolder(rowPetSitterReviewBinding: RowPetSitterReviewBinding) : RecyclerView.ViewHolder(rowPetSitterReviewBinding.root){
            val rowPetSitterReviewBinding : RowPetSitterReviewBinding

            init {
                this.rowPetSitterReviewBinding = rowPetSitterReviewBinding
                this.rowPetSitterReviewBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerMainViewHolder {
            val rowPetSitterReviewBinding = RowPetSitterReviewBinding.inflate(layoutInflater)
            val recyclerMainViewHolder = RecyclerMainViewHolder(rowPetSitterReviewBinding)

            return recyclerMainViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: RecyclerMainViewHolder, position: Int) {
            holder.rowPetSitterReviewBinding.imageViewRowPetSitterReviewWriter.setImageResource(R.drawable.eunwoo)
            holder.rowPetSitterReviewBinding.textViewRowPetSitterReviewWriterName.text = "안영준 $position"
            holder.rowPetSitterReviewBinding.textViewRowPetSitterReviewAgoTime.text = "3분 전"
            holder.rowPetSitterReviewBinding.textViewRowPetSitterReviewScore.text = "5점"
            holder.rowPetSitterReviewBinding.ratingBarRowPetSitterReview.rating = 5f
            holder.rowPetSitterReviewBinding.textViewRowPetSitterReviewDetail.text = "안녕하세요. 멋사 차은우 입니다. 족구미가 귀여워요"
        }
    }

}
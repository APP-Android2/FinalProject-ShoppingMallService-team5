package kr.co.lion.mungnolja_nayeon1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolja_nayeon1.MainActivity
import kr.co.lion.mungnolja_nayeon1.MainFragmentName
import kr.co.lion.mungnolja_nayeon1.R
import kr.co.lion.mungnolja_nayeon1.databinding.FragmentPetSitterReviewBinding
import kr.co.lion.mungnolja_nayeon1.databinding.RowPetSitterReviewBinding

class PetSitterReviewFragment : Fragment() {

    lateinit var fragmentPetSitterReViewBinding : FragmentPetSitterReviewBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentPetSitterReViewBinding = FragmentPetSitterReviewBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        setToolbar()
        setReviewInfo()
        setRecyclerView()

        return fragmentPetSitterReViewBinding.root
    }

    // 툴바 설정
    fun setToolbar(){
        fragmentPetSitterReViewBinding.apply {
            toolbarPetSitterReview.apply {
                // 타이틀
                title = "후기"
                // 뒤로가기
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    // PetSiiterInfoFragment 로 돌아가기
                    mainActivity.removeFragment(MainFragmentName.PETSITTER_REVIEW_FRAGMENT)
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
                layoutManager = LinearLayoutManager(mainActivity)
                // 데코
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
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
            holder.rowPetSitterReviewBinding.textViewRowPetSitterReviewDetail.text = "저희 뽀삐가 너무 즐거워했어요. 최고의 펫시터!"
        }
    }

}
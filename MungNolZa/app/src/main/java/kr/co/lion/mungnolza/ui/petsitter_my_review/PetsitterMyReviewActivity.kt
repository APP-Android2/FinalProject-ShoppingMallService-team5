package kr.co.lion.mungnolza.ui.petsitter_my_review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityPetsitterMyReviewBinding
import kr.co.lion.mungnolza.ui.petsitter_my_review.adapter.PetsitterMyReviewAdapter
import kr.co.lion.mungnolza.ui.petsitter_reservation_list.fragment.PetsitterReservationListLastAdapter

class PetsitterMyReviewActivity : AppCompatActivity() {

    lateinit var activityPetsitterMyReviewBinding: ActivityPetsitterMyReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPetsitterMyReviewBinding = ActivityPetsitterMyReviewBinding.inflate(layoutInflater)
        setContentView(activityPetsitterMyReviewBinding.root)

        setToolbar()
        setRecyclerView()
    }

    // 툴바를 설정한다
    fun setToolbar(){
        activityPetsitterMyReviewBinding.apply {
            toolbar.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.ic_arrow_back_24)
                setNavigationOnClickListener {
                    // 백버튼 클릭 이벤트
                    this@PetsitterMyReviewActivity.finish()
                }
            }
        }
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        activityPetsitterMyReviewBinding.apply {
            recyclerViewPetsitterMyReview.apply {
                // 어뎁터 설정
                adapter = PetsitterMyReviewAdapter()
                // 레이아웃
                layoutManager = LinearLayoutManager(this@PetsitterMyReviewActivity)
                // 데코
                val deco = MaterialDividerItemDecoration(this@PetsitterMyReviewActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
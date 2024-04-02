package kr.co.lion.mungnolja_nayeon1.fragment

import android.graphics.Color
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
import kr.co.lion.mungnolja_nayeon1.databinding.FragmentMatchingBinding
import kr.co.lion.mungnolja_nayeon1.databinding.RowMatchingBinding

class MatchingFragment : Fragment() {

    lateinit var fragmentMatchingBinding: FragmentMatchingBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentMatchingBinding = FragmentMatchingBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        setToolbar()
        setButtonMatching()
        setCardMatching()
        setRecyclerView()

        return fragmentMatchingBinding.root
    }

    // 툴 바 설정
    fun setToolbar(){
        fragmentMatchingBinding.apply {
            toolbarRealtimeLocation.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    // 코드 합치면 그때 작성할 것
                }
            }
        }
    }

    // 선택 완료 버튼
    fun setButtonMatching(){
        fragmentMatchingBinding.apply {
            buttonMatchingDone.apply {
                // 버튼을 눌렀을떄
                setOnClickListener {
                    // 결제 프래그먼토가 보여져야한다
                    mainActivity.replaceFragment(MainFragmentName.PAYMENT_FRAGMENT,true, true, null)
                }
            }
        }
    }

    // cardMatchingReservationDetail의 내용을 설정해준다
    fun setCardMatching(){
        fragmentMatchingBinding.apply {
            // 펫 사진
            imageViewMatchingPet.setImageResource(R.drawable.nayeon)
            // 돌봄 서비스 유형 및 시간
            textViewMatchingPetCareType.setText("돌봄 30분")
            // 펫 이름
            textViewMatchingPetName.setText("뽀삐나연")
            // 예약 날짜
            textViewMatchingReservationDate.setText("3월27일")
            // 예약 시간
            textViewMatchingReservationTime.setText("오후 5:00-5:30")
            // 예약 주소
            textViewMatchingReservationAddress.setText("서울특별시 사랑구 행복동")
        }
    }

    // RecyclerView설정
    fun setRecyclerView(){
        fragmentMatchingBinding.apply {
            recyclerMatching.apply {
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
        // 사용자로부터 선택된 항목의 인덱스
        var selectedPosition = -1
        inner class RecyclerMainViewHolder(rowMatchingBinding: RowMatchingBinding) : RecyclerView.ViewHolder(rowMatchingBinding.root){
            val rowMatchingBinding:RowMatchingBinding

            init{
                this.rowMatchingBinding = rowMatchingBinding
                this.rowMatchingBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerMainViewHolder {
            val rowMatchingBinding = RowMatchingBinding.inflate(layoutInflater)
            val recyclerMainViewHolder = RecyclerMainViewHolder(rowMatchingBinding)

            return recyclerMainViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: RecyclerMainViewHolder, position: Int) {
            holder.rowMatchingBinding.textViewRowMatchingPetSitterName.text = "이영주 시터$position"
            holder.rowMatchingBinding.textViewRowMatchingCareer.text = "수의학과 전공$position"
            holder.rowMatchingBinding.textViewRowMatchingReservationDateTime.text = "날짜${position} 2024.03.27 오후5:00-5:30"

            // 항목을 누르면 동작하는 리스너
            holder.rowMatchingBinding.imageViewRowMatchingReviewButton.setOnClickListener {
                // PetSitterInfoFragment가 보여지게 한다
                mainActivity.replaceFragment(MainFragmentName.PETSITTER_INFO_FRAGMENT,true, true, null)
            }

            // 항목을 누르면 해당 항목의 색상을 변경한다
            holder.itemView.setOnClickListener {
                selectedPosition = position
                notifyDataSetChanged()
            }

            if (selectedPosition == position){
                // 선택된 항목의 색상
                holder.rowMatchingBinding.root.setBackgroundColor(Color.parseColor("#FFEA9F"))
            } else{
                // 기본 색상
                holder.rowMatchingBinding.root.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
    }
}
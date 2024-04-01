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
import kr.co.lion.mungnolja_nayeon1.R
import kr.co.lion.mungnolja_nayeon1.ReservationListActivity
import kr.co.lion.mungnolja_nayeon1.databinding.FragmentReservationListOngoingBinding
import kr.co.lion.mungnolja_nayeon1.databinding.RowReservationListOngoingBinding

class ReservationListOngoingFragment : Fragment() {

    lateinit var fragmentReservationListOngoingBinding: FragmentReservationListOngoingBinding
    lateinit var reservationListActivity: ReservationListActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentReservationListOngoingBinding = FragmentReservationListOngoingBinding.inflate(layoutInflater)
        reservationListActivity = activity as ReservationListActivity

        setRecyclerView()
        setTextView()

        return fragmentReservationListOngoingBinding.root
    }

    // textViewReservationListOngoingCount - 총 건수 설정
    fun setTextView(){
        fragmentReservationListOngoingBinding.apply {
            textViewReservationListOngoingCount.setText("총 10건")
        }
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        fragmentReservationListOngoingBinding.apply {
            recyclerReservationListOngoing.apply {
                // 어뎁터 설정
                adapter = RecyclerMainAdapter()
                // 레이아웃
                layoutManager = LinearLayoutManager(reservationListActivity)
                // 데코
                val deco = MaterialDividerItemDecoration(reservationListActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerMainAdapter : RecyclerView.Adapter<ReservationListOngoingFragment.RecyclerMainAdapter.RecyclerMainViewHolder>(){
        inner class RecyclerMainViewHolder(rowReservationListOngoingBinding: RowReservationListOngoingBinding) : RecyclerView.ViewHolder(rowReservationListOngoingBinding.root){
            val rowReservationListOngoingBinding : RowReservationListOngoingBinding

            init {
                this.rowReservationListOngoingBinding = rowReservationListOngoingBinding
                this.rowReservationListOngoingBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerMainViewHolder {
            val rowReservationListOngoingBinding = RowReservationListOngoingBinding.inflate(layoutInflater)
            val recyclerMainViewHolder = RecyclerMainViewHolder(rowReservationListOngoingBinding)

            return recyclerMainViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: RecyclerMainViewHolder, position: Int) {
            holder.rowReservationListOngoingBinding.textViewRowReservationOngoingPetCareType.text = "돌봄 30분 ${position}"
            holder.rowReservationListOngoingBinding.imageViewRowReservationListOngoingPetsitter.setImageResource(R.drawable.petsitter)
            holder.rowReservationListOngoingBinding.textViewRowReservationListOngoingPetsitterName.text = "이영주 펫시터 ${position}"
            holder.rowReservationListOngoingBinding.imageViewRowReservationListOngoingPet.setImageResource(R.drawable.nayeon)
            holder.rowReservationListOngoingBinding.textViewRowReservationListOngoingPetName.text = "뽀삐나연 ${position}"
            holder.rowReservationListOngoingBinding.textViewRowReservationListOngoingDate.text = "2024년 4월 1일"
            holder.rowReservationListOngoingBinding.textViewRowReservationListOngoingTime.text = "오후 5:00 ~ 5:30"
        }
    }
}
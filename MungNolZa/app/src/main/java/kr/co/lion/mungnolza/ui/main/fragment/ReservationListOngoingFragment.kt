package kr.co.lion.mungnolza.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.ui.reservation_list.ReservationListActivity
import kr.co.lion.mungnolza.databinding.FragmentReservationListOngoingBinding
import kr.co.lion.mungnolza.databinding.RowReservationListOngoingBinding

class ReservationListOngoingFragment : Fragment() {

    private var _binding: FragmentReservationListOngoingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentReservationListOngoingBinding.inflate(inflater, container, false)

        setRecyclerView()

        return binding.root
    }


    // 예약내역이 0일때 vs 하나라도 있을 때 설정
    fun setView(){
        // 예약내역이 0개 일때

        // 예약내역이 1개 이상 있을 때
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        binding.apply {
            recyclerReservationListOngoing.apply {
                // 어뎁터 설정
                adapter = RecyclerMainAdapter()
                // 레이아웃
                layoutManager = LinearLayoutManager(requireContext())
                // 데코
                val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerMainAdapter : RecyclerView.Adapter<RecyclerMainAdapter.RecyclerMainViewHolder>(){
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
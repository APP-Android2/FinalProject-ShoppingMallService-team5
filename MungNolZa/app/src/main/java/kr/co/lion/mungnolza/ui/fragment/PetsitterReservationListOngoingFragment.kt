package kr.co.lion.mungnolza.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetsitterReservationListOngoingBinding
import kr.co.lion.mungnolza.databinding.RowPetsitterReservationListOngoingBinding

class PetsitterReservationListOngoingFragment : Fragment() {

    lateinit var fragmentPetsitterReservationListOngoingBinding: FragmentPetsitterReservationListOngoingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentPetsitterReservationListOngoingBinding = FragmentPetsitterReservationListOngoingBinding.inflate(layoutInflater)
        return fragmentPetsitterReservationListOngoingBinding.root
    }

    // textViewPetsitterReservationListLastCount - 총 건수 설정
    fun setTextView(){
        fragmentPetsitterReservationListOngoingBinding.apply {
            textViewPetsitterReservationListOngoingCount.setText("총 10건")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setTextView()
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        fragmentPetsitterReservationListOngoingBinding.apply {
            recyclerPetsitterReservationListOngoing.apply {
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
        inner class RecyclerMainViewHolder(rowPetsitterReservationListOngoingBinding: RowPetsitterReservationListOngoingBinding) : RecyclerView.ViewHolder(rowPetsitterReservationListOngoingBinding.root){
            val rowPetsitterReservationListOngoingBinding : RowPetsitterReservationListOngoingBinding

            init {
                this.rowPetsitterReservationListOngoingBinding = rowPetsitterReservationListOngoingBinding
                this.rowPetsitterReservationListOngoingBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerMainViewHolder {
            val rowPetsitterReservationListOngoingBinding = RowPetsitterReservationListOngoingBinding.inflate(layoutInflater)
            val recyclerMainViewHolder = RecyclerMainViewHolder(rowPetsitterReservationListOngoingBinding)

            return recyclerMainViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: RecyclerMainViewHolder, position: Int) {
            holder.rowPetsitterReservationListOngoingBinding.textViewRowReservationOngoingPetCareType.text = "돌봄 30분 ${position}"
            holder.rowPetsitterReservationListOngoingBinding.imageViewRowReservationListOngoingUser.setImageResource(R.drawable.ic_my_page_24px)
            holder.rowPetsitterReservationListOngoingBinding.textViewRowReservationListOngoingUserName.text = "임정혀니 ${position}"
            holder.rowPetsitterReservationListOngoingBinding.imageViewRowReservationListOngoingPet.setImageResource(R.drawable.nayeon)
            holder.rowPetsitterReservationListOngoingBinding.textViewRowReservationListOngoingPetName.text = "뽀삐나연 ${position}"
            holder.rowPetsitterReservationListOngoingBinding.textViewRowReservationListOngoingDate.text = "2024년 4월 1일"
            holder.rowPetsitterReservationListOngoingBinding.textViewRowReservationListOngoingTime.text = "오후 5:00 ~ 5:30"
        }
    }
}
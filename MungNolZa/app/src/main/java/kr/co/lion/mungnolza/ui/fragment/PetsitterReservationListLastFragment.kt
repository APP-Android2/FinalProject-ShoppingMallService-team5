package kr.co.lion.mungnolza.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetsitterReservationListLastBinding
import kr.co.lion.mungnolza.databinding.FragmentReservationListLastBinding
import kr.co.lion.mungnolza.databinding.RowPetsitterReservationListLastBinding
import kr.co.lion.mungnolza.databinding.RowReservationListLastBinding
import kr.co.lion.mungnolza.ui.reservation_list.PetsitterReviewWriteActivity

class PetsitterReservationListLastFragment : Fragment() {

    lateinit var fragmentPetsitterReservationListLastBinding: FragmentPetsitterReservationListLastBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentPetsitterReservationListLastBinding = FragmentPetsitterReservationListLastBinding.inflate(layoutInflater)

        return fragmentPetsitterReservationListLastBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setTextView()
    }

    // textViewPetsitterReservationListLastCount - 총 건수 설정
    fun setTextView(){
        fragmentPetsitterReservationListLastBinding.apply {
            textViewPetsitterReservationListLastCount.setText("총 10건")
        }
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        fragmentPetsitterReservationListLastBinding.apply {
            recyclerPetsitterReservationListLast.apply {
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
        inner class RecyclerMainViewHolder(rowPetsitterReservationListLastBinding: RowPetsitterReservationListLastBinding) : RecyclerView.ViewHolder(rowPetsitterReservationListLastBinding.root){
            val rowPetsitterReservationListLastBinding : RowPetsitterReservationListLastBinding

            init {
                this.rowPetsitterReservationListLastBinding = rowPetsitterReservationListLastBinding
                this.rowPetsitterReservationListLastBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerMainViewHolder {
            val rowPetsitterReservationListLastBinding = RowPetsitterReservationListLastBinding.inflate(layoutInflater)
            val recyclerMainViewHolder = RecyclerMainViewHolder(rowPetsitterReservationListLastBinding)

            return recyclerMainViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: RecyclerMainViewHolder, position: Int) {
            holder.rowPetsitterReservationListLastBinding.textViewRowReservationListPetCareType.text = "돌봄 30분 ${position}"
            holder.rowPetsitterReservationListLastBinding.imageViewRowReservationListLastUser.setImageResource(R.drawable.ic_my_page_24px)
            holder.rowPetsitterReservationListLastBinding.textViewRowReservationListLastUserName.text = "임정혀니 ${position}"
            holder.rowPetsitterReservationListLastBinding.imageViewRowReservationListLastPet.setImageResource(R.drawable.nayeon)
            holder.rowPetsitterReservationListLastBinding.textViewRowReservationListLastPetName.text = "뽀삐나연 ${position}"
            holder.rowPetsitterReservationListLastBinding.textViewRowReservationListLastDate.text = "2024년 4월 17일"
            holder.rowPetsitterReservationListLastBinding.textViewRowReservationListLastTime.text = "오후 5:00 ~ 5:30"
        }
    }
}
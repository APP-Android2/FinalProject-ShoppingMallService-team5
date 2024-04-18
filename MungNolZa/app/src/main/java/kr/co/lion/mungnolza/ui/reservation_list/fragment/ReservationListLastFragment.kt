package kr.co.lion.mungnolza.ui.reservation_list.fragment

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
import kr.co.lion.mungnolza.ui.reservation_list.ReservationListActivity
import kr.co.lion.mungnolza.databinding.FragmentReservationListLastBinding
import kr.co.lion.mungnolza.databinding.RowReservationListLastBinding
import kr.co.lion.mungnolza.ui.reservation_list.PetsitterReviewWriteActivity
import kr.co.lion.mungnolza.util.ReservationListFragmentName

class ReservationListLastFragment : Fragment() {

    lateinit var fragmentReservationListLastBinding : FragmentReservationListLastBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentReservationListLastBinding = FragmentReservationListLastBinding.inflate(layoutInflater)

        return fragmentReservationListLastBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setTextView()
    }


    // textViewReservationListLastCount - 총 건수 설정
    fun setTextView(){
        fragmentReservationListLastBinding.apply {
            textViewReservationListLastCount.setText("총 10건")
        }
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        fragmentReservationListLastBinding.apply {
            recyclerReservationListLast.apply {
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
        inner class RecyclerMainViewHolder(rowReservationListLastBinding: RowReservationListLastBinding) : RecyclerView.ViewHolder(rowReservationListLastBinding.root){
            val rowReservationListLastBinding : RowReservationListLastBinding

            init {
                this.rowReservationListLastBinding = rowReservationListLastBinding
                this.rowReservationListLastBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerMainViewHolder {
            val rowReservationListLastBinding = RowReservationListLastBinding.inflate(layoutInflater)
            val recyclerMainViewHolder = RecyclerMainViewHolder(rowReservationListLastBinding)

            return recyclerMainViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: RecyclerMainViewHolder, position: Int) {
            holder.rowReservationListLastBinding.textViewRowReservationListPetCareType.text = "돌봄 30분 ${position}"
            holder.rowReservationListLastBinding.imageViewRowReservationListLastPetsitter.setImageResource(R.drawable.petsitter)
            holder.rowReservationListLastBinding.textViewRowReservationListLastPetsitterName.text = "이영주 펫시터 ${position}"
            holder.rowReservationListLastBinding.imageViewRowReservationListLastPet.setImageResource(R.drawable.nayeon)
            holder.rowReservationListLastBinding.textViewRowReservationListLastPetName.text = "뽀삐나연 ${position}"
            holder.rowReservationListLastBinding.textViewRowReservationListLastDate.text = "2024년 4월 1일"
            holder.rowReservationListLastBinding.textViewRowReservationListLastTime.text = "오후 5:00 ~ 5:30"

            // '후기작성' 항목을 누르면 동작하는 리스너
            holder.rowReservationListLastBinding.textViewRowReservationiListLastWriteReview.setOnClickListener {
                // PetsitterReviewWriteActivity가 보여진다
                val intent = Intent(context, PetsitterReviewWriteActivity::class.java)
                startActivity(intent)
            }
            // '후기작성' 아이콘 항목을 누르면 동작하는 리스너
            holder.rowReservationListLastBinding.imageViewRowReservationListLastEdit.setOnClickListener {
                // PetsitterReviewWriteActivity가 보여진다
                val intent = Intent(context, PetsitterReviewWriteActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
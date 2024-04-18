package kr.co.lion.mungnolza.ui.petsitter_reservation_list.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.RowPetsitterReservationListLastBinding

class PetsitterReservationListLastAdapter : RecyclerView.Adapter<PetsitterReservationListLastAdapter.ViewHolder>() {

    inner class ViewHolder(rowPetsitterReservationListLastBinding: RowPetsitterReservationListLastBinding) : RecyclerView.ViewHolder(rowPetsitterReservationListLastBinding.root) {
        val rowPetsitterReservationListLastBinding : RowPetsitterReservationListLastBinding

        init {
            this.rowPetsitterReservationListLastBinding = rowPetsitterReservationListLastBinding
            this.rowPetsitterReservationListLastBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowPetsitterReservationListLastBinding = RowPetsitterReservationListLastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(rowPetsitterReservationListLastBinding)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rowPetsitterReservationListLastBinding.textViewRowReservationListPetCareType.text = "돌봄 30분 ${position}"
        holder.rowPetsitterReservationListLastBinding.imageViewRowReservationListLastUser.setImageResource(R.drawable.ic_my_page_24px)
        holder.rowPetsitterReservationListLastBinding.textViewRowReservationListLastUserName.text = "임정혀니 ${position}"
        holder.rowPetsitterReservationListLastBinding.imageViewRowReservationListLastPet.setImageResource(R.drawable.nayeon)
        holder.rowPetsitterReservationListLastBinding.textViewRowReservationListLastPetName.text = "뽀삐나연 ${position}"
        holder.rowPetsitterReservationListLastBinding.textViewRowReservationListLastDate.text = "2024년 4월 17일"
        holder.rowPetsitterReservationListLastBinding.textViewRowReservationListLastTime.text = "오후 5:00 ~ 5:30"
    }
}

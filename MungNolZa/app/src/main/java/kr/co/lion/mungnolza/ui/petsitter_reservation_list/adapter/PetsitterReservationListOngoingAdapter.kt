package kr.co.lion.mungnolza.ui.petsitter_reservation_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.RowPetsitterReservationListOngoingBinding

class PetsitterReservationListOngoingAdapter : RecyclerView.Adapter<PetsitterReservationListOngoingAdapter.ViewHolder>(){
    inner class ViewHolder(rowPetsitterReservationListOngoingBinding: RowPetsitterReservationListOngoingBinding) : RecyclerView.ViewHolder(rowPetsitterReservationListOngoingBinding.root){
        val rowPetsitterReservationListOngoingBinding : RowPetsitterReservationListOngoingBinding

        init{
            this.rowPetsitterReservationListOngoingBinding = rowPetsitterReservationListOngoingBinding
            this.rowPetsitterReservationListOngoingBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowPetsitterReservationListOngoingBinding = RowPetsitterReservationListOngoingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(rowPetsitterReservationListOngoingBinding)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rowPetsitterReservationListOngoingBinding.textViewRowReservationOngoingPetCareType.text = "돌봄 30분 ${position}"
        holder.rowPetsitterReservationListOngoingBinding.imageViewRowReservationListOngoingUser.setImageResource(
            R.drawable.ic_my_page_24px)
        holder.rowPetsitterReservationListOngoingBinding.textViewRowReservationListOngoingUserName.text = "임정혀니 ${position}"
        holder.rowPetsitterReservationListOngoingBinding.imageViewRowReservationListOngoingPet.setImageResource(
            R.drawable.nayeon)
        holder.rowPetsitterReservationListOngoingBinding.textViewRowReservationListOngoingPetName.text = "뽀삐나연 ${position}"
        holder.rowPetsitterReservationListOngoingBinding.textViewRowReservationListOngoingDate.text = "2024년 4월 1일"
        holder.rowPetsitterReservationListOngoingBinding.textViewRowReservationListOngoingTime.text = "오후 5:00 ~ 5:30"
    }
}
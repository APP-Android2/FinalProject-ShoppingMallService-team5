package kr.co.lion.mungnolza.ui.freeboard.adapter

import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.RowShowDetailBoardBinding

class BoardCarouselViewHolder(
    private val binding:RowShowDetailBoardBinding
) :RecyclerView.ViewHolder(binding.root) {

    fun bind(){
        itemView.apply{
            binding.imageViewCarouselShowDetailBoard.setImageResource(R.drawable.img_dog)
        }
    }

}
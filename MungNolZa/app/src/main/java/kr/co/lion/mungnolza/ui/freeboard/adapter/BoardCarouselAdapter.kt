package kr.co.lion.mungnolza.ui.freeboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.RowShowDetailBoardBinding

class BoardCarouselAdapter(): RecyclerView.Adapter<BoardCarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardCarouselViewHolder {
        return BoardCarouselViewHolder(RowShowDetailBoardBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    // override fun getItemCount(): Int = itemList.size
    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: BoardCarouselViewHolder, position: Int) {
        holder.bind()
        //holder.bind(itemList[position])
    }
}
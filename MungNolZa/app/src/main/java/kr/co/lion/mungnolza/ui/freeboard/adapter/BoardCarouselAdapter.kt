package kr.co.lion.mungnolza.ui.freeboard.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.RowShowDetailBoardBinding

class BoardCarouselAdapter(var imageUriList:MutableList<Uri?>): RecyclerView.Adapter<BoardCarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardCarouselViewHolder {
        return BoardCarouselViewHolder(RowShowDetailBoardBinding.inflate(LayoutInflater.from(parent.context),parent, false), imageUriList)
    }

    // override fun getItemCount(): Int = itemList.size
    override fun getItemCount(): Int = imageUriList.size

    override fun onBindViewHolder(holder: BoardCarouselViewHolder, position: Int) {
        holder.bind()
        //holder.bind(itemList[position])
    }
}
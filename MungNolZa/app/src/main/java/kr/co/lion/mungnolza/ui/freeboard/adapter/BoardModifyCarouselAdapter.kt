package kr.co.lion.mungnolza.ui.freeboard.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.mungnolza.databinding.RowModifyBoardBinding
import kr.co.lion.mungnolza.databinding.RowShowDetailBoardBinding

class BoardModifyCarouselAdapter(private var imageUriList:MutableList<Uri?>): RecyclerView.Adapter<BoardModifyCarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardModifyCarouselViewHolder {
        return BoardModifyCarouselViewHolder(RowModifyBoardBinding.inflate(LayoutInflater.from(parent.context),parent, false), imageUriList)
    }

    // override fun getItemCount(): Int = itemList.size
    override fun getItemCount(): Int = imageUriList.size

    override fun onBindViewHolder(holder: BoardModifyCarouselViewHolder, position: Int) {
        holder.bind(position)
    }
}
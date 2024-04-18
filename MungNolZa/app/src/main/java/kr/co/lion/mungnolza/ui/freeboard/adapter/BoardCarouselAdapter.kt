package kr.co.lion.mungnolza.ui.freeboard.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.RowShowDetailBoardBinding

class BoardCarouselAdapter(private var imageUriList:MutableList<Uri?>): RecyclerView.Adapter<BoardCarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardCarouselViewHolder {
        Log.d("Adapter의 onCreateViewHolder 이미지 리스트",imageUriList[0].toString())
        return BoardCarouselViewHolder(RowShowDetailBoardBinding.inflate(LayoutInflater.from(parent.context),parent, false), imageUriList)
    }

    // override fun getItemCount(): Int = itemList.size
    override fun getItemCount(): Int = imageUriList.size

    override fun onBindViewHolder(holder: BoardCarouselViewHolder, position: Int) {
        holder.bind(position)

    }
}
package kr.co.lion.mungnolza.ui.freeboard.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.RowShowDetailBoardBinding

class BoardCarouselViewHolder(
    private val binding:RowShowDetailBoardBinding,
    private val imageUriList:MutableList<Uri?>
) :RecyclerView.ViewHolder(binding.root) {

    fun bind(){
        itemView.apply{
            CoroutineScope(Dispatchers.Main).launch {
                imageUriList.forEach {
                    Glide.with(context).load(imageUriList).into(binding.imageViewCarouselShowDetailBoard)
                }
            }
        }
    }

}
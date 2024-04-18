package kr.co.lion.mungnolza.ui.freeboard.adapter

import android.net.Uri
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.RowShowDetailBoardBinding

class BoardCarouselViewHolder(
    private val binding: RowShowDetailBoardBinding,
    private val imageUriList: MutableList<Uri?>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(position: Int) {
        itemView.apply {

            Glide.with(context)
                .load(imageUriList[position])
                .into(binding.imageViewCarouselShowDetailBoard)

            Log.d("ViewHolder 의 bind 함수에서 이미지 리스트", imageUriList[position].toString())

        }
    }

}
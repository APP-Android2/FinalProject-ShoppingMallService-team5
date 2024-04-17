package kr.co.lion.mungnolza.ui.freeboard.adapter

import android.net.Uri
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.lion.mungnolza.databinding.RowModifyBoardBinding
import kr.co.lion.mungnolza.databinding.RowShowDetailBoardBinding

class BoardModifyCarouselViewHolder(
    private val binding: RowModifyBoardBinding,
    private val imageUriList: MutableList<Uri?>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(position: Int) {
        itemView.apply {
            Glide.with(context)
                .load(imageUriList[position])
                .into(binding.imageViewCarouselModifyBoard)

            binding.buttonCloseRowModifyBoard.setOnClickListener {
                imageUriList.removeAt(position)

                // 삭제 시 화면에서 사라지는 이벤트 구현 필요
                // 클릭 이벤트를 매개변수로 받아 프래그먼트에서 처리하는 방법도 있네... ㅇㅋ
            }
        }
    }

}
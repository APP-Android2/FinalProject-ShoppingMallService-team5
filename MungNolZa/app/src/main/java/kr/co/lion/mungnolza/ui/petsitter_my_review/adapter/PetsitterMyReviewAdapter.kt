package kr.co.lion.mungnolza.ui.petsitter_my_review.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.RowReviewPreviewBinding

class PetsitterMyReviewAdapter : RecyclerView.Adapter<PetsitterMyReviewAdapter.ViewHolder>() {
    inner class ViewHolder(rowReviewPreviewBinding: RowReviewPreviewBinding) : RecyclerView.ViewHolder(rowReviewPreviewBinding.root){
        val rowReviewPreviewBinding : RowReviewPreviewBinding

        init {
            this.rowReviewPreviewBinding = rowReviewPreviewBinding
            this.rowReviewPreviewBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowReviewPreviewBinding = RowReviewPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(rowReviewPreviewBinding)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rowReviewPreviewBinding.userImage.setImageResource(R.drawable.ic_my_page_24px)
        holder.rowReviewPreviewBinding.userName.text = "문상훈 $position"
        holder.rowReviewPreviewBinding.MyRating.rating = 5f
        holder.rowReviewPreviewBinding.reviewTime.text = "2024-04-18"
        holder.rowReviewPreviewBinding.reviewContent.text = "저같은경우에는항상배가고픈편입니다지금도배가고픈상태이지요들레히호호호호"
    }
}
package kr.co.lion.mungnolza.ui.appointment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.lion.mungnolza.databinding.RowReviewPreviewBinding
import kr.co.lion.mungnolza.model.ReviewAddUserInfoModel

class ReviewAdapter(private val reviewDate: List<ReviewAddUserInfoModel>): RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(private val binding: RowReviewPreviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ReviewAddUserInfoModel){
            with(binding){
                Glide.with(root)
                    .load(item.imgUri.toString())
                    .into(userImage)

                rating.rating = item.review.reviewStarCount

                userName.text = item.review.reviewWriterName
                reviewContent.text = item.review.reviewText
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReviewViewHolder(RowReviewPreviewBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount() = reviewDate.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviewDate[position])
    }
}
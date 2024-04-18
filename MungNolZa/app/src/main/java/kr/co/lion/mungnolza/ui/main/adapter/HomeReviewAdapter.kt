package kr.co.lion.mungnolza.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.mungnolza.databinding.RowReviewPreviewBinding
import kr.co.lion.mungnolza.model.PetsitterReviewModel

class HomeReviewAdapter: ListAdapter<PetsitterReviewModel, HomeReviewAdapter.HomeReviewAdapterViewHolder>(DiffCallback) {
    class HomeReviewAdapterViewHolder(private val binding: RowReviewPreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PetsitterReviewModel){
            with(binding){

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeReviewAdapterViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HomeReviewAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<PetsitterReviewModel>() {

            override fun areItemsTheSame(oldItem: PetsitterReviewModel, newItem: PetsitterReviewModel): Boolean {
                return oldItem.reviewIdx == newItem.reviewIdx
            }

            override fun areContentsTheSame(oldItem: PetsitterReviewModel, newItem: PetsitterReviewModel): Boolean {
                return oldItem == newItem

            }
        }
    }
}
package kr.co.lion.mungnolza.ui.appointment.adapter

import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.RowMatchingBinding
import kr.co.lion.mungnolza.model.PetSitterWithReviewModel

class PetSitterAdapter(
    private val petSitters: List<PetSitterWithReviewModel>,
    private val reviewCntClick: (Int) -> Unit,
    private val itemClick: (Int) -> Unit,
): RecyclerView.Adapter<PetSitterAdapter.PetSitterViewHolder>() {
    private var selectedItemPosition: Int = RecyclerView.NO_POSITION

    inner class PetSitterViewHolder(
        private val context: Context,
        private val binding: RowMatchingBinding,
        private val reviewCntClick: (Int) -> Unit,
        private val itemClick: (Int) -> Unit
    ): RecyclerView.ViewHolder(binding.root){

        init {

            binding.reviewCnt.setOnClickListener {
                reviewCntClick.invoke(absoluteAdapterPosition)
            }

            binding.root.setOnClickListener{
                if (absoluteAdapterPosition != RecyclerView.NO_POSITION) {
                    notifyItemChanged(selectedItemPosition)
                    selectedItemPosition = absoluteAdapterPosition
                    notifyItemChanged(selectedItemPosition)
                }
                itemClick.invoke(absoluteAdapterPosition)
            }
        }

        fun binding(item: PetSitterWithReviewModel, position: Int){
            with(binding){

                val reviewCount = item.review.size
                val content = SpannableString("${reviewCount}개의 리뷰")
                content.setSpan(UnderlineSpan(), 0, content.length, 0)
                reviewCnt.text = content

                val averageReviewCount = item.review.map { it.reviewStarCount.toInt() }.average()

                if (averageReviewCount.isNaN()){
                    reviewScore.text = "0"
                }else{
                    reviewScore.text = averageReviewCount.toString()
                }


                petSitterName.text = item.petSitterInfo.petSitter.petSitterName

                val career = StringBuilder()
                item.petSitterInfo.petSitter.petSitterCareer.map {
                    career.append(it).append("\n")
                }

                petSitterCareer.text = career.trim()

                Glide.with(binding.root)
                    .load(item.petSitterInfo.profileImg.toString())
                    .into(imgPetSitter)

                root.backgroundTintList = if (absoluteAdapterPosition == selectedItemPosition){
                        ContextCompat.getColorStateList(context, R.color.app_main_color)
                    } else{
                        ContextCompat.getColorStateList(context, R.color.white)
                    }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetSitterViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return PetSitterViewHolder(
            parent.context,
            RowMatchingBinding.inflate(inflater, parent, false),
            reviewCntClick,
            itemClick
        )
    }

    override fun getItemCount() = petSitters.size

    override fun onBindViewHolder(holder: PetSitterViewHolder, position: Int) {
        holder.binding(petSitters[position], position)
    }
}
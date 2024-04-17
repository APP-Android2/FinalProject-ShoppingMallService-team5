package kr.co.lion.mungnolza.ui.appointment.adapter

import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.lion.mungnolza.databinding.RowMatchingBinding
import kr.co.lion.mungnolza.model.PetSitterModelWithImg


class PetSitterAdapter(
    private val petSitters: ArrayList<PetSitterModelWithImg>,
    private val itemClick: (Int) -> Unit
): RecyclerView.Adapter<PetSitterAdapter.PetSitterViewHolder>() {
    class PetSitterViewHolder(
        private val context: Context,
        private val binding: RowMatchingBinding,
        private val itemClick: (Int) -> Unit
    ): RecyclerView.ViewHolder(binding.root){

        init {
            binding.reviewCnt.setOnClickListener {
                itemClick.invoke(adapterPosition)
            }
        }

        fun binding(item: PetSitterModelWithImg){
            with(binding){

                val content = SpannableString("5개의 리뷰")
                content.setSpan(UnderlineSpan(), 0, content.length, 0)
                reviewCnt.text = content

                petSitterName.text = item.petSitter.petSitterName

                val career = StringBuilder()
                item.petSitter.petSitterCareer.map {
                    career.append(it).append("\n")
                }

                petSitterCareer.text = career.trim()

                Glide.with(binding.root)
                    .load(item.profileImg.toString())
                    .into(imgPetSitter)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetSitterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PetSitterViewHolder(parent.context, RowMatchingBinding.inflate(inflater, parent, false), itemClick)
    }

    override fun getItemCount() = petSitters.size

    override fun onBindViewHolder(holder: PetSitterViewHolder, position: Int) {
        holder.binding(petSitters[position])
    }
}
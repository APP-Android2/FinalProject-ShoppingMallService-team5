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
import kr.co.lion.mungnolza.model.PetSitterModelWithImg

class PetSitterAdapter(
    private val petSitters: ArrayList<PetSitterModelWithImg>,
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
                reviewCntClick.invoke(adapterPosition)
            }

            binding.root.setOnClickListener{
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    notifyItemChanged(selectedItemPosition) // 기존 선택된 아이템 배경색 변경
                    selectedItemPosition = adapterPosition
                    notifyItemChanged(selectedItemPosition) // 새로운 선택된 아이템 배경색 변경
                }
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

                root.backgroundTintList = if (adapterPosition == selectedItemPosition){
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
        holder.binding(petSitters[position])
    }
}
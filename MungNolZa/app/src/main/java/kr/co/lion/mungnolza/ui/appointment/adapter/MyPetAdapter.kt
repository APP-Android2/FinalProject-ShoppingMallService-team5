package kr.co.lion.mungnolza.ui.appointment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.AppointmentPetSelectItemBinding
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.model.PetModel

class MyPetAdapter(
    private val myPets: List<PetImgModel>,
    private val itemClick: (Int) -> Unit
) : RecyclerView.Adapter<MyPetAdapter.SelectPatViewHolder>() {

    class SelectPatViewHolder(
        private val context: Context,
        private val binding: AppointmentPetSelectItemBinding,
        private val itemClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                root.setOnClickListener{
                    val isVisible = petCheck.isVisible

                    if (isVisible){
                        petCheck.visibility = View.INVISIBLE
                        root.backgroundTintList = ContextCompat.getColorStateList(context, R.color.white)
                    }else{
                        petCheck.visibility = View.VISIBLE
                        root.backgroundTintList = ContextCompat.getColorStateList(context, R.color.app_main_color)
                    }

                    itemClick.invoke(adapterPosition)
                }
            }
        }

        fun bind(item: PetImgModel) {
            with(binding) {

                Glide.with(root)
                    .load(item.imgUrl.toString())
                    .into(petImageView)

                petName.text = item.petInfo.petName
                petKind.text = item.petInfo.petBreed
                petAge.text = "${item.petInfo.petAge} 살"
                petWeight.text = " / ${item.petInfo.petWeight} kg"

                petImageView.clipToOutline = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectPatViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return SelectPatViewHolder(
            parent.context,
            AppointmentPetSelectItemBinding.inflate(inflater, parent, false),
            itemClick
        )
    }

    override fun getItemCount() = myPets.size

    override fun onBindViewHolder(holder: SelectPatViewHolder, position: Int) {
        holder.bind(myPets[position])
    }
}
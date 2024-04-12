package kr.co.lion.mungnolza.ui.appointment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.lion.mungnolza.databinding.AppointmentPetSelectItemBinding
import kr.co.lion.mungnolza.model.PetInfo

class SelectPetAdapter(
    private val myPets: ArrayList<PetInfo>,
    private val itemClick: (String) -> Unit
) : RecyclerView.Adapter<SelectPetAdapter.SelectPatViewHolder>() {

    class SelectPatViewHolder(
        private val binding: AppointmentPetSelectItemBinding,
        private val itemClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PetInfo) {
            with(binding) {

                Glide.with(root)
                    .load(item.petImg)
                    .into(petImageView)

                petName.text = item.petName
                petKind.text = item.petKind
                petAge.text = "${item.petAge} 살"
                petWeight.text = " / ${item.petWeight} kg"

                petImageView.clipToOutline = true

                root.setOnClickListener{
                    val isVisible = petCheck.isVisible

                    if (isVisible){
                        petCheck.visibility = View.INVISIBLE
                    }else{
                        petCheck.visibility = View.VISIBLE
                    }

                    itemClick.invoke(item.petName)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectPatViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return SelectPatViewHolder(
            AppointmentPetSelectItemBinding.inflate(inflater, parent, false),
            itemClick
        )
    }

    override fun getItemCount() = myPets.size

    override fun onBindViewHolder(holder: SelectPatViewHolder, position: Int) {
        holder.bind(myPets[position])
    }
}
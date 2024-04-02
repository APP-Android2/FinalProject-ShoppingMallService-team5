package kr.co.lion.mungnolza.ui.appointment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.lion.mungnolza.databinding.AppointmentPetSelectItemBinding
import kr.co.lion.mungnolza.model.PetInfo

class SelectPetAdapter(
    private val myPets: ArrayList<PetInfo>,
) : RecyclerView.Adapter<SelectPetAdapter.SelectPatViewHolder>() {

    class SelectPatViewHolder(private val binding: AppointmentPetSelectItemBinding
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
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectPatViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return SelectPatViewHolder(
            AppointmentPetSelectItemBinding.inflate(inflater, parent, false),
        )
    }

    override fun getItemCount() = myPets.size
    
    override fun onBindViewHolder(holder: SelectPatViewHolder, position: Int) {
        holder.bind(myPets[position])
    }
}
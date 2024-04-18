package kr.co.lion.mungnolza.ui.appointment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.lion.mungnolza.databinding.RowSelectedPetBinding
import java.net.URI

class PetImgAdapter(private val imgList: List<URI>): RecyclerView.Adapter<PetImgAdapter.PetImgViewHolder>() {
    class PetImgViewHolder(private val binding: RowSelectedPetBinding): RecyclerView.ViewHolder(binding.root){
        fun binding(item: URI){
            Glide.with(binding.root)
                .load(item.toString())
                .into(binding.imgPet)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetImgViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PetImgViewHolder(RowSelectedPetBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount() = imgList.size

    override fun onBindViewHolder(holder: PetImgViewHolder, position: Int) {
        holder.binding(imgList[position])
    }

}
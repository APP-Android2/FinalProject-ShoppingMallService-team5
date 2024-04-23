package kr.co.lion.mungnolza.ui.admin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.mungnolza.databinding.RowAdminMainNewPetsitterBinding

class AdminNewPetsitterAdapter : RecyclerView.Adapter<AdminNewPetsitterAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RowAdminMainNewPetsitterBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowAdminMainNewPetsitterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Customize the data displayed in each row
        holder.binding.textViewRowNewPetsitterType.text = "펫시터 지원 ${position+1}"
        holder.binding.textViewRowNewPetsitterName.text = "이름 $position"
        holder.binding.textViewRowNewPetsitterCertificate1.text = "반려동물자격증"
    }
}

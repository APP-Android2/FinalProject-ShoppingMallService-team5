package kr.co.lion.mungnolza.ui.appointment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.mungnolza.databinding.RowDateBinding

class DateAdapter(private val dateList: List<String>): RecyclerView.Adapter<DateAdapter.DateViewHolder>() {
    class DateViewHolder(val binding: RowDateBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item : String){
            binding.chip.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DateViewHolder(RowDateBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount() = dateList.size
    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(dateList[position])
    }
}
package kr.co.lion.mungnolza.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.lion.mungnolza.databinding.RowFreeBoardBinding
import kr.co.lion.mungnolza.model.BoardAddUerInfoModel

class FreeBoardAdapter(
    private val dataSet: List<BoardAddUerInfoModel>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<FreeBoardAdapter.FreeBoardAdapterViewHolder>() {
    class FreeBoardAdapterViewHolder(
        private val binding: RowFreeBoardBinding, private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BoardAddUerInfoModel) {
            with(binding) {
                tvTitle.text = item.contentData.boardTitle
                tvContent.text = "${item.contentData.boardContent.substring(0, 20)}..."
                tvLike.text = item.contentData.boardLikeNumber.toString()
                tvDate.text = item.contentData.boardWriteDate
                tvNickname.text = item.writerNickName

                Glide.with(binding.root)
                    .load(item.imgUri)
                    .into(thumbnail)

                root.setOnClickListener {
                    onClick.invoke(item.contentData.boardIdx)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreeBoardAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return FreeBoardAdapterViewHolder(
            RowFreeBoardBinding.inflate(inflater, parent, false),
            onClick
        )
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: FreeBoardAdapterViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }
}
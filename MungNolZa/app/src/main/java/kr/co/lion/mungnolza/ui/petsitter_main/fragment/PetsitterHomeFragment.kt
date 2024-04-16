package kr.co.lion.mungnolza.ui.petsitter_main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetsitterHomeBinding
import kr.co.lion.mungnolza.databinding.RowPetsitterHomeChatPersonListBinding

class PetsitterHomeFragment : Fragment() {

    lateinit var fragmentPetsitterHomeBinding : FragmentPetsitterHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentPetsitterHomeBinding = FragmentPetsitterHomeBinding.inflate(inflater, container, false)

        return fragmentPetsitterHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        fragmentPetsitterHomeBinding.apply {
            recyclerViewPetsitterHomeChatList.apply {
                // 어뎁터 설정
                adapter = RecyclerMainAdapter()
                // 레이아웃
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                // 데코
                //val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.HORIZONTAL)
                //addItemDecoration(deco)
            }
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerMainAdapter : RecyclerView.Adapter<RecyclerMainAdapter.RecyclerMainViewHolder>(){
       inner class RecyclerMainViewHolder(rowPetsitterHomeChatPersonListBinding: RowPetsitterHomeChatPersonListBinding) : RecyclerView.ViewHolder(rowPetsitterHomeChatPersonListBinding.root){
           val rowPetsitterHomeChatPersonListBinding : RowPetsitterHomeChatPersonListBinding

           init {
               this.rowPetsitterHomeChatPersonListBinding = rowPetsitterHomeChatPersonListBinding
               this.rowPetsitterHomeChatPersonListBinding.root.layoutParams = ViewGroup.LayoutParams(
                   ViewGroup.LayoutParams.WRAP_CONTENT,
                   ViewGroup.LayoutParams.MATCH_PARENT
               )
           }
       }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerMainViewHolder {
            val rowPetsitterHomeChatPersonListBinding = RowPetsitterHomeChatPersonListBinding.inflate(layoutInflater, parent, false)
            val recyclerMainViewHolder = RecyclerMainViewHolder(rowPetsitterHomeChatPersonListBinding)

            return recyclerMainViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: RecyclerMainViewHolder, position: Int) {
            holder.rowPetsitterHomeChatPersonListBinding.imageViewRowPetsitterHomeChatListPerson.setImageResource(R.drawable.eunwoo)
            holder.rowPetsitterHomeChatPersonListBinding.textViewRowPetsitterHomeChatListPersonName.text = "영준"
        }
    }
}
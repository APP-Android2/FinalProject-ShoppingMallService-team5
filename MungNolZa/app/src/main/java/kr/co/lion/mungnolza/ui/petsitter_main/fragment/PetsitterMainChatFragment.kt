package kr.co.lion.mungnolza.ui.petsitter_main.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetsitterMainChatBinding
import kr.co.lion.mungnolza.databinding.RowMainChatBinding
import kr.co.lion.mungnolza.databinding.RowPetsitterMainChatBinding
import kr.co.lion.mungnolza.ui.chat.ChatActivity

class PetsitterMainChatFragment : Fragment() {

    lateinit var fragmentPetsitterMainChatBinding : FragmentPetsitterMainChatBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentPetsitterMainChatBinding = FragmentPetsitterMainChatBinding.inflate(inflater, container, false)

        setRecyclerViewMainChat()

        return fragmentPetsitterMainChatBinding.root
    }

    fun setRecyclerViewMainChat(){
        fragmentPetsitterMainChatBinding.apply {
            recyclerViewPetsitterMainChat.apply {
                // 어뎁터
                adapter = RecyclerViewAdapterMainChat()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(requireContext())
                // 데코레이션
                val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 메인 화면의 RecyclerView의 어뎁터
    inner class RecyclerViewAdapterMainChat : RecyclerView.Adapter<RecyclerViewAdapterMainChat.ViewHolderMainChat>(){
        inner class ViewHolderMainChat(rowPetsitterMainChatBinding: RowPetsitterMainChatBinding) : RecyclerView.ViewHolder(rowPetsitterMainChatBinding.root){
            val rowPetsitterMainChatBinding: RowPetsitterMainChatBinding

            init {
                this.rowPetsitterMainChatBinding = rowPetsitterMainChatBinding

                this.rowPetsitterMainChatBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMainChat {
            val rowPetsitterMainChatBinding = RowPetsitterMainChatBinding.inflate(layoutInflater)
            val mainViewHolder = ViewHolderMainChat(rowPetsitterMainChatBinding)
            return mainViewHolder
        }

        override fun getItemCount(): Int {
            return 5
        }

        override fun onBindViewHolder(holder: ViewHolderMainChat, position: Int) {
            holder.rowPetsitterMainChatBinding.textViewContentRowMainChat.text = "안녕하세요~안녕하세요~"
            holder.rowPetsitterMainChatBinding.textViewNickNameRowMainChat.text = "홍길동"
            holder.rowPetsitterMainChatBinding.textViewDateRowMainChat.text = "2024-04-01"
            holder.rowPetsitterMainChatBinding.imageViewProfileRowMainChat.setImageResource(R.drawable.ic_my_page_24px)

            holder.rowPetsitterMainChatBinding.root.setOnClickListener {
                val intent = Intent(requireContext(), ChatActivity::class.java)

                startActivity(intent)
            }
        }
    }
}
package kr.co.lion.mungnolza.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentMainChatBinding
import kr.co.lion.mungnolza.databinding.RowMainChatBinding
import kr.co.lion.mungnolza.ui.chat.ChatActivity
import kr.co.lion.mungnolza.ui.chat.viewmodel.MainChatViewModel
import kr.co.lion.mungnolza.util.ChatFragmentName


class MainChatFragment : Fragment() {

    private var _binding: FragmentMainChatBinding ? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainChatBinding.inflate(inflater,  container, false)

        setRecyclerViewMainChat()

        return binding.root
    }

    fun setRecyclerViewMainChat(){
        binding.apply {
            recyclerViewMainChat.apply {
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
        inner class ViewHolderMainChat(rowMainChatBinding: RowMainChatBinding) : RecyclerView.ViewHolder(rowMainChatBinding.root){
            val rowMainChatBinding: RowMainChatBinding

            init {
                this.rowMainChatBinding = rowMainChatBinding

                this.rowMainChatBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMainChat {
            val rowMainChatBinding = RowMainChatBinding.inflate(layoutInflater)
            val mainViewHolder = ViewHolderMainChat(rowMainChatBinding)
            return mainViewHolder
        }

        override fun getItemCount(): Int {
            return 3
        }

        override fun onBindViewHolder(holder: ViewHolderMainChat, position: Int) {
            holder.rowMainChatBinding.textViewContentRowMainChat.text = "박지성... 너 축구 잘해?"
            holder.rowMainChatBinding.textViewNickNameRowMainChat.text = "정찬호"
            holder.rowMainChatBinding.textViewDateRowMainChat.text = "2024-04-01"
            holder.rowMainChatBinding.imageViewProfileRowMainChat.setImageResource(R.drawable.img_jch)

            holder.rowMainChatBinding.root.setOnClickListener {
                val intent = Intent(requireContext(),ChatActivity::class.java)

                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
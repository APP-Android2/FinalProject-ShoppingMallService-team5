package kr.co.lion.mungnolza.ui.chat.fragment

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
import kr.co.lion.mungnolza.databinding.FragmentDetailChatBinding
import kr.co.lion.mungnolza.databinding.RowDetailChatBinding
import kr.co.lion.mungnolza.databinding.RowFreeBoardBinding
import kr.co.lion.mungnolza.ui.freeboard.fragment.FreeBoardFragment
import kr.co.lion.mungnolza.ui.chat.ChatActivity
import kr.co.lion.mungnolza.ui.chat.viewmodel.DetailChatViewModel
import kr.co.lion.mungnolza.util.ChatFragmentName
import kr.co.lion.mungnolza.util.BoardFragmentName


class DetailChatFragment : Fragment() {
    lateinit var chatActivity: ChatActivity
    lateinit var binding: FragmentDetailChatBinding
    lateinit var detailChatViewModel: DetailChatViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail_chat,container, false)
        detailChatViewModel = DetailChatViewModel()
        binding.detailChatViewModel = detailChatViewModel
        binding.lifecycleOwner = this

        chatActivity = activity as ChatActivity

        setToolbar()
        setRecyclerViewDetailChat()

        return binding.root
    }

    fun setToolbar(){
        binding.apply{
            toolbarDetailChat.apply{
                title = "수원 손흥민 정찬호"
                setNavigationIcon(R.drawable.ic_arrow_back_24px)

                // 백버튼 이벤트
                setNavigationOnClickListener {
                    chatActivity.removeFragment(ChatFragmentName.DETAIL_CHAT_FRAGMENT)
                }
            }
        }
    }

    fun setRecyclerViewDetailChat(){
        binding.apply {
            recyclerViewChatDetailChat.apply {
                // 어뎁터
                adapter = RecyclerViewAdapterDetailChat()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(chatActivity)

            }
        }
    }

    inner class RecyclerViewAdapterDetailChat : RecyclerView.Adapter<RecyclerViewAdapterDetailChat.ViewHolderDetailChat>(){
        inner class ViewHolderDetailChat(rowDetailChatBinding: RowDetailChatBinding) : RecyclerView.ViewHolder(rowDetailChatBinding.root){
            val rowDetailChatBinding: RowDetailChatBinding

            init {
                this.rowDetailChatBinding = rowDetailChatBinding

                this.rowDetailChatBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDetailChat {
            val rowDetailChatBinding = RowDetailChatBinding.inflate(layoutInflater)
            val mainViewHolder = ViewHolderDetailChat(rowDetailChatBinding)
            return mainViewHolder
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: ViewHolderDetailChat, position: Int) {
            holder.rowDetailChatBinding.editTextChatRowDetailChat.setText("박지성... 너 축구 잘해?")
            holder.rowDetailChatBinding.textViewDateRowDetailChat.text = "오전 10:40"
        }
    }

}
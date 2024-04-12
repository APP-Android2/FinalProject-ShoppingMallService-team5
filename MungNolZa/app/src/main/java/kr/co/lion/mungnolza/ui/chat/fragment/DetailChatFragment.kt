package kr.co.lion.mungnolza.ui.chat.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentDetailChatBinding
import kr.co.lion.mungnolza.databinding.RowDetailChatBinding
import kr.co.lion.mungnolza.databinding.RowDetailChatYouBinding
import kr.co.lion.mungnolza.databinding.RowFreeBoardBinding
import kr.co.lion.mungnolza.model.ChatModel

import kr.co.lion.mungnolza.ui.chat.ChatActivity
import kr.co.lion.mungnolza.ui.chat.viewmodel.DetailChatViewModel
import kr.co.lion.mungnolza.util.ChatFragmentName

class DetailChatFragment : Fragment() {
    lateinit var chatActivity: ChatActivity
    lateinit var binding: FragmentDetailChatBinding
    lateinit var detailChatViewModel: DetailChatViewModel

    lateinit var chatList:MutableList<ChatModel>

    lateinit var testChat1:ChatModel
    lateinit var testChat2:ChatModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail_chat,container, false)
        detailChatViewModel = DetailChatViewModel()
        binding.detailChatViewModel = detailChatViewModel
        binding.lifecycleOwner = this

        chatActivity = activity as ChatActivity

        initData()
        testData()

        setToolbar()
        setRecyclerViewDetailChat()

        return binding.root
    }

    fun initData(){
        chatList = mutableListOf()
    }

    fun testData(){
        testChat1 = ChatModel("정찬호","박지성","수원의 아들은 나야나","5분전")
        testChat2 = ChatModel("박지성","정찬호","룰루랄라~","6분전")

        chatList.add(testChat1)
        chatList.add(testChat2)
    }

    fun setToolbar(){
        binding.apply{
            toolbarDetailChat.apply{
                title = "정찬호"
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

    inner class RecyclerViewAdapterDetailChat : RecyclerView.Adapter<ViewHolder>(){


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
            val rowDetailChatBinding = RowDetailChatBinding.inflate(layoutInflater)
            // val rowDetailChatYouBinding = RowDetailChatYouBinding.inflate(layoutInflater)

            val mainViewHolder = ViewHolderDetailChat(rowDetailChatBinding)
            return mainViewHolder
        }

        override fun getItemCount(): Int {
            return chatList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            (holder as ViewHolderDetailChat).rowDetailChatBinding.editTextChatRowDetailChat.setText(testChat1.chatContent)
            (holder as ViewHolderDetailChat).rowDetailChatBinding.textViewDateRowDetailChat.text = testChat1.chatDate

        }

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

        inner class ViewHolderDetailChatYou(rowDetailChatYouBinding: RowDetailChatYouBinding) : RecyclerView.ViewHolder(rowDetailChatYouBinding.root){
            val rowDetailChatYouBinding :RowDetailChatYouBinding

            init {
                this.rowDetailChatYouBinding = rowDetailChatYouBinding

                this.rowDetailChatYouBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }
    }
}
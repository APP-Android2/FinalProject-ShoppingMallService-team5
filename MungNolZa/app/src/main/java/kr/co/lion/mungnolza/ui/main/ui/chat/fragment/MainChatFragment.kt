package kr.co.lion.mungnolza.ui.main.ui.chat.fragment

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
import kr.co.lion.mungnolza.databinding.RowFreeBoardBinding
import kr.co.lion.mungnolza.databinding.RowMainChatBinding
import kr.co.lion.mungnolza.ui.main.ui.chat.ChatActivity
import kr.co.lion.mungnolza.ui.main.ui.chat.viewmodel.MainChatViewModel
import kr.co.lion.mungnolza.ui.main.util.ChatFragmentName
import kr.co.lion.mungnolza.util.BoardFragmentName


class MainChatFragment : Fragment() {

    lateinit var binding:FragmentMainChatBinding
    lateinit var chatActivity:ChatActivity
    lateinit var mainChatViewModel: MainChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_chat, container, false)
        mainChatViewModel = MainChatViewModel()
        binding.mainChatViewModel = mainChatViewModel
        binding.lifecycleOwner = this

        chatActivity = activity as ChatActivity

        setToolbar()
        setRecyclerViewMainChat()

        return binding.root
    }

    fun setToolbar(){
        binding.apply{
            toolbarMainChat.apply{
                title = "채팅"
                setNavigationIcon(R.drawable.ic_arrow_back_24px)

                inflateMenu(R.menu.menu_main_chat)
            }
        }
    }

    fun setRecyclerViewMainChat(){
        binding.apply {
            recyclerViewMainChat.apply {
                // 어뎁터
                adapter = RecyclerViewAdapterMainChat()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(chatActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(chatActivity, MaterialDividerItemDecoration.VERTICAL)
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
            return 100
        }

        override fun onBindViewHolder(holder: ViewHolderMainChat, position: Int) {
            holder.rowMainChatBinding.textViewContentRowMainChat.text = "나는 카리나 너는 호박 $position"
            holder.rowMainChatBinding.textViewNickNameRowMainChat.text = "최나연 $position"
            holder.rowMainChatBinding.textViewDateRowMainChat.text = "2024-04-01"
            holder.rowMainChatBinding.imageViewProfileRowMainChat.setImageResource(R.drawable.img_cny)

            holder.rowMainChatBinding.root.setOnClickListener {
                //chatActivity.replaceFragment(ChatFragmentName,true,true,null)
            }
        }
    }

}
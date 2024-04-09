package kr.co.lion.mungnolza.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentFreeBoardBinding
import kr.co.lion.mungnolza.databinding.RowFreeBoardBinding


class FreeBoardFragment : Fragment() {

    private var _binding: FragmentFreeBoardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFreeBoardBinding.inflate(inflater, container, false)


        setSearchBar()
        setRecyclerViewFreeBoard()
        setRecyclerViewSearchFreeBoard()


        return binding.root
    }


    fun setSearchBar(){
        binding.apply{
            searchBarFreeBoard.apply{
                // SearchBar에 보여줄 메시지
                hint = "여기를 눌러 검색해주세요"
                // 메뉴
                inflateMenu(R.menu.menu_search_free_board)

            }

            searchViewFreeBoard.apply{
                // SearchView에 보여줄 메시지
                hint = "검색어를 입력해주세요"
            }
        }
    }

    fun setRecyclerViewFreeBoard(){
        binding.apply {
            recyclerViewFreeBoard.apply {
                // 어뎁터
                adapter = RecyclerViewAdapterFreeBoard()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(requireContext())
                // 데코레이션
                val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
    // 검색 화면의 RecyclerView를 구성하는 메서드
    fun setRecyclerViewSearchFreeBoard(){
        binding.apply {
            recyclerViewSearchFreeBoard.apply {
                // 어뎁터
                adapter = RecyclerViewAdapterSearchFreeBoard()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(requireContext())
                // 데코레이션
                val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 메인 화면의 RecyclerView의 어뎁터
    inner class RecyclerViewAdapterFreeBoard : RecyclerView.Adapter<RecyclerViewAdapterFreeBoard.ViewHolderFreeBoard>(){
        inner class ViewHolderFreeBoard(rowFreeBoardBinding: RowFreeBoardBinding) : RecyclerView.ViewHolder(rowFreeBoardBinding.root){
            val rowFreeBoardBinding:RowFreeBoardBinding

            init {
                this.rowFreeBoardBinding = rowFreeBoardBinding

                this.rowFreeBoardBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFreeBoard {
            val rowMainBinding = RowFreeBoardBinding.inflate(layoutInflater)
            val mainViewHolder = ViewHolderFreeBoard(rowMainBinding)
            return mainViewHolder
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: ViewHolderFreeBoard, position: Int) {
            holder.rowFreeBoardBinding.textViewTitleFreeBoardRow.text = "제목 $position"
            holder.rowFreeBoardBinding.textViewNickNameFreeBoardRow.text = "작성자 $position"
            holder.rowFreeBoardBinding.textViewContentFreeBoardRow.text = "강아지 너무 귀엽죠!!\n참고로 암컷입니다!!! 남자 아닙니다!!"
            holder.rowFreeBoardBinding.textViewDateFreeBoardRow.text = "2024-03-26"
            holder.rowFreeBoardBinding.imageViewPhotoFreeBoardRow.setImageResource(R.drawable.img_dog)

            holder.rowFreeBoardBinding.root.setOnClickListener {
                //boardActivity.replaceFragment(BoardFragmentName.SHOW_DETAIL_BOARD_FRAGMENT,true,true,null)
            }
        }
    }

    // 검색 화면의 RecyclerView의 어뎁터
    inner class RecyclerViewAdapterSearchFreeBoard : RecyclerView.Adapter<RecyclerViewAdapterSearchFreeBoard.ViewHolderSearchFreeBoard>(){
        inner class ViewHolderSearchFreeBoard(rowMainBinding: RowFreeBoardBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val rowFreeBoardBinding:RowFreeBoardBinding

            init {
                this.rowFreeBoardBinding = rowMainBinding

                this.rowFreeBoardBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSearchFreeBoard {
            val rowMainBinding = RowFreeBoardBinding.inflate(layoutInflater)
            val searchViewHolder = ViewHolderSearchFreeBoard(rowMainBinding)
            return searchViewHolder
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: ViewHolderSearchFreeBoard, position: Int) {
            holder.rowFreeBoardBinding.textViewTitleFreeBoardRow.text = "제목 $position"
            holder.rowFreeBoardBinding.textViewNickNameFreeBoardRow.text = "작성자 $position"
        }
    }

}
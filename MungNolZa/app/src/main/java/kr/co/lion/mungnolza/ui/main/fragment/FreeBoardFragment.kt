package kr.co.lion.mungnolza.ui.main.fragment

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
import kr.co.lion.mungnolza.databinding.FragmentFreeBoardBinding
import kr.co.lion.mungnolza.databinding.RowFreeBoardBinding
import kr.co.lion.mungnolza.model.BoardModel
import kr.co.lion.mungnolza.ui.freeboard.BoardActivity
import kr.co.lion.mungnolza.ui.freeboard.viewmodel.FreeBoardViewModel
import kr.co.lion.mungnolza.util.BoardFragmentName

class FreeBoardFragment : Fragment() {

    private var _binding: FragmentFreeBoardBinding? = null
    private val binding get() = _binding!!

    lateinit var boardList:MutableList<BoardModel>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFreeBoardBinding.inflate(inflater, container, false)

        setSearchBar()
        setRecyclerViewFreeBoard()
        setRecyclerViewSearchFreeBoard()

        return binding.root
    }

    fun initData(){
        boardList = mutableListOf()
    }

    fun testData(){
        var boardImageList1 = mutableListOf<String>()
        var boardModel1 = BoardModel(1,"나는 멋쟁이 카리나",1, mutableListOf(""),1,"2024-04-03",1)
        var boardModel2 = BoardModel(1,"나는 멋쟁이 카리나",1, mutableListOf(""),1,"2024-04-03",1)

        boardList.add(boardModel1)
        boardList.add(boardModel2)
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
            return boardList.size
        }

        override fun onBindViewHolder(holder: ViewHolderFreeBoard, position: Int) {
            holder.rowFreeBoardBinding.textViewTitleFreeBoardRow.text = boardList[position].boardTitle
            holder.rowFreeBoardBinding.textViewNickNameFreeBoardRow.text = "최나연"
            holder.rowFreeBoardBinding.textViewContentFreeBoardRow.text = "거울에 왜 카리나가 있지?\nㅇㅅㅇ"
            holder.rowFreeBoardBinding.textViewDateFreeBoardRow.text = "2024-03-26"
            holder.rowFreeBoardBinding.imageViewPhotoFreeBoardRow.setImageResource(R.drawable.img_dog)

            // 게시판 하나의 글을 클릭 시 이벤트
            holder.rowFreeBoardBinding.root.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("boardIdx",boardList[position].boardIdx)

                //boardActivity.replaceFragment(BoardFragmentName.SHOW_DETAIL_BOARD_FRAGMENT,true,true,null)
                // 아이템 클릭 리스너
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
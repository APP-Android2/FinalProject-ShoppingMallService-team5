package kr.co.lion.mungnolza.ui.freeboard.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentModifyBoardBinding
import kr.co.lion.mungnolza.databinding.RowAddBoardBinding
import kr.co.lion.mungnolza.databinding.RowModifyBoardBinding
import kr.co.lion.mungnolza.model.BoardModel
import kr.co.lion.mungnolza.ui.freeboard.BoardActivity
import kr.co.lion.mungnolza.ui.freeboard.adapter.BoardCarouselAdapter
import kr.co.lion.mungnolza.ui.freeboard.adapter.BoardModifyCarouselAdapter
import kr.co.lion.mungnolza.ui.freeboard.viewmodel.ModifyBoardViewModel
import kr.co.lion.mungnolza.util.BoardFragmentName


class ModifyBoardFragment : Fragment() {
    lateinit var binding: FragmentModifyBoardBinding
    lateinit var boardActivity: BoardActivity

    lateinit var modifyBoardViewModel: ModifyBoardViewModel
    var boardData:BoardModel? =null
    var imageUriList:ArrayList<Uri?> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        boardActivity = activity as BoardActivity

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_modify_board,container,false)
        modifyBoardViewModel = ModifyBoardViewModel()
        binding.modifyBoardViewModel = modifyBoardViewModel
        binding.lifecycleOwner = this

        initData()
        setToolbar()
        setCarousel()
        setData()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    fun initData(){
        boardData = arguments?.getParcelable("boardData")!!
        imageUriList = arguments?.getParcelableArrayList("imageUriList")!!
    }

    fun setData(){
        binding.apply {
            editTextTitleModifyBoard.setText(boardData?.boardTitle)
            editTextContentModifyBoard.setText(boardData?.boardContent)
        }
    }

    fun setCarousel(){
        binding.apply{
            // RecyclerView 셋팅
            recyclerViewPhotosModifyBoard.apply{
                // 어댑터
                adapter = BoardModifyCarouselAdapter(imageUriList)
                // 레이아웃 매니저
                layoutManager = CarouselLayoutManager()
                // layoutManager = CarouselLayoutManager(MultiBrowseCarouselStrategy())
                // layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
                // layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())
            }
        }
    }
    fun setToolbar(){
        binding.toolbarModifyBoard.apply{

            setNavigationIcon(R.drawable.ic_arrow_back_24)
            setNavigationOnClickListener {
                boardActivity.removeFragment(BoardFragmentName.MODIFY_BOARD_FRAGMENT)
            }
            inflateMenu(R.menu.menu_modify_board)
            setOnMenuItemClickListener {
                when(it.itemId){
                    // 수정 완료 아이콘 클릭 이벤트
                    R.id.menuCompleteModifyBoard -> {
                        boardActivity.removeFragment(BoardFragmentName.MODIFY_BOARD_FRAGMENT)
                    }
                }
                true
            }
        }
    }


}
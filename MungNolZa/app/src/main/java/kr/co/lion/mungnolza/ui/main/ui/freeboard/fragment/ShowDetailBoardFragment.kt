package kr.co.lion.mungnolza.ui.freeboard.fragment

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentShowDetailBoardBinding
import kr.co.lion.mungnolza.ui.freeboard.BoardActivity
import kr.co.lion.mungnolza.ui.freeboard.viewmodel.ShowDetailBoardViewModel
import kr.co.lion.mungnolza.util.BoardFragmentName


class ShowDetailBoardFragment : Fragment() {

    lateinit var binding: FragmentShowDetailBoardBinding
    lateinit var boardActivity: BoardActivity

    lateinit var showDetailBoardViewModel: ShowDetailBoardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_show_detail_board,container,false)
        showDetailBoardViewModel = ShowDetailBoardViewModel()
        binding.showDetailBoardViewModel = showDetailBoardViewModel
        binding.lifecycleOwner = this

        boardActivity = activity as BoardActivity

        setToolbar()

        binding.buttonTest.setOnClickListener {
            showBottomCommentSheet()
        }



        return binding.root
    }


    // 툴바 설정
    fun setToolbar(){
        binding.apply{
            toolbarShowDetailBoard.apply{
                title = ""
                setNavigationIcon(R.drawable.ic_arrow_back_24)
                setNavigationOnClickListener {
                    // 백버튼 클릭 이벤트
                    boardActivity.removeFragment(BoardFragmentName.SHOW_DETAIL_BOARD_FRAGMENT)
                }

                inflateMenu(R.menu.menu_show_detail_board)

                setOnMenuItemClickListener {
                    when(it.itemId){
                        // 수정 아이콘 클릭 시
                        R.id.menuItemModifyShowDetailBoard -> {
                            boardActivity.replaceFragment(BoardFragmentName.MODIFY_BOARD_FRAGMENT,true,true,null)
                        }

                        // 삭제 아이콘 클릭 시
                        R.id.menuItemDeleteShowDetailBoard -> {
                            setDeleteDialog()
                        }
                    }
                    true
                }
            }
        }
    }

    fun setDeleteDialog(){
        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(requireContext())
        materialAlertDialogBuilder.apply{
            setTitle("삭제")
            setMessage("정말로 삭제하시겠습니까?")
            setPositiveButton("삭제"){ dialogInterface: DialogInterface, i: Int ->

            }
            setNegativeButton("닫기"){ dialogInterface: DialogInterface, i: Int ->

            }
            show()
        }
    }

    fun showBottomCommentSheet(){

        val bottomCommentFragment = BottomCommentFragment()
        bottomCommentFragment.show(boardActivity.supportFragmentManager, "BottomCommentSheet")


    }
}
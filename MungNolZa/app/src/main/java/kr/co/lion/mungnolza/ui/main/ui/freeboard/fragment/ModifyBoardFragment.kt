package kr.co.lion.mungnolza.ui.freeboard.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentModifyBoardBinding
import kr.co.lion.mungnolza.ui.freeboard.BoardActivity
import kr.co.lion.mungnolza.ui.freeboard.viewmodel.ModifyBoardViewModel
import kr.co.lion.mungnolza.util.BoardFragmentName


class ModifyBoardFragment : Fragment() {
    lateinit var binding: FragmentModifyBoardBinding
    lateinit var boardActivity: BoardActivity

    lateinit var modifyBoardViewModel: ModifyBoardViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        boardActivity = activity as BoardActivity

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_modify_board,container,false)
        modifyBoardViewModel = ModifyBoardViewModel()
        binding.modifyBoardViewModel = modifyBoardViewModel
        binding.lifecycleOwner = this

        setToolbar()


        return binding.root
    }

    fun setToolbar(){
        binding.toolbarModifyBoard.apply{
            title = "수정"

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
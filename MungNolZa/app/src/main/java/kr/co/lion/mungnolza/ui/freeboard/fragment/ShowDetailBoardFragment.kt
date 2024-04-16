package kr.co.lion.mungnolza.ui.freeboard.fragment

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentShowDetailBoardBinding
import kr.co.lion.mungnolza.model.BoardModel
import kr.co.lion.mungnolza.model.UserModel
import kr.co.lion.mungnolza.ui.freeboard.BoardActivity
import kr.co.lion.mungnolza.ui.freeboard.adapter.BoardCarouselAdapter
import kr.co.lion.mungnolza.ui.freeboard.viewmodel.BoardViewModel
import kr.co.lion.mungnolza.util.BoardFragmentName


class ShowDetailBoardFragment : Fragment() {

    private var _binding: FragmentShowDetailBoardBinding? = null
    private val binding get() = _binding!!

    lateinit var boardActivity: BoardActivity


    private val boardViewModel by viewModels<BoardViewModel>()

    var userData: UserModel? = null
    var boardData: BoardModel? = null

    var imagePathList = mutableListOf<String>()

    var boardIdx: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentShowDetailBoardBinding.inflate(layoutInflater)

        boardActivity = activity as BoardActivity

        setTest()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            boardData = boardActivity.intent.getParcelableExtra("boardData", BoardModel::class.java)
        } else {
            boardData = boardActivity.intent.getParcelableExtra<BoardModel>("boardData")
        }

        Log.d("ShowDetailBoardFragment intent 데이터", "${boardData?.boardImagePathList?.get(0)}")

        applyUserData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar()
        setCarousel()
        setCommentButton()

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    // ----------------------------------------------------------------------------

    fun setTest() {

    }

    suspend fun applyImage(){
        // binding.imageViewProfileShowDetailBoard.setImageURI()
    }
    fun applyUserData() {
        binding.editTextTitleShowDetailBoard.setText(boardData?.boardTitle)
        binding.editTextContentShowDetailBoard.setText(boardData?.boardContent)
        binding.textViewDateShowDetailBoard.text = boardData?.boardWriteDate


        // 유저 정보는 boardModel로 접근해야 하는데 테스트는 일단 직접 호출
    }


    fun setCommentButton() {
        binding.apply {
            imageViewCommentShowDetailBoard.setOnClickListener {
                showBottomCommentSheet()
            }
        }
    }

    fun setCarousel() {
        binding.apply {
            // RecyclerView 셋팅
            recyclerViewPhotosShowDetailBoard.apply {
                // 어댑터
                adapter = BoardCarouselAdapter()
                // 레이아웃 매니저
                layoutManager = CarouselLayoutManager()
                // layoutManager = CarouselLayoutManager(MultiBrowseCarouselStrategy())
                // layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
                // layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())
            }
        }
    }

    // 툴바 설정
    fun setToolbar() {
        binding.apply {
            toolbarShowDetailBoard.apply {

                setNavigationIcon(R.drawable.ic_arrow_back_24)
                setNavigationOnClickListener {
                    // 백버튼 클릭 이벤트
                    boardActivity.removeFragment(BoardFragmentName.SHOW_DETAIL_BOARD_FRAGMENT)
                }

                inflateMenu(R.menu.menu_show_detail_board)

                setOnMenuItemClickListener {
                    when (it.itemId) {
                        // 수정 아이콘 클릭 시
                        R.id.menuItemModifyShowDetailBoard -> {
                            val bundle = Bundle()
                            bundle.putParcelable("boardData",boardData)

                            boardActivity.replaceFragment(
                                BoardFragmentName.MODIFY_BOARD_FRAGMENT,
                                true,
                                true,
                                bundle
                            )
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

    fun setDeleteDialog() {
        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(requireContext())
        materialAlertDialogBuilder.apply {
            setTitle("삭제")
            setMessage("정말로 삭제하시겠습니까?")
            setPositiveButton("삭제") { dialogInterface: DialogInterface, i: Int ->

            }
            setNegativeButton("닫기") { dialogInterface: DialogInterface, i: Int ->

            }
            show()
        }
    }

    fun showBottomCommentSheet() {

        val bottomCommentFragment = BottomCommentFragment()

        bottomCommentFragment.apply {

        }

        bottomCommentFragment.show(boardActivity.supportFragmentManager, "BottomCommentSheet")
    }


}
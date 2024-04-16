package kr.co.lion.mungnolza.ui.freeboard.fragment

import android.content.DialogInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
<<<<<<< HEAD
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.data.repository.BoardRepository
import kr.co.lion.mungnolza.data.repository.BoardRepositoryImpl
=======
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.mungnolza.R
>>>>>>> 0e73214e0419402e5d3d00412ff2905432afe7a8
import kr.co.lion.mungnolza.databinding.FragmentShowDetailBoardBinding
import kr.co.lion.mungnolza.model.BoardModel
import kr.co.lion.mungnolza.model.UserModel
import kr.co.lion.mungnolza.repository.user.UserRepository
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl
import kr.co.lion.mungnolza.ui.freeboard.BoardActivity
import kr.co.lion.mungnolza.ui.freeboard.adapter.BoardCarouselAdapter
import kr.co.lion.mungnolza.ui.freeboard.viewmodel.BoardViewModel
import kr.co.lion.mungnolza.util.BoardFragmentName
import java.net.URI


class ShowDetailBoardFragment : Fragment() {

    private var _binding: FragmentShowDetailBoardBinding? = null
    private val binding get() = _binding!!

    lateinit var boardActivity: BoardActivity


    private val boardViewModel by viewModels<BoardViewModel>()

    var userData: UserModel? = null
    var boardData: BoardModel? = null
    var boardList: ArrayList<BoardModel>? = null

    lateinit var userRepository: UserRepository
    lateinit var boardRepository: BoardRepository
    var imgUri: URI? = null

    var imageUriList: MutableList<Uri?> = mutableListOf()

    var imagePathList = mutableListOf<String>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentShowDetailBoardBinding.inflate(layoutInflater)

        boardActivity = activity as BoardActivity

        setTest()

        lifecycleScope.launch {
            initData()
        }

        applyUserData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCarousel()

        setToolbar()
        setCommentButton()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    // ----------------------------------------------------------------------------

    suspend fun initData(){
        userRepository = UserRepositoryImpl()
        boardRepository = BoardRepositoryImpl()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            boardData = boardActivity.intent.getParcelableExtra("boardData", BoardModel::class.java)
            userData = boardActivity.intent.getParcelableExtra("userData", UserModel::class.java)
        } else {
            boardData = boardActivity.intent.getParcelableExtra<BoardModel>("boardData")
            userData = boardActivity.intent.getParcelableExtra<UserModel>("userData")
        }

        boardData?.boardImagePathList?.forEach {
            Log.d("게시판 boardImagePathList","${it}")
        }


        val job1 = CoroutineScope(Dispatchers.IO).launch {
            imgUri = userRepository.fetchUserProfileImage(userData?.userProfileImgPath!!)!!
            Log.d("프로필 이미지 uri","${imgUri}")


        }
        job1.join()

        val job2 = CoroutineScope(Dispatchers.IO).launch {
            boardList = boardRepository.getBoardList()
            imageUriList = boardRepository.getBoardImageUriList(boardData!!)
            imageUriList.forEach {
                Log.d("이미지 Uri 리스트",it.toString())
            }


        }
        job2.join()
    }
    fun setTest() {

    }


    fun applyUserData() {
        binding.apply{
            editTextTitleShowDetailBoard.setText(boardData?.boardTitle)
            editTextContentShowDetailBoard.setText(boardData?.boardContent)
            textViewDateShowDetailBoard.text = boardData?.boardWriteDate
            textViewNickNameShowDetailBoard.text = userData?.userNickname


            lifecycleScope.launch {
                delay(1000)
                Log.d("이미지 Uri 리스트 Glide 이전",imgUri.toString())
                Glide.with(requireContext())
                    .load(imgUri)
                    .error(R.drawable.eunwoo)
                    .into(binding.imageViewProfileShowDetailBoard)

                // boardRepository.applyBoardImage(requireContext(), boardData!!.boardImagePathList[0]!!, binding.imageViewProfileShowDetailBoard)
            }

        }
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
                adapter = BoardCarouselAdapter(imageUriList)
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
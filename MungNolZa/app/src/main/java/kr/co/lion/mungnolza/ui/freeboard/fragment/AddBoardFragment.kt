package kr.co.lion.mungnolza.ui.freeboard.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAddBoardBinding
import kr.co.lion.mungnolza.databinding.RowAddBoardBinding
import kr.co.lion.mungnolza.ui.freeboard.BoardActivity
import kr.co.lion.mungnolza.ui.freeboard.viewmodel.AddBoardViewModel
import kr.co.lion.mungnolza.util.BoardFragmentName
import kr.co.lion.mungnolza.util.BoardUtil


class AddBoardFragment : Fragment() {
    lateinit var binding: FragmentAddBoardBinding
    lateinit var boardActivity: BoardActivity

    lateinit var addBoardViewModel: AddBoardViewModel
    lateinit var boardImageList: MutableList<Bitmap>

    // Activity 실행을 위한 런처
    lateinit var albumLauncher: ActivityResultLauncher<Intent>

    // 이미지를 첨부한 적이 있는지...
    var isAddPicture = false

    lateinit var adapterAddBoard: RecyclerViewAdapterAddBoard

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_board, container, false)
        addBoardViewModel = AddBoardViewModel()
        binding.addBoardViewModel = addBoardViewModel
        binding.lifecycleOwner = this

        boardActivity = activity as BoardActivity

        initData()
        setToolbar()
        setAlbumLauncher()
        setImageViewEvent()
        // setCarousel()

        return binding.root
    }

    fun initData() {
        boardImageList = mutableListOf()
        adapterAddBoard = RecyclerViewAdapterAddBoard()
    }

    fun setCarousel() {
        binding.apply {
            // RecyclerView 셋팅
            recyclerViewPhotosAddBoard.apply {
                // 어댑터
                adapter = RecyclerViewAdapterAddBoard()
                // 레이아웃 매니저
                layoutManager = CarouselLayoutManager()
                // layoutManager = CarouselLayoutManager(MultiBrowseCarouselStrategy())
                // layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
                // layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())
            }
        }
    }

    fun setToolbar() {
        binding.apply {
            toolbarAddBoard.apply {
                title = "자유게시판"
                setNavigationIcon(R.drawable.ic_arrow_back_24)
                // 백버튼 이벤트
                setNavigationOnClickListener {
                    boardActivity.removeFragment(BoardFragmentName.ADD_BOARD_FRAGMENT)
                }
                inflateMenu(R.menu.menu_add_board)

                setOnMenuItemClickListener {
                    when (it.itemId) {
                        // 완료 버튼 이벤트
                        R.id.menuCompleteAddBoard -> {

                        }
                    }
                    true
                }

            }
        }
    }

    fun setImageViewEvent() {
        binding.apply {
            imageViewPhotoAddBoard.apply {
                setOnClickListener {
                    startAlbumLauncher()
                }
            }
        }
    }

    // 앨범 런처 설정
    fun setAlbumLauncher() {
        // 앨범 실행을 위한 런처
        val contract = ActivityResultContracts.StartActivityForResult()
        albumLauncher = registerForActivityResult(contract) {
            // 사진을 선택하고 돌아왔다면
            if (it.resultCode == AppCompatActivity.RESULT_OK) {
                // 선택한 이미지의 경로 데이터를 관리하는 Uri 객체를 추출한다.
                for (i in 0 until it.data?.clipData!!.itemCount) {
                    val uri = it.data?.clipData!!.getItemAt(i).uri
                    Log.d("이미지 uri", uri.toString())
                    if (uri != null) {
                        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            // 이미지를 생성할 수 있는 객체를 생성한다.
                            val source =
                                ImageDecoder.createSource(boardActivity.contentResolver, uri)
                            // Bitmap을 생성한다.
                            ImageDecoder.decodeBitmap(source)
                        } else {
                            // 컨텐츠 프로바이더를 통해 이미지 데이터에 접근한다.
                            val cursor =
                                boardActivity.contentResolver.query(uri, null, null, null, null)
                            if (cursor != null) {
                                cursor.moveToNext()

                                // 이미지의 경로를 가져온다.
                                val idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                                val source = cursor.getString(idx)

                                // 이미지를 생성한다.
                                BitmapFactory.decodeFile(source)
                            } else {
                                null
                            }
                        }

                        // 회전 각도값을 가져온다.
                        val degree = BoardUtil.getDegree(boardActivity, uri)
                        // 회전 이미지를 가져온다.
                        val bitmap2 = BoardUtil.rotateBitmap(bitmap!!, degree.toFloat())
                        // 크기를 줄인 이미지를 가져온다.
                        val bitmap3 = BoardUtil.resizeBitmap(bitmap2, 1024)

                        boardImageList.add(bitmap3)

                    }

                    isAddPicture = true

                    Log.d("리스트 이미지", "${boardImageList.size}")
                    //adapterAddBoard.notifyDataSetChanged()

                    SystemClock.sleep(100)

                    setCarousel()
                }
            }
        }


    }


    // 앨범 런처를 실행하는 메서드
    fun startAlbumLauncher() {
        // 앨범에서 사진을 선택할 수 있도록 세팅된 인텐트를 생성한다.
        val albumIntent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        // 실행할 액티비티의 타입을 설정(이미지를 선택할 수 있는 것이 뜨게 한다.)

        albumIntent.setType("image/*")
        // 선택할 수 있는 파일들의 MimeType을 설정한다.
        // val mimeType = arrayOf("image/*")
        // albumIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)

        albumIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        // 액티비티를 실행한다.
        albumLauncher.launch(albumIntent)
    }


    inner class RecyclerViewAdapterAddBoard :
        RecyclerView.Adapter<RecyclerViewAdapterAddBoard.ViewHolderAddBoard>() {
        inner class ViewHolderAddBoard(rowAddBoardBinding: RowAddBoardBinding) :
            RecyclerView.ViewHolder(rowAddBoardBinding.root) {
            val rowAddBoardBinding: RowAddBoardBinding

            init {
                this.rowAddBoardBinding = rowAddBoardBinding
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAddBoard {
            val rowAddBoardBinding = RowAddBoardBinding.inflate(layoutInflater)
            val viewHolderAddBoard = ViewHolderAddBoard(rowAddBoardBinding)
            return viewHolderAddBoard
        }

        override fun getItemCount(): Int = boardImageList.size

        override fun onBindViewHolder(holder: ViewHolderAddBoard, position: Int) {
            Log.d("이미지 onBindViewHolder", boardImageList.size.toString())
            holder.rowAddBoardBinding.imageViewCarouselAddBoard.setImageBitmap(boardImageList[position])
            holder.rowAddBoardBinding.buttonCloseRowAddBoard.setOnClickListener {
                boardImageList.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }
}
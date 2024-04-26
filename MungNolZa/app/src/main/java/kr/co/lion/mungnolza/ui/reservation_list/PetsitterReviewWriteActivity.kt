package kr.co.lion.mungnolza.ui.reservation_list

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityPetsitterReviewWriteBinding
import kr.co.lion.mungnolza.ext.hideSoftInput
import kr.co.lion.mungnolza.ext.showErrorDialog
import kr.co.lion.mungnolza.ui.reservation_list.viewmodel.PetSitterReviewWriteViewModel
import java.text.SimpleDateFormat
import java.util.Date
import kr.co.lion.mungnolza.repository.PetsitterReviewRepository

class PetsitterReviewWriteActivity : AppCompatActivity() {

    lateinit var activityPetsitterReviewWriteBinding: ActivityPetsitterReviewWriteBinding

    lateinit var petSitterReviewWriteViewModel : PetSitterReviewWriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityPetsitterReviewWriteBinding = ActivityPetsitterReviewWriteBinding.inflate(layoutInflater)
        setContentView(activityPetsitterReviewWriteBinding.root)

        // ViewModel 객체를 생성한다.
        petSitterReviewWriteViewModel = PetSitterReviewWriteViewModel()
        // 생성한 ViewModel 객체를 layout 파일에 설정해준다.
        activityPetsitterReviewWriteBinding.petSitterReviewWriteViewModel = petSitterReviewWriteViewModel
        // ViewModel의 생명주기를 Fragment와 일치시킨다. Fragment가 살아 있을 때 ViewModel 객체도 살아 있게끔 해준다.
        activityPetsitterReviewWriteBinding.lifecycleOwner = this

        setToolbar()
        setButtonDone()
        setTextField()
        initData()
    }

    // 툴바
    fun setToolbar(){
        activityPetsitterReviewWriteBinding.apply {
            toolbarPetSitterReviewWrite.apply {
                setNavigationIcon(R.drawable.ic_arrow_back_24)
                setNavigationOnClickListener {
                    // 수정해야함
                }
            }
        }
    }

    // 작성 완료 버튼 (이벤트 설정)
    fun setButtonDone(){
        // buttonPetsitterReviewWriteDone 작성완료 버튼 설정
        activityPetsitterReviewWriteBinding.apply {
            buttonPetsitterReviewWriteDone.apply {
                // 버튼을 눌럿을 때
                setOnClickListener {
                    // 입력을 검사한다. -유효성 검사
                    val chk = checkTextInput()

                    // 입력이 모두 잘 되어 있다면..
                    // 데이터를 저장하고 이동한다.
                    if(chk == true){
                        // (추가예정) 사용자가 입력한 데이터를 저장한다.
                        savePetsitterReviewData()

                        // 키보드를 내려준다
                        context.hideSoftInput(this@PetsitterReviewWriteActivity)

                        // 작성완료 다이어로그를 띄운다
                        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(this@PetsitterReviewWriteActivity)
                        materialAlertDialogBuilder.setTitle("작성 완료")
                        materialAlertDialogBuilder.setMessage("후기 작성이 완료되었습니다 !")
                        materialAlertDialogBuilder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                            // (추가예정) 메인화면으로 넘어간다.
                        }
                        materialAlertDialogBuilder.show()
                    }
                }
            }
        }
    }

    // 데잍
    fun savePetsitterReviewData() {
        CoroutineScope(Dispatchers.Main).launch {
            // 펫시터 후기 번호 시퀀스 값을 가져온다.
            val reviewIdxSequence = PetsitterReviewRepository.getReviewIdx()
            Log.d("test1234", "reviewIdxSequence : $reviewIdxSequence")
            // 시퀀스 값을 1 증가시켜 덮어씌워준다.
            PetsitterReviewRepository.updateReviewIdx(reviewIdxSequence + 1)

            // 저장할 데이터를 가져온다
            val reviewIdx = reviewIdxSequence + 1
            //val petsitterIdx
            //val reviewWriterIdx
            //val reviewWriterName
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            val reviewWriteDate = simpleDateFormat.format(Date())
            val reviewStarCount = petSitterReviewWriteViewModel.ratingBar2.value
            val reviewText = petSitterReviewWriteViewModel.textfieldPetsitterReviewWrite.value!!

            // 저장할 데이터를 객체에 담는다.
            //val petsitterReviewModel = PetsitterReviewModel(reviewIdx, 0, 0, reviewWriteDate, reviewStarCount, reviewText)

            // 펫시터 후기 정보를 저장한다.
            //PetsitterReviewRepository.insertPetsitterReviewData(petsitterReviewModel)
        }
    }

    // 입력요소 유효성 검사 메서드
    fun checkTextInput(): Boolean {
        // 사용자가 입력한 내용을 가져온다
        val starCount = petSitterReviewWriteViewModel.ratingBar2.value
        val reviewText = petSitterReviewWriteViewModel.textfieldPetsitterReviewWrite.value!!

        // 별점수를 입력하지 않았다면
        if(starCount == 0f){
            // 다이어로그 띄워준다
            showErrorDialog(this@PetsitterReviewWriteActivity, activityPetsitterReviewWriteBinding.ratingBar2, "별점 입력 오류", "펫시터에게 별점을 남겨주세요.")
            return false
        }

        // 후기 내용을 입력하지 않았다면
        if(reviewText.isEmpty()){
            // 다이어로그 띄워준다
            // 다이어로그의 확인 클릭시, 뷰에 포커스를 주고 키보드를 올린다.
            showErrorDialog(this@PetsitterReviewWriteActivity, activityPetsitterReviewWriteBinding.textfieldPetsitterReviewWrite, "후기 작성 오류", "펫시터에게 후기를 작성해주세요.")
            return false
        }
        return true
    }

    // 입력요소 초기설정
    fun setTextField(){
        // 입력 요소들을 초기화 한다.
        petSitterReviewWriteViewModel.ratingBar2.value = 0f
        petSitterReviewWriteViewModel.textfieldPetsitterReviewWrite.value = ""
    }


    // 임시 가짜 데이터 셋팅
    fun initData(){
        activityPetsitterReviewWriteBinding.apply {
            imageViewPetSitterReviewWrite.setImageResource(R.drawable.image_man1)
            textViewPetSitterReviewWriteName.setText("정찬호 펫시터")
            textViewPetSitterReviewWriteScore.setText("4.5점")
            ratingBarPetSitterReviewWrite.rating = 4.5f
            textViewPetSitterReviewWriteCountReview.setText("89개의 후기")
        }
    }

}
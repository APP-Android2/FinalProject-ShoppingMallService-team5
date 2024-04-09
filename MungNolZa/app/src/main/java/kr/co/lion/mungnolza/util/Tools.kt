package kr.co.lion.mungnolza.util

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.concurrent.thread

class Tools {
    companion object{

        // 뷰에 포커스를 주고 키보드를 올린다.
        fun showSoftInput(context: Context, view: View){
            // 뷰에 포커스를 준다.
            view.requestFocus()
            thread {
                // 딜레이
                SystemClock.sleep(200)
                // 키보드 관리 객체를 가져온다.
                val inputMethodManger = context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                // 키보드를 올린다.
                inputMethodManger.showSoftInput(view, 0)
            }
        }
        // 키보드를 내려주고 포커스를 제거한다.
        fun hideSoftInput(activity: Activity){
            // 포커스를 가지고 있는 뷰가 있다면..
            if(activity.window.currentFocus != null){
                // 키보드 관리 객체를 가져온다.
                val inputMethodManger = activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as
                        InputMethodManager
                // 키보드를 내려준다.
                inputMethodManger.hideSoftInputFromWindow(activity.window.currentFocus?.windowToken, 0)
                // 포커스를 제거해준다.
                activity.window.currentFocus?.clearFocus()
            }
        }
        // 입력 요소가 비어있을때 보여줄 다이얼로그를 구성하는 메서드
        fun showErrorDialog(context: Context, view: View, title:String, message:String){
            val materialAlertDialogBuilder = MaterialAlertDialogBuilder(context)
            materialAlertDialogBuilder.setTitle(title)
            materialAlertDialogBuilder.setMessage(message)
            materialAlertDialogBuilder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                showSoftInput(context, view)
            }
            materialAlertDialogBuilder.show()
        }
    }
}

// MainFragment 에서 보여줄 프래그먼트들의 이름
enum class MatchingPetsitterFragmentName(var str:String){
    // 매칭화면
    MATCHING_FRAGMENT("MatchingFragment"),
    // 펫시터 프로필 화면
    PETSITTER_INFO_FRAGMENT("PetSitterInfoFragment"),
    // 펫시터 리뷰 목록 화면
    PETSITTER_REVIEW_FRAGMENT("PetSitterReviewFragment"),
    // 결제 화면
    PAYMENT_FRAGMENT("PaymentFragment"),
    // 예약 확정 화면
    RESERVATION_CONFIRMED_FRAGMENT("ReservationConfirmedFragment"),
}

enum class ReservationListFragmentName(var str:String){
    // 상위-예약 목록 화면
    RESERVATION_LIST_FRAGMENT("ReservationListFragment"),
    // 펫시터 후기 작성 화면
    PETSITTER_REVIEW_WRITE_FRAGMENT("PetSitterReviewWriteFragment"),
}

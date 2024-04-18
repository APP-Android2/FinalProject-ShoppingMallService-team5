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

class ProfileUtil {

    companion object{
        // 뷰에 포커스를 주고 키보드를 올린다.
        fun showSoftInput(context: Context, view: View) {
            // 뷰에 포커스를 준다.
            view.requestFocus()
            thread {
                // 딜레이
                SystemClock.sleep(200)
                // 키보드 관리 객체를 가져온다.
                val inputMethodManger =
                    context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                // 키보드를 올린다.
                inputMethodManger.showSoftInput(view, 0)
            }
        }

        // 키보드를 내려주고 포커스를 제거한다.
        fun hideSoftInput(activity: Activity) {
            // 포커스를 가지고 있는 뷰가 있다면..
            if (activity.window.currentFocus != null) {
                // 키보드 관리 객체를 가져온다.
                val inputMethodManger =
                    activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                // 키보드를 내려준다.
                inputMethodManger.hideSoftInputFromWindow(
                    activity.window.currentFocus?.windowToken,
                    0
                )
                // 포커스를 제거해준다.
                activity.window.currentFocus?.clearFocus()
            }
        }

        // 입력 요소가 비어있을때 보여줄 다이얼로그를 구성하는 메서드
        fun showErrorDialog(context: Context, view: View, title: String, message: String) {
            val materialAlertDialogBuilder = MaterialAlertDialogBuilder(context)
            materialAlertDialogBuilder.setTitle(title)
            materialAlertDialogBuilder.setMessage(message)
            materialAlertDialogBuilder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                showSoftInput(context, view)
            }
            materialAlertDialogBuilder.show()
        }
    }
}

// ProfileActivity에서 보여줄 Fragment들의 이름
enum class ProfileFragmentName(var str:String){
    SETTING_FRAGMENT("SettingFragment"),
    USER_PROFILE_FRAGMENT("UserProfileFragment"),
    PET_PROFILE_LIST_FRAGMENT("PetProfileListFragment"),
    PET_PROFILE_FRAGMENT("PetProfileFragment"),
    ADD_PET_PROFILE_FRAGMENT("AddPetProfileFragment"),
    EDIT_PET_PROFILE_FRAGMENT("EditPetProfileFragment"),
    EDIT_USER_PROFILE_FRAGMENT("EditUserProfileFragment"),
    FOLLOW_PET_SITTER_FRAGMENT("FollowPetSitterFragment"),
    NOTICE_FRAGMENT("NoticeFragment"),
    INPUT_PET_SITTER_FRAGMENT("InputPetSitterFragment"),
}
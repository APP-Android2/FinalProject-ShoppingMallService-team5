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
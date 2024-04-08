package kr.co.lion.mungnolza.ext

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.mungnolza.R

fun Context.setColorWhite() = ContextCompat.getColorStateList(this, R.color.white)
fun Context.setColorBlack() = ContextCompat.getColorStateList(this, R.color.black)
fun Context.setColorkakaoYellow() = ContextCompat.getColorStateList(this, R.color.kakao_color)
fun Context.setColorPrimary() = ContextCompat.getColorStateList(this, R.color.colorPrimary2)
fun Context.setSelectTimeButtonColor() = ContextCompat.getColorStateList(this, R.color.v)
fun Context.setColorGreenBlue() = ContextCompat.getColorStateList(this, R.color.green_blue)

// 키보드를 내려주고 포커스를 제거한다.
fun Context.hideSoftInput(activity: Activity) {
    // 포커스를 가지고 있는 뷰가 있다면..
    if (activity.window.currentFocus != null) {
        // 키보드 관리 객체를 가져온다.
        val inputMethodManger =
            activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        // 키보드를 내려준다.
        inputMethodManger.hideSoftInputFromWindow(activity.window.currentFocus?.windowToken, 0)
        // 포커스를 제거해준다.
        activity.window.currentFocus?.clearFocus()
    }
}

// 뷰에 포커스를 주고 키보드를 올린다.
fun Context.showSoftInput(view: View) {
    // 뷰에 포커스를 준다.
    view.requestFocus()

    // 키보드 관리 객체를 가져온다.
    val inputMethodManger =
        this.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    // 키보드를 올린다.
    inputMethodManger.showSoftInput(view, 0)
}


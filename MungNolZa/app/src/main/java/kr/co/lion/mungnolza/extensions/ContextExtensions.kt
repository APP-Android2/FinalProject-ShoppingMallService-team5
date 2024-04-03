package kr.co.lion.mungnolza.extensions

import android.content.Context
import android.util.TypedValue
import androidx.core.content.ContextCompat
import kr.co.lion.mungnolza.R

fun Context.setAppointmentButtonColor() = ContextCompat.getColorStateList(this, R.color.colorPrimary)
fun Context.setColorWhite() = ContextCompat.getColorStateList(this, R.color.white)
fun Context.setColorPrimary() = ContextCompat.getColorStateList(this, R.color.colorPrimary2)



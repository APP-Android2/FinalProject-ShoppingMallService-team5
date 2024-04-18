package kr.co.lion.mungnolza.ext

import androidx.fragment.app.FragmentManager
import kr.co.lion.mungnolza.ui.dialog.PositiveCustomDialog

fun FragmentManager.showDialog(title: String, message: String){
        val dialog = PositiveCustomDialog(
            title = title,
                    message = message,
                    positiveButtonClick = { }
        )
        dialog.show(this, "PositiveCustomDialog")
}
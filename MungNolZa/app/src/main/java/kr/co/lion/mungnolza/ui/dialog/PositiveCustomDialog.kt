package kr.co.lion.mungnolza.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.PositiveCustomDialogBinding

class PositiveCustomDialog(
    private val title: String,
    private val message: String,
    private val positiveButtonClick: () -> Unit
) : DialogFragment(R.layout.positive_custom_dialog) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.CustomPositiveDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = PositiveCustomDialogBinding.bind(view)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        with(binding){
            tvTitle.text = title
            tvMessage.text = message
            btnDone.setOnClickListener {
                positiveButtonClick.invoke()
                dismiss()
            }
        }
    }
}

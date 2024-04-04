package kr.co.lion.mungnolza.ui.intro

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kr.co.lion.mungnolza.databinding.PermissionDialogBinding

class RequestPermissionDialog(private val buttonClick: ()-> Unit): DialogFragment() {
    private var _binding: PermissionDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = PermissionDialogBinding.inflate(inflater, container, false)

        val width = (resources.displayMetrics.widthPixels * 0.8).toInt()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.btnNext.setOnClickListener {
            buttonClick.invoke()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

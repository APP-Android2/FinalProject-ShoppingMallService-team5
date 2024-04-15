package kr.co.lion.mungnolza.ui.dialog

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

        initView()

        return binding.root
    }

    private fun initView(){
        binding.btnNext.setOnClickListener {
            buttonClick.invoke()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

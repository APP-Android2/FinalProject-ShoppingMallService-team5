package kr.co.lion.mungnolza.ui.profile.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentDeleteAccountDialogBinding
import kr.co.lion.mungnolza.databinding.FragmentDeleteFollowPetSitterDialogBinding
import kr.co.lion.mungnolza.ui.profile.ProfileActivity

class DeleteFollowPetSitterDialogFragment : DialogFragment() {
    private var _binding: FragmentDeleteFollowPetSitterDialogBinding? = null
    private val binding get() = _binding!!
    lateinit var profileActivity: ProfileActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDeleteFollowPetSitterDialogBinding.inflate(inflater, container, false)
        profileActivity = activity as ProfileActivity

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val width = (resources.displayMetrics.widthPixels * 0.8).toInt()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.buttonDeleteFollowPetSitterDialogConfirm.setOnClickListener {
            dismiss()
        }
        binding.buttonDeleteFollowPetSitterDialogCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
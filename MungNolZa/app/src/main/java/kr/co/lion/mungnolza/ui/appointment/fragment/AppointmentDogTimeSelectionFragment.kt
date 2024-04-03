package kr.co.lion.mungnolza.ui.appointment.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentDogTimeSelectionBinding
import kr.co.lion.mungnolza.ui.main.MainActivity
import kr.co.lion.mungnolza.ui.main.MainFragmentName


class AppointmentDogTimeSelectionFragment : Fragment() {

    private var _binding: FragmentAppointmentDogTimeSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{

        _binding = FragmentAppointmentDogTimeSelectionBinding.inflate(inflater)

        settingupDogTimeButtons()

        return binding.root
    }

    private fun settingupDogTimeButtons() {
        val selectedButtonColor = ContextCompat.getColor(requireContext(), R.color.selectedButtonColor) // 선택된 버튼 색상
        val defaultButtonColor = ContextCompat.getColor(requireContext(), R.color.defaultButtonColor) // 기본 버튼 색상

        binding.apply {
//            selecttimefordoguntil.setOnClickListener {
//                // 일반 방문 버튼이 선택되었을 때
//                it.backgroundTintList = ColorStateList.valueOf(selectedButtonColor)
//                selecttimefordogthirtymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
//                selecttimefordogsixtymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
//                selecttimefordogonetwentymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
//            }
//
//            selecttimefordogthirtymin.setOnClickListener {
//                // 정기 방문 버튼이 선택되었을 때
//                it.backgroundTintList = ColorStateList.valueOf(selectedButtonColor)
//                selecttimefordoguntil.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
//                selecttimefordogsixtymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
//                selecttimefordogonetwentymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
//            }
//
//            selecttimefordogsixtymin.setOnClickListener {
//                // 정기 방문 버튼이 선택되었을 때
//                it.backgroundTintList = ColorStateList.valueOf(selectedButtonColor)
//                selecttimefordoguntil.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
//                selecttimefordogthirtymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
//                selecttimefordogonetwentymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
//            }
//
//            selecttimefordogonetwentymin.setOnClickListener {
//                // 정기 방문 버튼이 선택되었을 때
//                it.backgroundTintList = ColorStateList.valueOf(selectedButtonColor)
//                selecttimefordoguntil.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
//                selecttimefordogthirtymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
//                selecttimefordogsixtymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
//            }
        }
    }
}



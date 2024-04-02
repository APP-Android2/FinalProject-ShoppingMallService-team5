package kr.co.lion.mungnolza.ui.main.appointment.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentDogTimeSelectionBinding
import kr.co.lion.mungnolza.ui.main.MainFragmentName
import kr.co.lion.mungnolza.ui.main.appointment.AppointmentActivity
import kr.co.lion.mungnolza.ui.main.showAlertDialog


class AppointmentDogTimeSelectionFragment : Fragment() {

    lateinit var appointmentActivity: AppointmentActivity

    private var _binding: FragmentAppointmentDogTimeSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{

        _binding = FragmentAppointmentDogTimeSelectionBinding.inflate(inflater)
        appointmentActivity = activity as AppointmentActivity

        settingupDogTimeButtons()
        settingDogTimeNextButton()

        return binding.root
    }

    private fun settingupDogTimeButtons() {
        val selectedButtonColor = ContextCompat.getColor(requireContext(), R.color.selectedButtonColor) // 선택된 버튼 색상
        val defaultButtonColor = ContextCompat.getColor(requireContext(), R.color.defaultButtonColor) // 기본 버튼 색상

        binding.apply {
            selecttimefordoguntil.setOnClickListener {
                // 일반 방문 버튼이 선택되었을 때
                it.backgroundTintList = ColorStateList.valueOf(selectedButtonColor)
                selecttimefordogthirtymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
                selecttimefordogsixtymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
                selecttimefordogonetwentymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
            }

            selecttimefordogthirtymin.setOnClickListener {
                // 정기 방문 버튼이 선택되었을 때
                it.backgroundTintList = ColorStateList.valueOf(selectedButtonColor)
                selecttimefordoguntil.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
                selecttimefordogsixtymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
                selecttimefordogonetwentymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
            }

            selecttimefordogsixtymin.setOnClickListener {
                // 정기 방문 버튼이 선택되었을 때
                it.backgroundTintList = ColorStateList.valueOf(selectedButtonColor)
                selecttimefordoguntil.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
                selecttimefordogthirtymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
                selecttimefordogonetwentymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
            }

            selecttimefordogonetwentymin.setOnClickListener {
                // 정기 방문 버튼이 선택되었을 때
                it.backgroundTintList = ColorStateList.valueOf(selectedButtonColor)
                selecttimefordoguntil.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
                selecttimefordogthirtymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
                selecttimefordogsixtymin.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
            }
        }
    }

    fun settingDogTimeNextButton() {
        binding.apply {
            buttonappointmentselecttimenext.setOnClickListener {
                var isAllSelected = true

                // 예약 유형 중 하나라도 선택되지 않았는지 확인
                if (!selecttimefordoguntil.isSelected && !selecttimefordogthirtymin.isSelected && !selecttimefordogsixtymin.isSelected && !selecttimefordogonetwentymin.isSelected) {
                    showAlertDialog("시간을 선택해 주세요.")
                    isAllSelected = false
                }

                // 모든 조건을 충족시키면 다음 화면으로 넘어감
                if (isAllSelected) {
                    appointmentActivity.replaceFragment(
                        MainFragmentName.APPOINTMENT_USER_ADDRESS, true, true, null)
                }
            }
        }
    }
}



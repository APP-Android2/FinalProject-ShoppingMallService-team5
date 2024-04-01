package kr.co.lion.mungnolza.ui.main.fragment

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

    lateinit var fragmentAppointmentDogTimeSelectionBinding: FragmentAppointmentDogTimeSelectionBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        // Inflate the layout for this fragment

        fragmentAppointmentDogTimeSelectionBinding = FragmentAppointmentDogTimeSelectionBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingDogtimeToolbar()
        settingDogTimeNextButton()
        settingupDogTimeButtons()

        return fragmentAppointmentDogTimeSelectionBinding.root
    }

    // 툴바 설정
    fun settingDogtimeToolbar(){
        fragmentAppointmentDogTimeSelectionBinding.apply {
            toolbarDogTime.apply {
                // 타이틀
                title = "예약"
                // Back
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                setNavigationOnClickListener {
                    // 이전 화면으로 간다.
                    mainActivity.removeFragment(MainFragmentName.APPOINTMENT_DOG_TIME_SELECTION)
                }
            }
        }
    }

    fun settingDogTimeNextButton(){
        fragmentAppointmentDogTimeSelectionBinding.apply {
            buttonappointmentselecttimenext.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    mainActivity.replaceFragment(MainFragmentName.APPOINTMENT_USER_ADDRESS, true, true, null)
                }
            }
        }
    }

    fun settingupDogTimeButtons() {
        val selectedButtonColor = ContextCompat.getColor(requireContext(), R.color.selectedButtonColor) // 선택된 버튼 색상
        val defaultButtonColor = ContextCompat.getColor(requireContext(), R.color.defaultButtonColor) // 기본 버튼 색상

        fragmentAppointmentDogTimeSelectionBinding.apply {
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
}



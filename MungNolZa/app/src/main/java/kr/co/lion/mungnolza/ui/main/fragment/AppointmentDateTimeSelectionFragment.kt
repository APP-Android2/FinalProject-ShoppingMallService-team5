package kr.co.lion.mungnolza.ui.main.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.core.content.ContextCompat
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.aminography.primedatepicker.picker.callback.MultipleDaysPickCallback
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentDateTimeSelectionBinding
import kr.co.lion.mungnolza.ui.main.MainActivity
import kr.co.lion.mungnolza.ui.main.MainFragmentName


class AppointmentDateTimeSelectionFragment : Fragment() {

    lateinit var fragmentAppointmentDateTimeSelectionBinding: FragmentAppointmentDateTimeSelectionBinding
    lateinit var mainActivity: MainActivity


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentAppointmentDateTimeSelectionBinding = FragmentAppointmentDateTimeSelectionBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingDateTimeToolbar()
        setupSelectTimeButton()
        settingDateTimeNextButton()
        settingupDayButton()
        settingupButtons()

        return fragmentAppointmentDateTimeSelectionBinding.root
    }

    fun settingDateTimeToolbar(){
        fragmentAppointmentDateTimeSelectionBinding.apply {
            materialToolbar5.apply {
                // 타이틀
//                title = "예약"
                // Back
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainFragmentName.APPOINTMENT_DATE_TIME_SELECTION)
                }
            }
        }
    }

    fun setupSelectTimeButton() {
        // View Binding을 사용하여 뷰에 접근
        fragmentAppointmentDateTimeSelectionBinding.apply {
            buttonplustime.setOnClickListener {
                // 버튼을 눌렀을 때 TimePicker를 표시하는 메서드 호출
                showMaterialTimePicker()

            }
        }
    }


    fun settingDateTimeNextButton(){
        fragmentAppointmentDateTimeSelectionBinding.apply {
            buttonAppointmentCalendarNext.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    mainActivity.replaceFragment(MainFragmentName.APPOINTMENT_REQUEST, true, true, null)
                }
            }
        }
    }

    fun settingupDayButton() {
        // View Binding을 사용하여 뷰에 접근, buttonAppointmentOneday 버튼에 클릭 리스너 설정
        fragmentAppointmentDateTimeSelectionBinding.buttonappointmentCalendarSelectDate.apply {
            setOnClickListener {
                // 캘린더 설정 메서드 호출
                calendarSettingForMultipleDates()
            }
        }
    }

    fun settingupButtons() {
        val selectedButtonColor = ContextCompat.getColor(requireContext(), R.color.selectedButtonColor) // 선택된 버튼 색상
        val defaultButtonColor = ContextCompat.getColor(requireContext(), R.color.defaultButtonColor) // 기본 버튼 색상

        fragmentAppointmentDateTimeSelectionBinding.apply {
            buttonAppointmentOneday.setOnClickListener {
                // 일반 방문 버튼이 선택되었을 때
                it.backgroundTintList = ColorStateList.valueOf(selectedButtonColor)
                buttonAppointmentEveryweek.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
                // 선택된 버튼에 따른 추가 액션 처리
            }

            buttonAppointmentEveryweek.setOnClickListener {
                // 정기 방문 버튼이 선택되었을 때
                it.backgroundTintList = ColorStateList.valueOf(selectedButtonColor)
                buttonAppointmentOneday.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
                // 다른 프래그먼트로 이동
                navigateToAnotherFragment()
            }
        }
    }

    fun navigateToAnotherFragment() {
        // 여기에 프래그먼트 교체
    }

    fun showMaterialTimePicker() {
        // MaterialTimePicker Builder를 사용하여 인스턴스 생성
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12) // 초기 시간 설정
            .setMinute(0) // 초기 분 설정
            .setTitleText("시간 선택")
            .build()

        // TimePickerDialog 표시
        picker.show(parentFragmentManager, picker.toString())

        // 사용자가 시간을 선택하고 확인 버튼을 누른 후의 리스너 설정
        picker.addOnPositiveButtonClickListener {
            // 사용자가 선택한 시간을 EditText에 표시
            val selectedTime = String.format("%02d:%02d", picker.hour, picker.minute)
            fragmentAppointmentDateTimeSelectionBinding.editTextVisitTime.setText(selectedTime)
            fragmentAppointmentDateTimeSelectionBinding.editTextVisitTime.setText(selectedTime)

            // 여기에 CheckBox의 가시성을 변경하는 코드를 추가
            fragmentAppointmentDateTimeSelectionBinding.checkBoxTime.visibility = View.VISIBLE
            fragmentAppointmentDateTimeSelectionBinding.checkBoxCan.visibility = View.VISIBLE
            fragmentAppointmentDateTimeSelectionBinding.checkBoxCant.visibility = View.VISIBLE
        }
    }


    fun calendarSettingForMultipleDates() {
        val today = CivilCalendar()

        val datePicker = PrimeDatePicker.bottomSheetWith(today)
            .pickMultipleDays { selectedDays ->
                // 여러 날짜 처리 로직
                val formattedDates = selectedDays.map { day ->
                    // 각 날짜를 원하는 형식으로 포맷
                    "${day.month + 1}월 ${day.dayOfMonth}일"
                }.joinToString(separator = ", ") // 선택된 모든 날짜를 하나의 문자열로 결합

                // textViewDateDone에 선택된 날짜들 표시
                fragmentAppointmentDateTimeSelectionBinding.textViewDateDone.text = formattedDates
            }
            .initiallyPickedMultipleDays(listOf())
            .build()

        datePicker.show(requireActivity().supportFragmentManager, "MULTIPLE_DATE_PICKER_TAG")
    }
}
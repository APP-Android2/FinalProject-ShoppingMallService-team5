package kr.co.lion.mungnolza.ui.main.appointment.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kr.co.lion.mungnolza.R
<<<<<<< HEAD
import kr.co.lion.mungnolza.databinding.ActivityAppointmentBinding
import kr.co.lion.mungnolza.databinding.FragmentAppointmentDateTimeSelectionBinding
import kr.co.lion.mungnolza.ui.main.MainActivity
import kr.co.lion.mungnolza.ui.main.MainFragmentName
import kr.co.lion.mungnolza.ui.main.appointment.AppointmentActivity
import kr.co.lion.mungnolza.ui.main.showAlertDialog
=======
import kr.co.lion.mungnolza.databinding.FragmentAppointmentDateTimeSelectionBinding
>>>>>>> origin/7-feature-예약화면-개발


class AppointmentDateTimeSelectionFragment : Fragment() {

    private var _binding: FragmentAppointmentDateTimeSelectionBinding? = null
    private val binding get() = _binding!!

<<<<<<< HEAD
    lateinit var appointmentActivity: AppointmentActivity

=======
>>>>>>> origin/7-feature-예약화면-개발
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentAppointmentDateTimeSelectionBinding.inflate(inflater)
<<<<<<< HEAD
        appointmentActivity = activity as AppointmentActivity


        setupSelectTimeButton()
        settingupButtons()
        settingDateTimeNextButton()
        settingupDayButton()
=======

        setupSelectTimeButton()
        settingupButtons()
>>>>>>> origin/7-feature-예약화면-개발

        return binding.root
    }


    fun setupSelectTimeButton() {
        // View Binding을 사용하여 뷰에 접근
        binding.apply {
            buttonplustime.setOnClickListener {
                // 버튼을 눌렀을 때 TimePicker를 표시하는 메서드 호출
                showMaterialTimePicker()
            }
        }
    }

    fun settingupButtons() {
        val selectedButtonColor = ContextCompat.getColor(requireContext(), R.color.selectedButtonColor) // 선택된 버튼 색상
        val defaultButtonColor = ContextCompat.getColor(requireContext(), R.color.defaultButtonColor) // 기본 버튼 색상

        binding.apply {
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
            binding.editTextVisitTime.setText(selectedTime)
<<<<<<< HEAD

            val checkBoxText = "$selectedTime 로 펫시터님이 협의를 제안합니다"
            binding.checkBoxTime.apply {
                text = checkBoxText
                visibility = View.VISIBLE
            }

            // 여기에 CheckBox 변경하는 코드를 추가
=======
            binding.editTextVisitTime.setText(selectedTime)

            // 여기에 CheckBox의 가시성을 변경하는 코드를 추가
>>>>>>> origin/7-feature-예약화면-개발
            binding.checkBoxTime.visibility = View.VISIBLE
            binding.checkBoxCan.visibility = View.VISIBLE
            binding.checkBoxCant.visibility = View.VISIBLE
        }
    }

<<<<<<< HEAD
    fun settingDateTimeNextButton() {
        binding.apply {
            buttonAppointmentCalendarNext.setOnClickListener {
                // 날짜 선택 확인
                if (textViewDateDone.text.toString().isEmpty() || textViewDateDone.text.toString() == " ") {
                    showAlertDialog("날짜를 선택해 주세요.")
                    return@setOnClickListener
                }

                // 시간 선택 확인
                if (editTextVisitTime.text.toString().isEmpty() || editTextVisitTime.text.toString() == "시간 선택") {
                    showAlertDialog("시간을 선택해 주세요.")
                    return@setOnClickListener
                }

                // 최소 하나의 CheckBox가 선택되었는지 확인
                if (!checkBoxTime.isChecked && !checkBoxCan.isChecked && !checkBoxCant.isChecked) {
                    showAlertDialog("하나의 조건에 체크해 주세요.")
                    return@setOnClickListener
                }

                // 모든 조건을 충족시키면 다음 화면으로 넘어감
                appointmentActivity.replaceFragment(
                    MainFragmentName.APPOINTMENT_REQUEST, true, true, null
                )
            }
        }
    }

    fun settingupDayButton() {
        // View Binding을 사용하여 뷰에 접근, buttonAppointmentOneday 버튼에 클릭 리스너 설정
        binding.buttonappointmentCalendarSelectDate.apply {
            setOnClickListener {
                // 캘린더 설정 메서드 호출
                calendarSettingForMultipleDates()
            }
        }
    }
=======
>>>>>>> origin/7-feature-예약화면-개발

    fun calendarSettingForMultipleDates() {
        val today = CivilCalendar()

        val datePicker = PrimeDatePicker.bottomSheetWith(today)
            .pickMultipleDays { selectedDays ->
<<<<<<< HEAD
                val formattedDates = selectedDays.map { day ->
                    "${day.month + 1}월 ${day.dayOfMonth}일"
                }.joinToString(separator = ", ")
=======
                // 여러 날짜 처리 로직
                val formattedDates = selectedDays.map { day ->
                    // 각 날짜를 원하는 형식으로 포맷
                    "${day.month + 1}월 ${day.dayOfMonth}일"
                }.joinToString(separator = ", ") // 선택된 모든 날짜를 하나의 문자열로 결합
>>>>>>> origin/7-feature-예약화면-개발

                // textViewDateDone에 선택된 날짜들 표시
                binding.textViewDateDone.text = formattedDates
            }
            .initiallyPickedMultipleDays(listOf())
            .build()

        datePicker.show(requireActivity().supportFragmentManager, "MULTIPLE_DATE_PICKER_TAG")
    }

<<<<<<< HEAD

=======
>>>>>>> origin/7-feature-예약화면-개발
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
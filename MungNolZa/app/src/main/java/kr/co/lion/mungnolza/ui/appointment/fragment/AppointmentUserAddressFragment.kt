package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentUserAddressBinding
import kr.co.lion.mungnolza.ext.hideSoftInput
import kr.co.lion.mungnolza.ext.setColorWhite
import kr.co.lion.mungnolza.ext.showSoftInput
import kr.co.lion.mungnolza.ui.PositiveCustomDialog

class AppointmentUserAddressFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentAppointmentUserAddressBinding? = null
    private val binding get() = _binding!!
    var selectedVisitType: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppointmentUserAddressBinding.inflate(inflater)
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(view)
    }

    private fun initToolbar(view: View) {
        binding.toolbar.setNavigationOnClickListener {
            val action = AppointmentUserAddressFragmentDirections.toAppointmentDogTimeSelectionFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun initView() {

        with(binding) {
            btnSaveAddr.setOnClickListener(this@AppointmentUserAddressFragment)
            btnNewAddr.setOnClickListener(this@AppointmentUserAddressFragment)
            btnInputAddrComplete.setOnClickListener(this@AppointmentUserAddressFragment)
            cardviewCommonVisit.setOnClickListener(this@AppointmentUserAddressFragment)
            cardviewRegularVisit.setOnClickListener(this@AppointmentUserAddressFragment)
            btnSelectDate.setOnClickListener(this@AppointmentUserAddressFragment)
            btnNewAddr.setOnClickListener(this@AppointmentUserAddressFragment)
            imgSelectTime.setOnClickListener(this@AppointmentUserAddressFragment)
        }
    }


    private fun showMaterialTimePicker() {
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
            binding.edittextSeletedTime.setText(selectedTime)

            val checkBoxText = "$selectedTime 로 펫시터님이 협의를 제안합니다"
            binding.checkBoxTime.apply {
                text = checkBoxText
                visibility = VISIBLE
            }

            // 여기에 CheckBox 변경하는 코드를 추가
            binding.edittextSeletedTime.setText(selectedTime)

            // 여기에 CheckBox의 가시성을 변경하는 코드를 추가
            binding.checkBoxTime.visibility = View.VISIBLE
            binding.checkBoxCan.visibility = View.VISIBLE
        }
    }

    private fun calendarSettingForMultipleDates() {
        val today = CivilCalendar()

        val datePicker = PrimeDatePicker.bottomSheetWith(today)
            .pickMultipleDays { selectedDays ->

                val formattedDates = selectedDays.map { day ->
                    "${day.month + 1}월 ${day.dayOfMonth}일"
                }.joinToString(separator = ", ")

                // textViewDateDone에 선택된 날짜들 표시
                binding.btnSelectDate.text = formattedDates
            }
            .initiallyPickedMultipleDays(listOf())
            .build()

        datePicker.show(requireActivity().supportFragmentManager, "MULTIPLE_DATE_PICKER_TAG")
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v?.id) {
                R.id.btn_save_addr -> {
                    // 내 주소 가져오기
                }
                R.id.btn_new_addr ->{
                    // 주소 API 호출
                }

                R.id.btn_input_addr_complete ->{
                    if (edittextAddr.text.toString().isEmpty()){
                        edittextAddrLayout.helperText = "주소를 입력해 주세요"
                        requireContext().showSoftInput(edittextAddr)
                    }else{
                        edittextAddrLayout.helperText = ""
                        timeSelectContainer.visibility = VISIBLE
                        requireContext().hideSoftInput(requireActivity())
                    }
                }
                R.id.cardview_common_visit -> {
                    imgWeekContainer.visibility = GONE

                    cardviewCommonVisit.isChecked = true

                    cardviewRegularVisit.isChecked = false
                    cardviewRegularVisit.backgroundTintList = requireContext().setColorWhite()
                    selectedVisitType = visitType.COMMON_VISIT.value
                }
                R.id.cardview_regular_visit -> {
                    imgWeekContainer.visibility = VISIBLE

                    cardviewRegularVisit.isChecked = true

                    cardviewCommonVisit.isChecked = false
                    cardviewCommonVisit.backgroundTintList = requireContext().setColorWhite()
                    selectedVisitType = visitType.REGULAR_VISIT.value
                }
                R.id.img_select_time -> {
                    // 버튼을 눌렀을 때 TimePicker를 표시하는 메서드 호출
                    showMaterialTimePicker()
                }
                R.id.btn_select_date -> {
                    if (selectedVisitType.isNullOrEmpty()){
                        val dialog = PositiveCustomDialog(
                            title = "방문 타입을 선택해 볼까요?",
                            message = "원하시는 방문 타입을 선택해 주세요!",
                            positiveButtonClick = { }
                        )
                        dialog.show(childFragmentManager, "PositiveCustomDialog")
                    }else{
                        calendarSettingForMultipleDates()
                    }

                }
                else -> {}
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    enum class visitType(val value: String) {
        COMMON_VISIT("common visit"),
        REGULAR_VISIT("regular visit"),
    }
}
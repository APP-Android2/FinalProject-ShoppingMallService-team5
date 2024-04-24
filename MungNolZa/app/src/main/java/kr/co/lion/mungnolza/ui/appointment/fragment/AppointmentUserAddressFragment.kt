package kr.co.lion.mungnolza.ui.appointment.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.aminography.primecalendar.PrimeCalendar
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentUserAddressBinding
import kr.co.lion.mungnolza.ext.repeatOnViewStarted
import kr.co.lion.mungnolza.ext.setColorWhite
import kr.co.lion.mungnolza.ext.showDialog
import kr.co.lion.mungnolza.model.SelectScheduleModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModelFactory
import kr.co.lion.mungnolza.ui.intro.activity.AddressActivity
import kr.co.lion.mungnolza.util.Tools.ADDR_RESULT_CODE
import kr.co.lion.mungnolza.util.VisitType
import kr.co.lion.mungnolza.util.Week
import java.util.Calendar

class AppointmentUserAddressFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentAppointmentUserAddressBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AppointmentViewModel by activityViewModels { AppointmentViewModelFactory() }
    private lateinit var launcherForActivity: ActivityResultLauncher<Intent>

    private val args: AppointmentUserAddressFragmentArgs by navArgs()
    private val selectedWeek = mutableListOf<String>()

    private val selectDate = ArrayList<String>()
    private var selectedVisitType: String? = null
    private var selectedVisitTime: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppointmentUserAddressBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val flag = args.flag
        if (flag != null) {
            viewModel.setFlag(flag)
        }

        val contracts = ActivityResultContracts.StartActivityForResult()
        launcherForActivity = registerForActivityResult(contracts) { result ->
            val callback = result.data
            if (callback != null) {
                if (result.resultCode == ADDR_RESULT_CODE) {
                    val data = callback.getStringExtra("data")
                    with(binding) {
                        btnSaveAddr.visibility = VISIBLE
                        editTextDetailAddr.visibility = VISIBLE
                        edittextAddr.setText(data)
                    }
                }
            }
        }

        with(binding) {
            binding.toolbar.setNavigationOnClickListener {
                val action: NavDirections = when (viewModel.fromWhere.value) {
                    AppointmentMainFragment.ServiceType.JOGGING.value -> {
                        AppointmentUserAddressFragmentDirections.toAppointmentDogTimeSelectionFragment()
                    }

                    AppointmentMainFragment.ServiceType.CARE.value -> {
                        AppointmentUserAddressFragmentDirections.toAppointmentDogTimeSelection2Fragment()
                    }

                    else -> {
                        return@setNavigationOnClickListener
                    }
                }
                Navigation.findNavController(view).navigate(action)
            }

            repeatOnViewStarted {
                val careType = viewModel.careType.value.toString()
                if (careType == AppointmentMainFragment.CareType.VISIT.value) {
                    addressContainer.visibility = GONE
                }
            }

            edittextAddr.setText(viewModel.userAddress.value)
        }
        initView()
    }

    private fun initView() {
        with(binding) {
            repeatOnViewStarted {
                val careType = viewModel.careType.value.toString()
                if (careType == AppointmentMainFragment.CareType.VISIT.value) {
                    addressContainer.visibility = GONE
                }
            }

            edittextAddr.setText(viewModel.userAddress.value)

            btnSaveAddr.setOnClickListener(this@AppointmentUserAddressFragment)
            btnNewAddr.setOnClickListener(this@AppointmentUserAddressFragment)
            cardviewCommonVisit.setOnClickListener(this@AppointmentUserAddressFragment)
            cardviewRegularVisit.setOnClickListener(this@AppointmentUserAddressFragment)
            btnSelectDate.setOnClickListener(this@AppointmentUserAddressFragment)
            btnNewAddr.setOnClickListener(this@AppointmentUserAddressFragment)
            btnNext.setOnClickListener(this@AppointmentUserAddressFragment)
            imgSelectTime.setOnClickListener(this@AppointmentUserAddressFragment)

            imgWeekSunday.setOnClickListener(this@AppointmentUserAddressFragment)
            imgWeekSaturday.setOnClickListener(this@AppointmentUserAddressFragment)
            imgWeekMonday.setOnClickListener(this@AppointmentUserAddressFragment)
            imgWeekTuesday.setOnClickListener(this@AppointmentUserAddressFragment)
            imgWeekWednesday.setOnClickListener(this@AppointmentUserAddressFragment)
            imgWeekThursday.setOnClickListener(this@AppointmentUserAddressFragment)
            imgWeekFriday.setOnClickListener(this@AppointmentUserAddressFragment)
            imgWeekSaturday.setOnClickListener(this@AppointmentUserAddressFragment)
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v?.id) {
                R.id.btn_save_addr -> {
                    edittextAddr.setText(viewModel.userAddress.value)
                }

                R.id.btn_new_addr -> {
                    launcherForActivity.launch(
                        Intent(
                            requireContext(),
                            AddressActivity::class.java
                        )
                    )
                }

                R.id.cardview_common_visit -> {
                    imgWeekContainer.visibility = GONE

                    cardviewCommonVisit.isChecked = true

                    cardviewRegularVisit.isChecked = false
                    cardviewRegularVisit.backgroundTintList = requireContext().setColorWhite()
                    selectedVisitType = VisitType.COMMON_VISIT.type
                }

                R.id.cardview_regular_visit -> {
                    imgWeekContainer.visibility = VISIBLE

                    cardviewRegularVisit.isChecked = true
                    cardviewCommonVisit.isChecked = false
                    cardviewCommonVisit.backgroundTintList = requireContext().setColorWhite()
                    selectedVisitType = VisitType.REGULAR_VISIT.type

                }

                R.id.img_select_time -> {
                    // 버튼을 눌렀을 때 TimePicker를 표시하는 메서드 호출
                    showMaterialTimePicker()
                }

                R.id.btn_select_date -> {
                    if (selectedVisitType.isNullOrEmpty()) {
                        childFragmentManager.showDialog("방문 타입을 선택해 볼까요?", "원하시는 방문 타입을 선택해 주세요!")
                    } else if (selectedVisitType == VisitType.COMMON_VISIT.type) {
                        showCommonVisitCalender()
                    } else {
                        if (selectedWeek.isEmpty()) {
                            childFragmentManager.showDialog(
                                "방문 요일을 선택해 볼까요?",
                                "원하시는 방문 요일을 선택해 주세요!"
                            )
                        } else {
                            showRegularVisitCalender()
                        }
                    }

                }

                R.id.btn_next -> {
                    if (edittextAddr.text.isNullOrEmpty() &&
                        viewModel.careType.value.toString() != AppointmentMainFragment.CareType.VISIT.value
                    ) {
                        childFragmentManager.showDialog("주소를 입력해 볼까요?", "방문 주소을 선택해 주세요")
                    } else if (selectedVisitType.isNullOrEmpty()) {
                        childFragmentManager.showDialog("방문 타입을 선택해 볼까요?", "방문 타입을 선택해 주세요")
                    } else if (selectDate.isEmpty()) {
                        childFragmentManager.showDialog("방문 날짜를 선택해 볼까요?", "방문 날짜를 선택해 주세요")
                    } else if (selectedVisitTime.isNullOrEmpty()) {
                        childFragmentManager.showDialog("방문 시간을 선택해 볼까요?", "방문 시간을 선택해 주세요")
                    } else {
                        val addr = "${edittextAddr.text} ${editTextDetailAddr.text}"
                        val request = appointmentRequestTextview.text.toString()

                        viewModel.onCompleteSchedule(
                            SelectScheduleModel(
                                addr,
                                selectedVisitTime.toString(),
                                request,
                                viewModel.serviceType.value.toString(),
                                viewModel.payment.value.time,
                                viewModel.selectedPet.value,
                                selectDate,
                                calcPayment(),
                            )
                        )

                        val action = AppointmentUserAddressFragmentDirections.toMatchingFragment()
                        Navigation.findNavController(v).navigate(action)
                    }
                }

                R.id.img_week_sunday -> {
                    if (!selectedWeek.contains(Week.SUNDAY.day)) {
                        addToSelectList(Week.SUNDAY.day)
                    } else {
                        removeToSelectList(Week.SUNDAY.day)
                    }
                }

                R.id.img_week_monday -> {
                    if (!selectedWeek.contains(Week.MONDAY.day)) {
                        addToSelectList(Week.MONDAY.day)
                    } else {
                        removeToSelectList(Week.MONDAY.day)
                    }
                }

                R.id.img_week_tuesday -> {
                    if (!selectedWeek.contains(Week.TUESDAY.day)) {
                        addToSelectList(Week.TUESDAY.day)
                    } else {
                        removeToSelectList(Week.TUESDAY.day)
                    }
                }

                R.id.img_week_wednesday -> {
                    if (!selectedWeek.contains(Week.WEDNESDAY.day)) {
                        addToSelectList(Week.WEDNESDAY.day)
                    } else {
                        removeToSelectList(Week.WEDNESDAY.day)
                    }
                }

                R.id.img_week_thursday -> {
                    if (!selectedWeek.contains(Week.THURSDAY.day)) {
                        addToSelectList(Week.THURSDAY.day)
                    } else {
                        removeToSelectList(Week.THURSDAY.day)
                    }
                }

                R.id.img_week_friday -> {
                    if (!selectedWeek.contains(Week.FRIDAY.day)) {
                        addToSelectList(Week.FRIDAY.day)
                    } else {
                        removeToSelectList(Week.FRIDAY.day)
                    }
                }

                R.id.img_week_saturday -> {
                    if (!selectedWeek.contains(Week.SATURDAY.day)) {
                        addToSelectList(Week.SATURDAY.day)
                    } else {
                        removeToSelectList(Week.SATURDAY.day)
                    }
                }

                else -> {}
            }
        }
    }


    private fun addToSelectList(day: String) {
        with(binding) {
            when(day) {
                Week.SUNDAY.day -> {
                    selectedWeek.add(Week.SUNDAY.day)
                    imgWeekSunday.setImageResource(R.drawable.img_week_sunday)
                }

                Week.MONDAY.day -> {
                    selectedWeek.add(Week.MONDAY.day)
                    imgWeekMonday.setImageResource(R.drawable.img_week_monday)
                }

                Week.TUESDAY.day -> {
                    selectedWeek.add(Week.TUESDAY.day)
                    imgWeekTuesday.setImageResource(R.drawable.img_week_tuesday)
                }

                Week.WEDNESDAY.day -> {
                    selectedWeek.add(Week.WEDNESDAY.day)
                    imgWeekWednesday.setImageResource(R.drawable.img_week_wednesday)
                }

                Week.THURSDAY.day -> {
                    selectedWeek.add(Week.THURSDAY.day)
                    imgWeekThursday.setImageResource(R.drawable.img_week_thursday)
                }

                Week.FRIDAY.day -> {
                    selectedWeek.add(Week.FRIDAY.day)
                    imgWeekFriday.setImageResource(R.drawable.img_week_friday)
                }

                Week.SATURDAY.day -> {
                    selectedWeek.add(Week.SATURDAY.day)
                    imgWeekSaturday.setImageResource(R.drawable.img_week_saturday)
                }
            }
        }
    }

    private fun removeToSelectList(day: String) {
        with(binding) {
            when (day) {
                Week.SUNDAY.day -> {
                    selectedWeek.remove(Week.SUNDAY.day)
                    imgWeekSunday.setImageResource(R.drawable.img_week_sunday_dog)
                }

                Week.MONDAY.day -> {
                    selectedWeek.remove(Week.MONDAY.day)
                    imgWeekMonday.setImageResource(R.drawable.img_week_monday_dog)
                }

                Week.TUESDAY.day -> {
                    selectedWeek.remove(Week.TUESDAY.day)
                    imgWeekTuesday.setImageResource(R.drawable.img_week_tuesday_dog)
                }

                Week.WEDNESDAY.day -> {
                    selectedWeek.remove(Week.WEDNESDAY.day)
                    imgWeekWednesday.setImageResource(R.drawable.img_week_wednesday_dog)
                }

                Week.THURSDAY.day -> {
                    selectedWeek.remove(Week.THURSDAY.day)
                    imgWeekThursday.setImageResource(R.drawable.img_week_thursday_dog)
                }

                Week.FRIDAY.day -> {
                    selectedWeek.remove(Week.FRIDAY.day)
                    imgWeekFriday.setImageResource(R.drawable.img_week_friday_dog)
                }

                Week.SATURDAY.day -> {
                    selectedWeek.remove(Week.SATURDAY.day)
                    imgWeekSaturday.setImageResource(R.drawable.img_week_saturday_dog)
                }
            }
        }
    }

    private fun showCommonVisitCalender() {
        val today = CivilCalendar()

        val datePicker = PrimeDatePicker.bottomSheetWith(today)
            .pickMultipleDays { selectedDays ->

                val formattedDates = selectedDays.map { day ->
                    "${day.month + 1}월 ${day.dayOfMonth}일"
                }.joinToString(separator = ", ")

                selectDate.clear()
                formattedDates.split(",").map { selectDate.add(it) }

                if (selectDate.size > 1) {
                    binding.btnSelectDate.text = "${selectDate[0]} 외 ${selectDate.size - 1} 일"
                } else {
                    binding.btnSelectDate.text = formattedDates
                }
            }
            .initiallyPickedMultipleDays(listOf())
            .minPossibleDate(today)
            .build()

        datePicker.show(requireActivity().supportFragmentManager, "MULTIPLE_DATE_PICKER_TAG")
    }

    private fun showRegularVisitCalender() {
        val today = CivilCalendar()

        val fiveWeeksLater = today.clone().apply {
            add(Calendar.WEEK_OF_YEAR, 5)
        }
        val selectedDaysOfWeek = selectedWeek.map { getDayOfWeek(it) }

        val datePicker = PrimeDatePicker.bottomSheetWith(today)
            .pickRangeDays { startDate, endDate ->
                val formattedStartDate = "${startDate.month + 1}월 ${startDate.dayOfMonth}일"
                val formattedEndDate = "${endDate.month + 1}월 ${endDate.dayOfMonth}일"
                binding.btnSelectDate.text = "$formattedStartDate ~ $formattedEndDate"

                val allDatesInRange = getAllDatesInRange(startDate, endDate)

                selectDate.clear()
                allDatesInRange.map { date ->
                    if (selectedDaysOfWeek.contains(date[Calendar.DAY_OF_WEEK])) {
                        selectDate.add("${date.month + 1}월 ${date.dayOfMonth}일")
                    }

                }
            }
            .minPossibleDate(today)
            .maxPossibleDate(fiveWeeksLater)
            .disabledDays(getDisabledDays(selectedDaysOfWeek, today, fiveWeeksLater))
            .build()

        datePicker.show(requireActivity().supportFragmentManager, "RANGE_DATE_PICKER_TAG")
    }

    private fun getAllDatesInRange(startDate: PrimeCalendar, endDate: PrimeCalendar): List<PrimeCalendar> {
        val allDatesInRange = mutableListOf<PrimeCalendar>()
        val currentDate = startDate.clone()
        while (currentDate <= endDate) {
            // 해당 날짜가 selectDate에 없는 경우에만 추가
            if (!selectDate.contains("${currentDate.month + 1}월 ${currentDate.dayOfMonth}일")) {
                allDatesInRange.add(currentDate.clone())
            }
            currentDate.add(Calendar.DAY_OF_MONTH, 1)
        }
        return allDatesInRange
    }


    private fun getDisabledDays(
        selectedDaysOfWeek: List<Int>,
        startDate: CivilCalendar,
        endDate: PrimeCalendar
    ): List<PrimeCalendar> {
        val disabledDays = mutableListOf<PrimeCalendar>()

        val daysOfWeekToExclude = selectedDaysOfWeek.toSet()

        val currentDay = startDate.clone()
        val adjustedEndDate = endDate.clone()
        adjustedEndDate.add(Calendar.DAY_OF_MONTH, 1)

        while (currentDay < adjustedEndDate) {
            val dayOfWeek = currentDay[Calendar.DAY_OF_WEEK]
            if (dayOfWeek !in daysOfWeekToExclude) {
                disabledDays.add(currentDay.clone())
            }
            currentDay.add(Calendar.DAY_OF_MONTH, 1)
        }

        return disabledDays
    }


    private fun getDayOfWeek(day: String): Int {
        return when (day) {
            Week.SUNDAY.day -> Calendar.SUNDAY
            Week.MONDAY.day -> Calendar.MONDAY
            Week.TUESDAY.day -> Calendar.TUESDAY
            Week.WEDNESDAY.day -> Calendar.WEDNESDAY
            Week.THURSDAY.day -> Calendar.THURSDAY
            Week.FRIDAY.day -> Calendar.FRIDAY
            Week.SATURDAY.day -> Calendar.SATURDAY
            else -> 0
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
            selectedVisitTime = selectedTime
            binding.edittextSeletedTime.setText(selectedTime)

        }
    }

    private fun calcPayment(): Int {
        val pets = viewModel.selectedPet.value.size
        val pay = viewModel.payment.value.payment
        val day = selectDate.size
        return (pets * pay) * day
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentDogTimeSelectionBinding
import kr.co.lion.mungnolza.ext.setColorGreenBlue
import kr.co.lion.mungnolza.ext.setSelectTimeButtonColor
import kr.co.lion.mungnolza.ui.PositiveCustomDialog


class AppointmentDogTimeSelectionFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentAppointmentDogTimeSelectionBinding? = null
    private val binding get() = _binding!!
    private var selectedTime: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppointmentDogTimeSelectionBinding.inflate(inflater)
        initView()
        return binding.root
    }

    private fun initView() {
        with(binding) {
            btnBack.setOnClickListener(this@AppointmentDogTimeSelectionFragment)
            btnAllDay.setOnClickListener(this@AppointmentDogTimeSelectionFragment)
            btn30Minutes.setOnClickListener(this@AppointmentDogTimeSelectionFragment)
            btn1Hour.setOnClickListener(this@AppointmentDogTimeSelectionFragment)
            btn2Hour.setOnClickListener(this@AppointmentDogTimeSelectionFragment)
            btnNext.setOnClickListener(this@AppointmentDogTimeSelectionFragment)
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v?.id) {
                R.id.btn_back -> {
                    val action = AppointmentDogTimeSelectionFragmentDirections.toAppointmentMainFragment()
                    Navigation.findNavController(v).navigate(action)
                }

                R.id.btn_all_day -> {
                    btnAllDay.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btn30Minutes.backgroundTintList = requireContext().setColorGreenBlue()
                    btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    selectedTime = ServiceTime.ALL_DAY.value
                }

                R.id.btn_30_minutes -> {
                    btn30Minutes.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btnAllDay.backgroundTintList = requireContext().setColorGreenBlue()
                    btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    selectedTime = ServiceTime.HALF_AN_HOUR.value
                }

                R.id.btn_1_hour -> {
                    btn1Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btnAllDay.backgroundTintList = requireContext().setColorGreenBlue()
                    btn30Minutes.backgroundTintList = requireContext().setColorGreenBlue()
                    btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    selectedTime = ServiceTime.ONE_HOUR.value
                }

                R.id.btn_2_hour -> {
                    btn2Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btnAllDay.backgroundTintList = requireContext().setColorGreenBlue()
                    btn30Minutes.backgroundTintList = requireContext().setColorGreenBlue()
                    btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    selectedTime = ServiceTime.TWO_HOUR.value
                }

                R.id.btn_next -> {
                    if (selectedTime.isNullOrEmpty()) {
                        val dialog = PositiveCustomDialog(
                            title = "서비스 시간을 선택해 볼까요 ?",
                            message = "원하시는 서비스 시간을 선택해 주세요!",
                            positiveButtonClick = { }
                        )
                        dialog.show(childFragmentManager, "PositiveCustomDialog")
                    } else {
                        val action = AppointmentDogTimeSelectionFragmentDirections.toAppointmentUserAddressFragment()
                        Navigation.findNavController(v).navigate(action)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        selectedTime = null
        _binding = null
    }

    enum class ServiceTime(val value: String) {
        ALL_DAY("all day"),
        HALF_AN_HOUR("30 minutes"),
        ONE_HOUR("1 HOUR"),
        TWO_HOUR("2 HOUR")
    }
}




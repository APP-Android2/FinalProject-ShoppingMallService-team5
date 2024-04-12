package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentDogTimeSelection2Binding
import kr.co.lion.mungnolza.ext.setColorGreenBlue
import kr.co.lion.mungnolza.ext.setSelectTimeButtonColor
import kr.co.lion.mungnolza.ui.dialog.PositiveCustomDialog


class AppointmentDogTimeSelection2Fragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentAppointmentDogTimeSelection2Binding? = null
    private val binding get() = _binding!!
    private var selectedTime: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppointmentDogTimeSelection2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding) {
            btnBack.setOnClickListener(this@AppointmentDogTimeSelection2Fragment)
            btn1Hour.setOnClickListener(this@AppointmentDogTimeSelection2Fragment)
            btn2Hour.setOnClickListener(this@AppointmentDogTimeSelection2Fragment)
            btn3Hour.setOnClickListener(this@AppointmentDogTimeSelection2Fragment)
            btnNext.setOnClickListener(this@AppointmentDogTimeSelection2Fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v?.id) {
                R.id.btn_back -> {
                    val action = AppointmentDogTimeSelection2FragmentDirections.toAppointmentMainFragment()
                    Navigation.findNavController(v).navigate(action)
                }

                R.id.btn_1_hour -> {
                    btn1Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    btn3Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    selectedTime = ServiceTime.ONE_HOUR.value
                }

                R.id.btn_2_hour -> {
                    btn2Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btn3Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    selectedTime = ServiceTime.TWO_HOUR.value
                }
                R.id.btn_3_hour -> {
                    btn3Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    selectedTime = ServiceTime.THREE_HOUR.value
                }
                R.id.btn_next -> {
                    if (selectedTime.isNullOrEmpty()) {
                        val dialog = PositiveCustomDialog(
                            title = "서비스 시간을 선택해 볼까요?",
                            message = "원하시는 서비스 시간을 선택해 주세요!",
                            positiveButtonClick = { }
                        )
                        dialog.show(childFragmentManager, "PositiveCustomDialog")
                    } else {
                        val action = AppointmentDogTimeSelection2FragmentDirections.toAppointmentUserAddressFragment()
                        Navigation.findNavController(v).navigate(action)
                    }
                }
            }
        }
    }
    enum class ServiceTime(val value: String) {
        ONE_HOUR("1 HOUR"),
        TWO_HOUR("2 HOUR"),
        THREE_HOUR("3 HOUR")
    }
}
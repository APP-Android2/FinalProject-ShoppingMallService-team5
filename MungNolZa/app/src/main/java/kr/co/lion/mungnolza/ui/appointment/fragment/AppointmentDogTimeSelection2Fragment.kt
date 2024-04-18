package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentDogTimeSelection2Binding
import kr.co.lion.mungnolza.ext.setColorGreenBlue
import kr.co.lion.mungnolza.ext.setSelectTimeButtonColor
import kr.co.lion.mungnolza.model.PaymentTimeModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModelFactory
import kr.co.lion.mungnolza.ui.dialog.PositiveCustomDialog


class AppointmentDogTimeSelection2Fragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentAppointmentDogTimeSelection2Binding? = null
    private val binding get() = _binding!!
    private val viewModel: AppointmentViewModel by activityViewModels { AppointmentViewModelFactory() }
    private var selectedTime: String = ""
    private var payment: Int = 0

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
                    payment = ServiceTime.ONE_HOUR.pay
                }

                R.id.btn_2_hour -> {
                    btn2Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btn3Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    selectedTime = ServiceTime.TWO_HOUR.value
                    payment = ServiceTime.TWO_HOUR.pay
                }
                R.id.btn_3_hour -> {
                    btn3Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    selectedTime = ServiceTime.THREE_HOUR.value
                    payment = ServiceTime.THREE_HOUR.pay
                }
                R.id.btn_next -> {
                    if (selectedTime.isEmpty()) {
                        val dialog = PositiveCustomDialog(
                            title = "서비스 시간을 선택해 볼까요?",
                            message = "원하시는 서비스 시간을 선택해 주세요!",
                            positiveButtonClick = { }
                        )
                        dialog.show(childFragmentManager, "PositiveCustomDialog")
                    } else {
                        val flag = AppointmentMainFragment.ServiceType.CARE.value
                        viewModel.setPayment(PaymentTimeModel(payment, selectedTime))
                        val action = AppointmentDogTimeSelection2FragmentDirections.toAppointmentUserAddressFragment(flag)
                        Navigation.findNavController(v).navigate(action)
                    }
                }
            }
        }
    }
    enum class ServiceTime(val value: String, val pay: Int) {
        ONE_HOUR("1시간", 28000),
        TWO_HOUR("2시간", 37000),
        THREE_HOUR("3시간", 46000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentDogTimeSelectionBinding
import kr.co.lion.mungnolza.ext.setColorGreenBlue
import kr.co.lion.mungnolza.ext.setSelectTimeButtonColor
import kr.co.lion.mungnolza.ext.showDialog
import kr.co.lion.mungnolza.model.PaymentTimeModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModelFactory

class AppointmentDogTimeSelectionFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentAppointmentDogTimeSelectionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AppointmentViewModel by activityViewModels { AppointmentViewModelFactory() }
    private var selectedTime: String = ""
    private var payment: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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
                    val action =
                        AppointmentDogTimeSelectionFragmentDirections.toAppointmentMainFragment()
                    Navigation.findNavController(v).navigate(action)
                }

                R.id.btn_all_day -> {
                    btnAllDay.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btn30Minutes.backgroundTintList = requireContext().setColorGreenBlue()
                    btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    selectedTime = ServiceTime.ALL_DAY.value
                    payment = ServiceTime.ALL_DAY.pay
                }

                R.id.btn_30_minutes -> {
                    btn30Minutes.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btnAllDay.backgroundTintList = requireContext().setColorGreenBlue()
                    btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    selectedTime = ServiceTime.HALF_AN_HOUR.value
                    payment = ServiceTime.HALF_AN_HOUR.pay

                }

                R.id.btn_1_hour -> {
                    btn1Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btnAllDay.backgroundTintList = requireContext().setColorGreenBlue()
                    btn30Minutes.backgroundTintList = requireContext().setColorGreenBlue()
                    btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    selectedTime = ServiceTime.ONE_HOUR.value
                    payment = ServiceTime.ONE_HOUR.pay
                }

                R.id.btn_2_hour -> {
                    btn2Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btnAllDay.backgroundTintList = requireContext().setColorGreenBlue()
                    btn30Minutes.backgroundTintList = requireContext().setColorGreenBlue()
                    btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    selectedTime = ServiceTime.TWO_HOUR.value
                    payment = ServiceTime.TWO_HOUR.ordinal
                }

                R.id.btn_next -> {
                    if (selectedTime.isEmpty()) {
                        childFragmentManager.showDialog("서비스 시간을 선택해 볼까요 ?", "원하시는 서비스 시간을 선택해 주세요!")
                    } else {
                        val flag = AppointmentMainFragment.ServiceType.JOGGING.value
                        viewModel.setPayment(PaymentTimeModel(payment, selectedTime))
                        val action = AppointmentDogTimeSelectionFragmentDirections.toAppointmentUserAddressFragment(flag)
                        Navigation.findNavController(v).navigate(action)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    enum class ServiceTime(val value: String, val pay: Int) {
        ALL_DAY("종일권", 80000),
        HALF_AN_HOUR("30분", 18000),
        ONE_HOUR("1시간", 28000),
        TWO_HOUR("2시간", 38000)
    }
}
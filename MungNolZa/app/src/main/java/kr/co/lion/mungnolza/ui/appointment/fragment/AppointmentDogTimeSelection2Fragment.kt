package kr.co.lion.mungnolza.ui.appointment.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentDogTimeSelection2Binding
import kr.co.lion.mungnolza.ext.setColorGreenBlue
import kr.co.lion.mungnolza.ext.setSelectTimeButtonColor
import kr.co.lion.mungnolza.ext.showDialog
import kr.co.lion.mungnolza.model.PaymentTimeModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModelFactory


class AppointmentDogTimeSelection2Fragment : Fragment(R.layout.fragment_appointment_dog_time_selection2){
    private val viewModel: AppointmentViewModel by activityViewModels { AppointmentViewModelFactory() }
    private var selectedTime: String = ""
    private var payment: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAppointmentDogTimeSelection2Binding.bind(view)

        with(binding) {

            val imgUrl = viewModel.selectedPet.value[0].imgUrl

            Glide.with(this@AppointmentDogTimeSelection2Fragment)
                .load(imgUrl.toString())
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        imgDog.background = resource
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })

            btnBack.setOnClickListener{
                val action = AppointmentDogTimeSelection2FragmentDirections.toAppointmentMainFragment()
                Navigation.findNavController(view).navigate(action)
            }

            btn1Hour.setOnClickListener{
                btn1Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                btn3Hour.backgroundTintList = requireContext().setColorGreenBlue()
                selectedTime = ServiceTime.ONE_HOUR.value
                payment = ServiceTime.ONE_HOUR.pay
            }

            btn2Hour.setOnClickListener{
                btn2Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                btn3Hour.backgroundTintList = requireContext().setColorGreenBlue()
                btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                selectedTime = ServiceTime.TWO_HOUR.value
                payment = ServiceTime.TWO_HOUR.pay
            }
            btn3Hour.setOnClickListener{
                btn3Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                selectedTime = ServiceTime.THREE_HOUR.value
                payment = ServiceTime.THREE_HOUR.pay
            }
            btnNext.setOnClickListener{
                if (selectedTime.isEmpty()) {
                    childFragmentManager.showDialog("서비스 시간을 선택해 볼까요?", "원하시는 서비스 시간을 선택해 주세요")
                } else {
                    val flag = AppointmentMainFragment.ServiceType.CARE.value
                    viewModel.setPayment(PaymentTimeModel(payment, selectedTime))
                    val action = AppointmentDogTimeSelection2FragmentDirections.toAppointmentUserAddressFragment(flag)
                    Navigation.findNavController(view).navigate(action)
                }
            }
        }
    }

    enum class ServiceTime(val value: String, val pay: Int) {
        ONE_HOUR("1시간", 28000),
        TWO_HOUR("2시간", 37000),
        THREE_HOUR("3시간", 46000)
    }
}
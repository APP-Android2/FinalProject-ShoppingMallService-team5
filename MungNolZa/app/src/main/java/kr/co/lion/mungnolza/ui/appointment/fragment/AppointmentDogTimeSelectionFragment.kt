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
import kr.co.lion.mungnolza.databinding.FragmentAppointmentDogTimeSelectionBinding
import kr.co.lion.mungnolza.ext.setColorGreenBlue
import kr.co.lion.mungnolza.ext.setSelectTimeButtonColor
import kr.co.lion.mungnolza.ext.showDialog
import kr.co.lion.mungnolza.model.PaymentTimeModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModelFactory

class AppointmentDogTimeSelectionFragment : Fragment(R.layout.fragment_appointment_dog_time_selection){

    private val viewModel: AppointmentViewModel by activityViewModels { AppointmentViewModelFactory() }
    private var selectedTime: String = ""
    private var payment: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAppointmentDogTimeSelectionBinding.bind(view)

        with(binding) {

            val imgUrl = viewModel.selectedPet.value[0].imgUrl

            Glide.with(this@AppointmentDogTimeSelectionFragment)
                .load(imgUrl.toString())
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        imgDog.background = resource
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })

            btnBack.setOnClickListener{
                val action = AppointmentDogTimeSelectionFragmentDirections.toAppointmentMainFragment()
                Navigation.findNavController(view).navigate(action)
            }

            btnAllDay.setOnClickListener{
                btnAllDay.backgroundTintList = requireContext().setSelectTimeButtonColor()

                btn30Minutes.backgroundTintList = requireContext().setColorGreenBlue()
                btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                selectedTime = ServiceTime.ALL_DAY.value
                payment = ServiceTime.ALL_DAY.pay
            }

            btn30Minutes.setOnClickListener{
                btn30Minutes.backgroundTintList = requireContext().setSelectTimeButtonColor()

                btnAllDay.backgroundTintList = requireContext().setColorGreenBlue()
                btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                selectedTime = ServiceTime.HALF_AN_HOUR.value
                payment = ServiceTime.HALF_AN_HOUR.pay
            }

            btn1Hour.setOnClickListener{
                btn1Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                btnAllDay.backgroundTintList = requireContext().setColorGreenBlue()
                btn30Minutes.backgroundTintList = requireContext().setColorGreenBlue()
                btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                selectedTime = ServiceTime.ONE_HOUR.value
                payment = ServiceTime.ONE_HOUR.pay
            }

            btn2Hour.setOnClickListener{
                btn2Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                btnAllDay.backgroundTintList = requireContext().setColorGreenBlue()
                btn30Minutes.backgroundTintList = requireContext().setColorGreenBlue()
                btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                selectedTime = ServiceTime.TWO_HOUR.value
                payment = ServiceTime.TWO_HOUR.ordinal
            }

            btnNext.setOnClickListener{
                if (selectedTime.isEmpty()) {
                    childFragmentManager.showDialog("서비스 시간을 선택해 볼까요 ?", "원하시는 서비스 시간을 선택해 주세요!")
                } else {
                    val flag = AppointmentMainFragment.ServiceType.JOGGING.value
                    viewModel.setPayment(PaymentTimeModel(payment, selectedTime))
                    val action = AppointmentDogTimeSelectionFragmentDirections.toAppointmentUserAddressFragment(flag)
                    Navigation.findNavController(view).navigate(action)
                }
            }
        }
    }

    enum class ServiceTime(val value: String, val pay: Int) {
        ALL_DAY("종일권", 80000),
        HALF_AN_HOUR("30분", 18000),
        ONE_HOUR("1시간", 28000),
        TWO_HOUR("2시간", 38000)
    }
}
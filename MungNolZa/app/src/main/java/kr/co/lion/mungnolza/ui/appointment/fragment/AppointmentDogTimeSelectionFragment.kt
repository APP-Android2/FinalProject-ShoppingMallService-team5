package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentDogTimeSelectionBinding
import kr.co.lion.mungnolza.extensions.setColorGreenBlue
import kr.co.lion.mungnolza.extensions.setSelectTimeButtonColor


class AppointmentDogTimeSelectionFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentAppointmentDogTimeSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        _binding = FragmentAppointmentDogTimeSelectionBinding.inflate(inflater)
        initView()
        return binding.root
    }

    private fun initView(){
        with(binding){
            btnAllDay.setOnClickListener(this@AppointmentDogTimeSelectionFragment)
            btn30Minutes.setOnClickListener(this@AppointmentDogTimeSelectionFragment)
            btn1Hour.setOnClickListener(this@AppointmentDogTimeSelectionFragment)
            btn2Hour.setOnClickListener(this@AppointmentDogTimeSelectionFragment)
            btnNext.setOnClickListener(this@AppointmentDogTimeSelectionFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        with(binding){
            when(v?.id){
                R.id.btn_all_day -> {
                    btnAllDay.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btn30Minutes.backgroundTintList = requireContext().setColorGreenBlue()
                    btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                }
                R.id.btn_30_minutes -> {
                    btn30Minutes.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btnAllDay.backgroundTintList = requireContext().setColorGreenBlue()
                    btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                    btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                }
                R.id.btn_1_hour -> {
                    btn1Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btnAllDay.backgroundTintList = requireContext().setColorGreenBlue()
                    btn30Minutes.backgroundTintList = requireContext().setColorGreenBlue()
                    btn2Hour.backgroundTintList = requireContext().setColorGreenBlue()
                }
                R.id.btn_2_hour -> {
                    btn2Hour.backgroundTintList = requireContext().setSelectTimeButtonColor()

                    btnAllDay.backgroundTintList = requireContext().setColorGreenBlue()
                    btn30Minutes.backgroundTintList = requireContext().setColorGreenBlue()
                    btn1Hour.backgroundTintList = requireContext().setColorGreenBlue()
                }
                R.id.btn_next -> {
                    val action = AppointmentDogTimeSelectionFragmentDirections.toAppointmentUserAddressFragment()
                    Navigation.findNavController(v).navigate(action)
                }
            }
        }
    }
}



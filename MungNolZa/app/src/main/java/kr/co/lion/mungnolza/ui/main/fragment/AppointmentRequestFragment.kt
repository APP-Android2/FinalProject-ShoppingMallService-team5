package kr.co.lion.mungnolza.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentMainBinding
import kr.co.lion.mungnolza.databinding.FragmentAppointmentRequestBinding
import kr.co.lion.mungnolza.ui.main.MainActivity
import kr.co.lion.mungnolza.ui.main.MainFragmentName

class AppointmentRequestFragment : Fragment() {

    lateinit var fragmentAppointmentRequestBinding: FragmentAppointmentRequestBinding
    lateinit var mainActivity: MainActivity


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentAppointmentRequestBinding = FragmentAppointmentRequestBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        return fragmentAppointmentRequestBinding.root
    }

    // 툴바 설정
    fun settingRequestToolbar(){
        fragmentAppointmentRequestBinding.apply {
            materialToolbar2.apply {
                // 타이틀
//                title = "예약"
                // Back
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainFragmentName.APPOINTMENT_REQUEST)
                }
            }
        }
    }

}
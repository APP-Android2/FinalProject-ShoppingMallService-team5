package kr.co.lion.mungnolza.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject4boardapp.MainActivity
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentMainBinding
import kr.co.lion.mungnolza.ui.main.MainFragmentName


class AppointmentMainFragment : Fragment() {

    lateinit var fragmentAppointmentMainBinding: FragmentAppointmentMainBinding
    lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appointment_main, container, false)
    }




    //다음화면으로
    fun settingMainNextButton(){
        fragmentAppointmentMainBinding.apply {
            buttonAppointmentMainNext.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    // JoinFragment가 보여지게 한다.
                    mainActivity.replaceFragment(MainFragmentName.APPOINTMENT_USER_ADDRESS, true, true, null)
                }
            }
        }
    }
}
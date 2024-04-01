package kr.co.lion.mungnolza.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentUserAddressBinding
import kr.co.lion.mungnolza.ui.main.MainActivity
import kr.co.lion.mungnolza.ui.main.MainFragmentName


class AppointmentUserAddressFragment : Fragment() {

    lateinit var fragmentAppointmentUserAddressBinding: FragmentAppointmentUserAddressBinding
    lateinit var mainActivity: MainActivity


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentAppointmentUserAddressBinding = FragmentAppointmentUserAddressBinding.inflate(inflater)
        mainActivity = activity as MainActivity


        settingUserAdToolbar()
        setAddressInfo()
        settingUserNextButton()

        return fragmentAppointmentUserAddressBinding.root
    }

    // 툴바 설정
    fun settingUserAdToolbar(){
        fragmentAppointmentUserAddressBinding.apply {
            toolbarUserAddress.apply {
                // 타이틀
//                title = "예약"
                // Back
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainFragmentName.APPOINTMENT_USER_ADDRESS)
                }
            }
        }
    }


    fun setAddressInfo(){
        fragmentAppointmentUserAddressBinding.textviewaddress.setText("서울시 00구 00동")

    }

    //다음화면으로
    fun settingUserNextButton(){
        fragmentAppointmentUserAddressBinding.apply {
            buttonAddressNext.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    mainActivity.replaceFragment(MainFragmentName.APPOINTMENT_DATE_TIME_SELECTION, true, true, null)
                }
            }
        }
    }
}


package kr.co.lion.mungnolza.ui.main.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentMainBinding
import kr.co.lion.mungnolza.ui.main.MainFragmentName
import androidx.databinding.DataBindingUtil
import kr.co.lion.mungnolza.ui.main.MainActivity


class AppointmentMainFragment : Fragment() {

    lateinit var fragmentAppointmentMainBinding: FragmentAppointmentMainBinding
    lateinit var mainActivity: MainActivity


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentAppointmentMainBinding = FragmentAppointmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingMainToolbar()
        settingMainNextButton()
        settingupMainAppointmentButtons()
        settingupMainTakeinorOutButtons()

        return fragmentAppointmentMainBinding.root
    }

    fun settingMainToolbar() {
        fragmentAppointmentMainBinding.apply {
            materialToolbar3.apply {
                // 타이틀
//                title = "예약"
                // Back 합칠때 구현
//                setNavigationIcon(R.drawable.arrow_back_24px)
//                setNavigationOnClickListener {

            }
        }
    }


//    fun setupMainButtons() {
//
//        fragmentAppointmentMainBinding.buttonmainwalkappointment.setOnClickListener {
//
//        }
//
//        fragmentAppointmentMainBinding.buttonmaintakecareappointment.setOnClickListener {
//
//        }
//
//        fragmentAppointmentMainBinding.buttonMainTakeDog.setOnClickListener {
//
//        }
//
//        fragmentAppointmentMainBinding.buttonmainvisitdog.setOnClickListener {
//
//        }
//    }

    //다음화면으로
    fun settingMainNextButton() {
        fragmentAppointmentMainBinding.apply {
            buttonAppointmentMainNext.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    mainActivity.replaceFragment(
                        MainFragmentName.APPOINTMENT_DOG_TIME_SELECTION,
                        true,
                        true,
                        null
                    )
                }
            }
        }
    }

    fun settingupMainAppointmentButtons() {
        val selectedButtonColor = ContextCompat.getColor(requireContext(), R.color.selectedButtonColor) // 선택된 버튼 색상
        val defaultButtonColor = ContextCompat.getColor(requireContext(), R.color.defaultButtonColor) // 기본 버튼 색상

        fragmentAppointmentMainBinding.apply {
            buttonmainwalkappointment.setOnClickListener {
                //버튼이 선택되었을 때
                it.backgroundTintList = ColorStateList.valueOf(selectedButtonColor)
                buttonmaintakecareappointment.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
                // 선택된 버튼에 따른 추가 액션 처리
            }

            buttonmaintakecareappointment.setOnClickListener {
                // 정기 방문 버튼이 선택되었을 때
                it.backgroundTintList = ColorStateList.valueOf(selectedButtonColor)
                buttonmainwalkappointment.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)

            }
        }
    }

    fun settingupMainTakeinorOutButtons() {
        val selectedButtonColor = ContextCompat.getColor(requireContext(), R.color.selectedButtonColor) // 선택된 버튼 색상
        val defaultButtonColor = ContextCompat.getColor(requireContext(), R.color.defaultButtonColor) // 기본 버튼 색상

        fragmentAppointmentMainBinding.apply {
            selecttimefordoguntil.setOnClickListener {
                //버튼이 선택되었을 때
                it.backgroundTintList = ColorStateList.valueOf(selectedButtonColor)
                buttonmainvisitdog.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)
                // 선택된 버튼에 따른 추가 액션 처리
            }

            buttonmainvisitdog.setOnClickListener {
                // 정기 방문 버튼이 선택되었을 때
                it.backgroundTintList = ColorStateList.valueOf(selectedButtonColor)
                selecttimefordoguntil.backgroundTintList = ColorStateList.valueOf(defaultButtonColor)

            }
        }
    }
}

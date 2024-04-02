package kr.co.lion.mungnolza.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityMainBinding
import kr.co.lion.mungnolza.ui.main.appointment.AppointmentActivity
import kr.co.lion.mungnolza.ui.main.appointment.fragment.AppointmentDateTimeSelectionFragment
import kr.co.lion.mungnolza.ui.main.appointment.fragment.AppointmentDogTimeSelectionFragment
import kr.co.lion.mungnolza.ui.main.appointment.fragment.AppointmentMainFragment
import kr.co.lion.mungnolza.ui.main.appointment.fragment.AppointmentRequestFragment
import kr.co.lion.mungnolza.ui.main.appointment.fragment.AppointmentUserAddressFragment


class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // AppointmentActivity를 시작하는 Intent 생성
        val intent = Intent(this, AppointmentActivity::class.java)
        startActivity(intent)

    }
}




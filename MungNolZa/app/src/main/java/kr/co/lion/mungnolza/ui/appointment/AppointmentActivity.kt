package kr.co.lion.mungnolza.ui.appointment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityAppointmentBinding
import kr.co.lion.mungnolza.ui.main.MainFragmentName
import kr.co.lion.mungnolza.ui.appointment.fragment.AppointmentDateTimeSelectionFragment
import kr.co.lion.mungnolza.ui.appointment.fragment.AppointmentDogTimeSelectionFragment
import kr.co.lion.mungnolza.ui.appointment.fragment.AppointmentMainFragment
import kr.co.lion.mungnolza.ui.appointment.fragment.AppointmentRequestFragment
import kr.co.lion.mungnolza.ui.appointment.fragment.AppointmentUserAddressFragment

class AppointmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}


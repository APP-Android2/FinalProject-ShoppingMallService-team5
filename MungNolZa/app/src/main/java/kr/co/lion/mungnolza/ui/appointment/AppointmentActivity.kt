package kr.co.lion.mungnolza.ui.appointment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Build
import kr.co.lion.mungnolza.databinding.ActivityAppointmentBinding
import androidx.activity.viewModels
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModelFactory

class AppointmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppointmentBinding
    private val viewModel: AppointmentViewModel by viewModels { AppointmentViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myPet: List<PetImgModel>? = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableArrayExtra("myPet", PetImgModel::class.java)?.mapNotNull { it }?.toTypedArray()?.toList()
        } else {
            intent.getParcelableArrayExtra("myPet")?.mapNotNull { it as? PetImgModel }?.toTypedArray()?.toList()
        }

        if (myPet != null) {
            viewModel.setMyPetData(myPet)
        }
    }

}


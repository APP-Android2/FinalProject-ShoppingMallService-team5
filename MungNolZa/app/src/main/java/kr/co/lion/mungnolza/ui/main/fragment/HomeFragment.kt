package kr.co.lion.mungnolza.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentHomeBinding
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.ui.appointment.AppointmentActivity
import kr.co.lion.mungnolza.ui.appointment.NoPetActivity
import kr.co.lion.mungnolza.ui.main.vm.MainViewModel
import kr.co.lion.mungnolza.ui.main.vm.MainViewModelFactory
import kr.co.lion.mungnolza.ui.realtime_location.RealtimeLocationActivity

class HomeFragment : Fragment(R.layout.fragment_home){
    private val viewModel: MainViewModel by activityViewModels { MainViewModelFactory() }
    private var myPet: List<PetImgModel>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.myPetData.collect {
                    myPet = it
                }
            }
        }

        with(binding){
            btnReserve.setOnClickListener{
                if (myPet?.isEmpty() == true) {
                    startActivity(Intent(requireActivity(), NoPetActivity::class.java))
                } else {
                    val intent = Intent(requireActivity(), AppointmentActivity::class.java)
                    intent.putExtra("myPet", myPet?.toTypedArray())
                    startActivity(intent)
                }
            }
            btnReserveList.setOnClickListener{
                startActivity(Intent(requireActivity(), RealtimeLocationActivity::class.java))
            }
            btnReviewList.setOnClickListener { }
        }
    }
}

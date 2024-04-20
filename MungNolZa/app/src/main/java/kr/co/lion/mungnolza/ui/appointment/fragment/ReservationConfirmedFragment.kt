package kr.co.lion.mungnolza.ui.appointment.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentReservationConfirmedBinding
import kr.co.lion.mungnolza.ui.main.MainActivity

class ReservationConfirmedFragment : Fragment(R.layout.fragment_reservation_confirmed) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentReservationConfirmedBinding.bind(view)

        with(binding){
            btnNext.setOnClickListener {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                activity?.finish()
            }
        }
    }
}
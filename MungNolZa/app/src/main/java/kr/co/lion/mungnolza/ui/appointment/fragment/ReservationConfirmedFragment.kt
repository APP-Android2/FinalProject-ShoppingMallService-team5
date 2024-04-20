package kr.co.lion.mungnolza.ui.appointment.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentReservationConfirmedBinding
import kr.co.lion.mungnolza.ext.moneyFormat
import kr.co.lion.mungnolza.ui.appointment.adapter.PetImgAdapter
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModelFactory
import kr.co.lion.mungnolza.ui.main.MainActivity

class ReservationConfirmedFragment : Fragment(R.layout.fragment_reservation_confirmed) {
    private val viewModel: AppointmentViewModel by activityViewModels { AppointmentViewModelFactory() }
    private val args: ReservationConfirmedFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentReservationConfirmedBinding.bind(view)

        val myPetImg = viewModel.myPetData.value.map { it.imgUrl }
        val imgAdapter = PetImgAdapter(myPetImg)

        with(binding){
            rvPetImg.adapter = imgAdapter
            rvPetImg.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


            val schedule = viewModel.reserveSchedule.value
            val date = StringBuilder()

            schedule.reserveDate.map {
                date.append(it).append("\n".trim())
            }

            reserveDate.text = date.trim()
            reserveServiceType.text =
                if (schedule.serviceType == AppointmentMainFragment.ServiceType.JOGGING.value) {
                    "산책 서비스 예약 확인"
                } else {
                    "돌봄 서비스 예약 확인"
                }
            serviceTime.text = schedule.serviceTime
            reserveDate.text = date.trim()
            reserveTime.text = schedule.reserveTime
            reserveAddr.text = schedule.address
            reservePrice.text = reservePrice.moneyFormat(schedule.totalPrice)

            val selectedPetSitter = viewModel.petSitterData.value?.find { it.petSitter.petSitterIdx == args.idx }
            if (selectedPetSitter != null){
                Glide.with(this@ReservationConfirmedFragment)
                    .load(selectedPetSitter.profileImg.toString())
                    .into(imgPetSitter)

                reviewScore.text = "5.0"
                reviewCnt.text = "20개의 리뷰"

                val career = StringBuilder()
                selectedPetSitter.petSitter.petSitterCareer.map {
                    career.append(it).append("\n")
                }

                petSitterCareer.text = career.trim()

            }

            btnNext.setOnClickListener {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                activity?.finish()
            }
        }
    }
}
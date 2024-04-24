package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentMatchingBinding
import kr.co.lion.mungnolza.ext.moneyFormat
import kr.co.lion.mungnolza.ext.repeatOnViewStarted
import kr.co.lion.mungnolza.ext.showDialog
import kr.co.lion.mungnolza.ui.appointment.adapter.DateAdapter
import kr.co.lion.mungnolza.ui.appointment.adapter.PetImgAdapter
import kr.co.lion.mungnolza.ui.appointment.adapter.PetSitterAdapter
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModelFactory

class MatchingFragment : Fragment(R.layout.fragment_matching) {

    private val viewModel: AppointmentViewModel by activityViewModels { AppointmentViewModelFactory() }
    private var selectedPetSitter: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMatchingBinding.bind(view)

        viewModel.onStartMatching()
        val myPetImg = viewModel.myPetData.value.map { it.imgUrl }
        val imgAdapter = PetImgAdapter(myPetImg)

        with(binding) {
            rvPetImg.adapter = imgAdapter
            rvPetImg.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            toolbar.setNavigationOnClickListener {
                val action = MatchingFragmentDirections.toAppointmentUserAddressFragment(null)
                Navigation.findNavController(view).navigate(action)
            }

            repeatOnViewStarted {
                viewModel.petSitterData.collect {
                    if (it != null) {
                        val petSitterAdapter = PetSitterAdapter(it, { idx ->
                            val info = it[idx].petSitter
                            val action = MatchingFragmentDirections.toPetSitterInfoFragment(info)
                            Navigation.findNavController(view).navigate(action)
                        }, { idx ->
                            selectedPetSitter = it[idx].petSitter.petSitterIdx
                        })

                        rvPetsitter.adapter = petSitterAdapter
                        rvPetsitter.layoutManager = LinearLayoutManager(requireContext())
                        val deco = MaterialDividerItemDecoration(
                            requireContext(),
                            MaterialDividerItemDecoration.VERTICAL
                        )
                        rvPetsitter.addItemDecoration(deco)
                    }
                }
            }

            val schedule = viewModel.reserveSchedule.value

            reserveServiceType.text =
                if (schedule.serviceType == AppointmentMainFragment.ServiceType.JOGGING.value) {
                    "산책 서비스 예약 확인"
                } else {
                    "돌봄 서비스 예약 확인"
                }

            serviceTime.text = schedule.serviceTime

            val dateAdapter = DateAdapter(schedule.reserveDate)
            rvDate.adapter = dateAdapter
            rvDate.layoutManager = GridLayoutManager(requireContext(), 2)

            reserveTime.text = schedule.reserveTime
            reserveAddr.text = schedule.address
            reservePrice.text = reservePrice.moneyFormat(schedule.totalPrice)

            btnNext.setOnClickListener {
                if (selectedPetSitter.isNotEmpty()) {
                    val action = MatchingFragmentDirections.toPaymentFragment(selectedPetSitter)
                    Navigation.findNavController(view).navigate(action)
                } else {
                    childFragmentManager.showDialog("펫시터를 선택해 볼까요 ?", "펫시터를 선택해 주세요")
                }
            }
        }
    }
}
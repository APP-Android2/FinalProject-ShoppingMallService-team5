package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.databinding.FragmentMatchingBinding
import kr.co.lion.mungnolza.ui.appointment.adapter.PetImgAdapter
import kr.co.lion.mungnolza.ui.appointment.adapter.PetSitterAdapter
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModelFactory
import kr.co.lion.mungnolza.ui.dialog.PositiveCustomDialog

class MatchingFragment : Fragment() {

    private var _binding: FragmentMatchingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AppointmentViewModel by activityViewModels { AppointmentViewModelFactory() }
    private var selectedPetSitter: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        viewModel.fetchAllPetSitterData()

        val myPetImg = viewModel.myPetData.value.map { it.imgUrl }
        val imgAdapter = PetImgAdapter(myPetImg)
        with(binding.rvPetImg) {
            adapter = imgAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.petSitterData.collect {
                    if (it != null) {
                        val petSitterAdapter = PetSitterAdapter(it, { idx ->
                            val info = it[idx].petSitter
                            val action = MatchingFragmentDirections.toPetSitterInfoFragment(info)
                            Navigation.findNavController(view).navigate(action)
                        }, { idx ->
                            selectedPetSitter = idx
                        })

                        with(binding) {
                            with(rvPetsitter) {
                                adapter = petSitterAdapter
                                layoutManager = LinearLayoutManager(requireContext())
                                val deco = MaterialDividerItemDecoration(
                                    requireContext(),
                                    MaterialDividerItemDecoration.VERTICAL
                                )
                                addItemDecoration(deco)
                            }
                        }
                    }
                }
            }
        }

        with(binding) {
            val schedule = viewModel.reserveSchedule.value

            val date = StringBuilder()

            if (schedule != null) {
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
                reservePrice.text = schedule.totalPrice.toString()
            }

            btnNext.setOnClickListener {
                val action = MatchingFragmentDirections.toPaymentFragment()
                Navigation.findNavController(view).navigate(action)
            }
        }
        initToolbar(view)
    }

    private fun initToolbar(view: View) {
        with(binding.toolbar) {
            setNavigationOnClickListener {
                val action = MatchingFragmentDirections.toAppointmentUserAddressFragment(null)
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
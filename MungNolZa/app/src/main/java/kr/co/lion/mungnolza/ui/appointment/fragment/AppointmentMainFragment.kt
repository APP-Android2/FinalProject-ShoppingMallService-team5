package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentMainBinding
import kr.co.lion.mungnolza.ext.setColorBlack
import kr.co.lion.mungnolza.ext.setColorPrimary
import kr.co.lion.mungnolza.ext.setColorWhite
import kr.co.lion.mungnolza.ext.setColorkakaoYellow
import kr.co.lion.mungnolza.ext.showDialog
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.ui.appointment.adapter.MyPetAdapter
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModelFactory

class AppointmentMainFragment : Fragment(R.layout.fragment_appointment_main){
    private val viewModel: AppointmentViewModel by activityViewModels { AppointmentViewModelFactory() }

    private var selectedPet = mutableListOf<PetImgModel>()
    private var selectedService: String? = null
    private var careType: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAppointmentMainBinding.bind(view)

        with(binding) {
            with(rv) {
                viewLifecycleOwner.lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.myPetData.collect {
                            adapter = MyPetAdapter(it) { selectedIdx ->
                                if (selectedPet.contains(it[selectedIdx])) {
                                    selectedPet.remove(it[selectedIdx])
                                } else {
                                    selectedPet.add(it[selectedIdx])
                                }
                            }
                            layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
                        }
                    }
                }
            }

            toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
                when (checkedId) {
                    R.id.btn_entrust -> {
                        btnEntrust.backgroundTintList = requireContext().setColorkakaoYellow()
                        btnEntrust.setTextColor(requireContext().setColorBlack())

                        btnVisit.backgroundTintList = requireContext().setColorWhite()
                        btnVisit.setTextColor(requireContext().setColorPrimary())

                        viewModel.setCareType(CareType.ENTRUST.value)
                        careType = CareType.ENTRUST.value
                    }

                    R.id.btn_visit -> {
                        btnVisit.backgroundTintList = requireContext().setColorkakaoYellow()
                        btnVisit.setTextColor(requireContext().setColorBlack())

                        btnEntrust.backgroundTintList = requireContext().setColorWhite()
                        btnEntrust.setTextColor(requireContext().setColorPrimary())

                        viewModel.setCareType(CareType.VISIT.value)
                        careType = CareType.VISIT.value

                    }
                }
            }

            toolbar.setNavigationOnClickListener {
                activity?.finish()
            }

            btnJogging.setOnClickListener {
                btnJogging.backgroundTintList = requireContext().setColorkakaoYellow()
                btnJogging.setTextColor(requireContext().setColorBlack())

                btnCare.backgroundTintList = requireContext().setColorWhite()
                btnCare.setTextColor(requireContext().setColorPrimary())

                toggleGroup.visibility = GONE

                selectedService = ServiceType.JOGGING.value
                viewModel.setServiceType(ServiceType.JOGGING.value)
                viewModel.setCareType(null)
            }

            btnCare.setOnClickListener {
                btnCare.backgroundTintList = requireContext().setColorkakaoYellow()
                btnCare.setTextColor(requireContext().setColorBlack())

                btnJogging.backgroundTintList = requireContext().setColorWhite()
                btnJogging.setTextColor(requireContext().setColorPrimary())

                toggleGroup.visibility = VISIBLE

                selectedService = ServiceType.CARE.value
                viewModel.setServiceType(ServiceType.CARE.value)
            }

            btnNext.setOnClickListener {
                when (selectedService) {
                    ServiceType.JOGGING.value -> {
                        if (selectedPet.isEmpty()) {
                            childFragmentManager.showDialog("반려 동물을 선택해야 해요", "소중한 우리 아이를 선택해 주세요")
                        } else {
                            viewModel.setSelectedPet(selectedPet)
                            viewModel.setCareType(null)

                            val action = AppointmentMainFragmentDirections.toAppointmentDogTimeSelection2Fragment()
                            Navigation.findNavController(view).navigate(action)
                        }
                    }

                    ServiceType.CARE.value -> {
                        if (selectedPet.isEmpty()) {
                            childFragmentManager.showDialog("반려 동물을 선택해야 해요", "소중한 우리 아이를 선택해 주세요")
                        } else if (careType.isNullOrEmpty()) {
                            childFragmentManager.showDialog("돌봄 서비스를 선택해 볼까요?", "원하시는 돌봄 서비스를 선택해 주세요")
                        } else if (careType == CareType.ENTRUST.value) {
                            // 맡김 일 때의 처리
                            viewModel.setCareType(CareType.ENTRUST.value)
                        } else if (careType == CareType.VISIT.value) {
                            // 방문일 때의 처리
                            viewModel.setCareType(CareType.VISIT.value)
                        }

                        if (careType != null && selectedPet.isNotEmpty()) {
                            viewModel.setSelectedPet(selectedPet)
                            val action = AppointmentMainFragmentDirections.toAppointmentDogTimeSelectionFragment()
                            Navigation.findNavController(view).navigate(action)
                        }
                    }

                    else -> {
                        childFragmentManager.showDialog("서비스를 선택해 볼까요?", "원하시는 서비스를 선택해 주세요 ")
                    }
                }
            }
        }
    }

    enum class ServiceType(val value: String) {
        JOGGING("JOGGING"),
        CARE("CARE")
    }

    enum class CareType(val value: String) {
        ENTRUST("ENTRUST"),
        VISIT("VISIT")
    }
}

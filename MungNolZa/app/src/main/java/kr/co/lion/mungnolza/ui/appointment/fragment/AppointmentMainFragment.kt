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
import kr.co.lion.mungnolza.ui.appointment.adapter.MyPetAdapter
import kr.co.lion.mungnolza.ui.dialog.PositiveCustomDialog
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModelFactory

class AppointmentMainFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentAppointmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AppointmentViewModel by activityViewModels { AppointmentViewModelFactory() }


    private var selectedService: String? = null
    private var careType: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppointmentMainBinding.inflate(inflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding) {
            with(rv) {
                viewLifecycleOwner.lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED){
                        viewModel.fetchMyAllPetData()

                        viewModel.myPetData.collect{
                            if (it.isEmpty()) {
                                noPetCardview.visibility = VISIBLE
                                rv.visibility = GONE
                                serviceContainer.visibility = GONE
                            } else {
                                noPetCardview.visibility = GONE
                                rv.visibility = VISIBLE
                                serviceContainer.visibility = VISIBLE

                                adapter = MyPetAdapter(
                                    myPets = it
                                ) {

                                }
                                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
                            }
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
                        careType = CareType.ENTRUST.value
                        viewModel.setCareType(false)
                    }

                    R.id.btn_visit -> {
                        btnVisit.backgroundTintList = requireContext().setColorkakaoYellow()
                        btnVisit.setTextColor(requireContext().setColorBlack())

                        btnEntrust.backgroundTintList = requireContext().setColorWhite()
                        btnEntrust.setTextColor(requireContext().setColorPrimary())
                        careType = CareType.VISIT.value
                        viewModel.setCareType(true)
                    }
                }
            }

            toolbar.setNavigationOnClickListener{
                activity?.finish()
            }

            btnJogging.setOnClickListener(this@AppointmentMainFragment)
            btnCare.setOnClickListener(this@AppointmentMainFragment)
            btnNext.setOnClickListener(this@AppointmentMainFragment)
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v?.id) {
                R.id.btn_jogging -> {
                    btnJogging.backgroundTintList = requireContext().setColorkakaoYellow()
                    btnJogging.setTextColor(requireContext().setColorBlack())

                    btnCare.backgroundTintList = requireContext().setColorWhite()
                    btnCare.setTextColor(requireContext().setColorPrimary())

                    toggleGroup.visibility = GONE
                    selectedService = ServiceType.JOGGING.value
                    viewModel.setCareType(false)
                }

                R.id.btn_care -> {
                    btnCare.backgroundTintList = requireContext().setColorkakaoYellow()
                    btnCare.setTextColor(requireContext().setColorBlack())

                    btnJogging.backgroundTintList = requireContext().setColorWhite()
                    btnJogging.setTextColor(requireContext().setColorPrimary())

                    toggleGroup.visibility = VISIBLE
                    selectedService = ServiceType.CARE.value
                }

                R.id.btn_next -> {
                    when (selectedService) {
                        ServiceType.JOGGING.value -> {
                            val action =
                                AppointmentMainFragmentDirections.toAppointmentDogTimeSelectionFragment()
                            Navigation.findNavController(v).navigate(action)
                        }

                        ServiceType.CARE.value -> {
                            val action = AppointmentMainFragmentDirections.toAppointmentDogTimeSelection2Fragment()

                            if (careType == CareType.ENTRUST.value) {
                                // 맡김 일 때의 처리
                            } else if (careType == CareType.VISIT.value) {
                                // 방문일 때의 처리
                            } else if (careType.isNullOrEmpty()) {
                                showDialog("돌봄 서비스를 선택해 볼까요?", "원하시는 돌봄 서비스를 선택해 주세요 !")
                            }

                            if (careType != null) {
                                Navigation.findNavController(v).navigate(action)
                            }
                        }
                        else -> {
//                            if (dataList.isEmpty()){
//                                showDialog("반려 동물을 등록해야 해요", "소중한 우리 아이를 등록해 주세요")
//                            }else{
//                                showDialog("서비스를 선택해 볼까요?", "원하시는 서비스를 선택해 주세요 !")
//                            }

                        }
                    }
                }
            }
        }
    }

    private fun showDialog(title: String, message: String) {
        val dialog = PositiveCustomDialog(
            title,
            message,
            positiveButtonClick = { }
        )
        dialog.show(childFragmentManager, "PositiveCustomDialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        selectedService = null
        careType = null
        _binding = null
    }

    enum class ServiceType(val value: String) {
        JOGGING("jogging"),
        CARE("care")
    }

    enum class CareType(val value: String) {
        ENTRUST("entrust"),
        VISIT("visit")
    }
}

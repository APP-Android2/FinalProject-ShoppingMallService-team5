package kr.co.lion.mungnolza.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentHomeBinding
import kr.co.lion.mungnolza.ui.appointment.AppointmentActivity
import kr.co.lion.mungnolza.ui.appointment.NoPetActivity
import kr.co.lion.mungnolza.ui.main.viewmodel.MainViewModel
import kr.co.lion.mungnolza.ui.main.viewmodel.MainViewModelFactory
import kr.co.lion.mungnolza.ui.reservation_list.ReservationListActivity

class HomeFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels { MainViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        viewModel
        with(binding) {
            btnReserve.setOnClickListener(this@HomeFragment)
            btnReserveList.setOnClickListener(this@HomeFragment)
            btnReviewList.setOnClickListener(this@HomeFragment)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_reserve -> {
                viewLifecycleOwner.lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.myPetData.collect {
                            if (it.isEmpty()) {
                                startActivity(Intent(requireActivity(), NoPetActivity::class.java))
                            } else {
                                val intent = Intent(requireActivity(), AppointmentActivity::class.java)
                                intent.putExtra("myPet", it.toTypedArray())
                                startActivity(intent)
                            }
                        }
                    }
                }
            }

            R.id.btn_reserve_list -> {
                startActivity(Intent(requireActivity(), ReservationListActivity::class.java))
            }

            R.id.btn_review_list -> {

            }
        }

    }
}

package kr.co.lion.mungnolza.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentHomeBinding
import kr.co.lion.mungnolza.ext.repeatOnViewStarted
import kr.co.lion.mungnolza.ui.appointment.AppointmentActivity
import kr.co.lion.mungnolza.ui.appointment.NoPetActivity
import kr.co.lion.mungnolza.ui.appointment.adapter.ReviewAdapter
import kr.co.lion.mungnolza.ui.main.vm.MainViewModel
import kr.co.lion.mungnolza.ui.main.vm.MainViewModelFactory
import kr.co.lion.mungnolza.ui.realtime_location.RealtimeLocationActivity

class HomeFragment : Fragment(R.layout.fragment_home){
    private val viewModel: MainViewModel by activityViewModels { MainViewModelFactory(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

        repeatOnViewStarted {
            viewModel.myPetData.collect { data ->
                binding.btnReserve.setOnClickListener {
                    if (data.isEmpty()) {
                        startActivity(Intent(requireActivity(), NoPetActivity::class.java))
                    } else {
                        val intent = Intent(requireActivity(), AppointmentActivity::class.java)
                        intent.putExtra("myPet", data.toTypedArray())
                        startActivity(intent)
                    }
                }
            }
        }
        repeatOnViewStarted {
            viewModel.review.collect {
                Log.d("dasdsa", it.toString())
                val adapter = ReviewAdapter(it)

                with(binding) {
                    rvReview.adapter = adapter
                    rvReview.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                    btnReserveList.setOnClickListener {
                        startActivity(Intent(requireActivity(), RealtimeLocationActivity::class.java))
                    }
                }
            }
        }
    }
}

package kr.co.lion.mungnolza.ui.petsitter_reservation_list.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetsitterReservationListOngoingBinding
import kr.co.lion.mungnolza.databinding.RowPetsitterReservationListOngoingBinding
import kr.co.lion.mungnolza.ui.petsitter_reservation_list.adapter.PetsitterReservationListOngoingAdapter

class PetsitterReservationListOngoingFragment : Fragment() {

    lateinit var fragmentPetsitterReservationListOngoingBinding: FragmentPetsitterReservationListOngoingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentPetsitterReservationListOngoingBinding = FragmentPetsitterReservationListOngoingBinding.inflate(layoutInflater)
        return fragmentPetsitterReservationListOngoingBinding.root
    }

    // textViewPetsitterReservationListLastCount - 총 건수 설정
    fun setTextView(){
        fragmentPetsitterReservationListOngoingBinding.apply {
            textViewPetsitterReservationListOngoingCount.setText("총 10건")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setTextView()
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        fragmentPetsitterReservationListOngoingBinding.apply {
            recyclerPetsitterReservationListOngoing.apply {
                // 어뎁터 설정
                adapter = PetsitterReservationListOngoingAdapter()
                // 레이아웃
                layoutManager = LinearLayoutManager(requireContext())
                // 데코
                val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
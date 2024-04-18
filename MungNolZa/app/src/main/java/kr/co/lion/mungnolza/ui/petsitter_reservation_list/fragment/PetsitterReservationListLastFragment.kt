package kr.co.lion.mungnolza.ui.petsitter_reservation_list.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetsitterReservationListLastBinding
import kr.co.lion.mungnolza.databinding.FragmentReservationListLastBinding
import kr.co.lion.mungnolza.databinding.RowPetsitterReservationListLastBinding
import kr.co.lion.mungnolza.databinding.RowReservationListLastBinding
import kr.co.lion.mungnolza.ui.reservation_list.PetsitterReviewWriteActivity

class PetsitterReservationListLastFragment : Fragment() {

    lateinit var fragmentPetsitterReservationListLastBinding: FragmentPetsitterReservationListLastBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentPetsitterReservationListLastBinding = FragmentPetsitterReservationListLastBinding.inflate(layoutInflater)

        return fragmentPetsitterReservationListLastBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setTextView()
    }

    // textViewPetsitterReservationListLastCount - 총 건수 설정
    fun setTextView(){
        fragmentPetsitterReservationListLastBinding.apply {
            textViewPetsitterReservationListLastCount.setText("총 10건")
        }
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        fragmentPetsitterReservationListLastBinding.apply {
            recyclerPetsitterReservationListLast.apply {
                // 어뎁터 설정
                adapter = PetsitterReservationListLastAdapter()
                // 레이아웃
                layoutManager = LinearLayoutManager(requireContext())
                // 데코
                val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
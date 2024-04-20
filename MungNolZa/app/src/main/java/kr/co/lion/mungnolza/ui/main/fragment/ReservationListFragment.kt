package kr.co.lion.mungnolza.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentReservationListBinding
import kr.co.lion.mungnolza.ui.reservation_list.fragment.ReservationListLastFragment
import kr.co.lion.mungnolza.ui.reservation_list.fragment.ReservationListOngoingFragment


class ReservationListFragment : Fragment(R.layout.fragment_reservation_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentReservationListBinding.bind(view)

        // 진행 중인 예약과 지난 예약을 보여줄 두 개의 Fragment 객체 생성
        val reservationListOngoingFragment = ReservationListOngoingFragment()
        val reservationListLastFragment = ReservationListLastFragment()

        // 두 개의 Fragment를 담은 리스트 생성
        val fragments = arrayListOf(reservationListOngoingFragment, reservationListLastFragment)
        // ViewPager2에 사용할 어댑터 생성
        val tabAdapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return fragments.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragments[position]
            }
        }
        // ViewPager2에 어댑터 설정
        binding.viewPagerReservationList.adapter = tabAdapter
        // TabLayout과 ViewPager2를 연결하여 탭을 설정하는 코드
        TabLayoutMediator(binding.tabLayoutReservationList, binding.viewPagerReservationList) {tab,position ->
            when(position){
                0 -> tab.setText("진행 중인 예약")
                1 -> tab.setText("지난 예약")
            }
        }.attach()
    }

}



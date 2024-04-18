package kr.co.lion.mungnolza.ui.petsitter_main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetsitterReservationListBinding
import kr.co.lion.mungnolza.ui.petsitter_reservation_list.fragment.PetsitterReservationListLastFragment
import kr.co.lion.mungnolza.ui.petsitter_reservation_list.fragment.PetsitterReservationListOngoingFragment
import kr.co.lion.mungnolza.ui.reservation_list.fragment.ReservationListLastFragment
import kr.co.lion.mungnolza.ui.reservation_list.fragment.ReservationListOngoingFragment

class PetsitterReservationListFragment : Fragment() {

    lateinit var fragmentPetsitterReservationListBinding: FragmentPetsitterReservationListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentPetsitterReservationListBinding = FragmentPetsitterReservationListBinding.inflate(layoutInflater)
        return fragmentPetsitterReservationListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        // 진행 중인 예약과 지난 예약을 보여줄 두 개의 Fragment 객체 생성
        val petsitterReservationListOngoingFragment = PetsitterReservationListOngoingFragment()
        val petsitterReservationListLastFragment = PetsitterReservationListLastFragment()

        // 두 개의 Fragment를 담은 리스트 생성
        val fragments = arrayListOf(petsitterReservationListOngoingFragment, petsitterReservationListLastFragment)
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
        fragmentPetsitterReservationListBinding.viewPagerPetsitterReservationList.adapter = tabAdapter
        // TabLayout과 ViewPager2를 연결하여 탭을 설정하는 코드
        TabLayoutMediator(fragmentPetsitterReservationListBinding.tabLayoutPetsitterReservationList, fragmentPetsitterReservationListBinding.viewPagerPetsitterReservationList) {tab,position ->
            when(position){
                0 -> tab.setText("진행 중인 예약")
                1 -> tab.setText("지난 예약")
            }
        }.attach()
    }
}
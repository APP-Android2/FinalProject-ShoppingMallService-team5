package kr.co.lion.mungnolja_nayeon1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.lion.mungnolja_nayeon1.R
import kr.co.lion.mungnolja_nayeon1.ReservationListActivity
import kr.co.lion.mungnolja_nayeon1.databinding.FragmentReservationListBinding
import org.intellij.lang.annotations.JdkConstants.TabLayoutPolicy


class ReservationListFragment : Fragment() {

    lateinit var fragmentReservationListBinding: FragmentReservationListBinding
    lateinit var reservationListActivity : ReservationListActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentReservationListBinding = FragmentReservationListBinding.inflate(layoutInflater)
        reservationListActivity = activity as ReservationListActivity

        // 진행 중인 예약과 지난 예약을 보여줄 두 개의 Fragment 객체 생성
        val reservationListOngoingFragment = ReservationListOngoingFragment()
        val reservationListLastFragment = ReservationListLastFragment()

        // 두 개의 Fragment를 담은 리스트 생성
        val fragments = arrayListOf<Fragment>(reservationListOngoingFragment, reservationListLastFragment)
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
        fragmentReservationListBinding.viewPagerReservationList.adapter = tabAdapter
        // TabLayout과 ViewPager2를 연결하여 탭을 설정하는 코드
        TabLayoutMediator(fragmentReservationListBinding.tabLayoutReservationList, fragmentReservationListBinding.viewPagerReservationList) {tab,position ->
            when(position){
                0 -> tab.setText("진행 중인 예약")
                1 -> tab.setText("지난 예약")
            }
        }.attach()

        return fragmentReservationListBinding.root
    }
}



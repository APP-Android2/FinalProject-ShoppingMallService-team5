package kr.co.lion.mungnolza.ui.admin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAdminMainBinding
import kr.co.lion.mungnolza.ui.admin.AdminContentActivity
import kr.co.lion.mungnolza.ui.admin.adapter.AdminNewPetsitterAdapter
import kr.co.lion.mungnolza.ui.petsitter_reservation_list.adapter.PetsitterReservationListOngoingAdapter

class AdminMainFragment : Fragment() {

    lateinit var fragmentAdminMainBinding: FragmentAdminMainBinding
    lateinit var adminContentActivity: AdminContentActivity

    // 게시판 종류를 담을 프로퍼티
    var contentType = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentAdminMainBinding = FragmentAdminMainBinding.inflate(inflater)
        adminContentActivity = activity as AdminContentActivity

        // 게시판 종류값을 담아준다.
        contentType = arguments?.getInt("TypeNumber")!!

        settingToolbar()

        return fragmentAdminMainBinding.root
    }


     fun settingToolbar() {
        fragmentAdminMainBinding.toolbarMain.apply {
            title = ("관리자")
            setNavigationIcon(R.drawable.ic_menu_24px)
            setNavigationOnClickListener {
                //drawer를 열기
                adminContentActivity.activityAdminContentBinding.drawerLayoutContent.open()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewPetsitter()
        setTextViewPetsitter()
        setRecyclerViewReservation()
    }

    fun setTextViewPetsitter(){
        fragmentAdminMainBinding.apply {
            textViewNewPetsitterCount.setText("펫시터 지원 총 5건")
        }
    }

    fun setRecyclerViewPetsitter(){
        fragmentAdminMainBinding.apply {
            recyclerNewPetsitterList.apply {
                // 어뎁터 설정
                adapter = AdminNewPetsitterAdapter()
                // 레이아웃
                layoutManager = LinearLayoutManager(requireContext())
                // 데코
                val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    fun setRecyclerViewReservation(){
        fragmentAdminMainBinding.apply {
            recyclerNewTodayReservationList.apply {
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
//    override fun onDestroyView() {
//        super.onDestroyView()
//        fragmentAdminMainBinding = null
//    }
}
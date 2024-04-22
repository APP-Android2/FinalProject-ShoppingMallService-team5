package kr.co.lion.mungnolza.ui.admin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAdminMainBinding
import kr.co.lion.mungnolza.ui.admin.AdminContentActivity

class AdminMainFragment : Fragment() {

    lateinit var fragmentAdminMainBinding: FragmentAdminMainBinding
    lateinit var adminContentActivity: AdminContentActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentAdminMainBinding = FragmentAdminMainBinding.inflate(inflater)
        adminContentActivity = activity as AdminContentActivity

//        // 게시판 종류값을 담아준다.
//        contentType = arguments?.getInt("TypeNumber")!!

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

//    override fun onDestroyView() {
//        super.onDestroyView()
//        fragmentAdminMainBinding = null
//    }
}
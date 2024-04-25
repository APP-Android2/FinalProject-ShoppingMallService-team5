package kr.co.lion.mungnolza.ui.profile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentSettingBinding
import kr.co.lion.mungnolza.ui.profile.ProfileActivity
import kr.co.lion.mungnolza.ui.profile.dialog.DeleteAccountDialogFragment
import kr.co.lion.mungnolza.ui.profile.dialog.LogOutDialogFragment
import kr.co.lion.mungnolza.util.ProfileFragmentName

class SettingFragment : Fragment() {

    lateinit var fragmentSettingBinding: FragmentSettingBinding
    lateinit var profileActivity: ProfileActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentSettingBinding = FragmentSettingBinding.inflate(inflater)
        profileActivity = activity as ProfileActivity

        settingToolbar()
        settingLogOut()
        settingDeleteAccount()

        return fragmentSettingBinding.root
    }

    // 툴바 설정
    private fun settingToolbar(){
        fragmentSettingBinding.apply {
            toolbarSetting.apply {
                // 타이틀
                title = "환경설정"
                // Back
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                // 사용자 프로필 화면으로 전환
                setNavigationOnClickListener {
                    profileActivity.removeFragment(ProfileFragmentName.SETTING_FRAGMENT)
                }
            }
        }
    }

    // 로그아웃을 눌렀을 때
    private fun settingLogOut(){
        fragmentSettingBinding.apply {
            cardViewLogOut.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    // LogOutDialog
                    val logOutDialogFragment = LogOutDialogFragment()
                    logOutDialogFragment.show(requireActivity().supportFragmentManager, "LogOutDialogFragment")
                }
            }
        }
    }

    // 회원탈퇴를 눌렀을 때
    private fun settingDeleteAccount(){
        fragmentSettingBinding.apply {
            cardViewDeleteAccount.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    // DeleteAccountDialog
                    val deleteAccountDialogFragment = DeleteAccountDialogFragment()
                    deleteAccountDialogFragment.show(requireActivity().supportFragmentManager, "DeleteAccountDialogFragment")
                }
            }
        }
    }
}
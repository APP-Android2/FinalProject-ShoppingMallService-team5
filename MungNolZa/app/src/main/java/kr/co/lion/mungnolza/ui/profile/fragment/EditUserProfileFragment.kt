package kr.co.lion.mungnolza.ui.profile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentEditUserProfileBinding
import kr.co.lion.mungnolza.ui.profile.ProfileActivity
import kr.co.lion.mungnolza.ui.profile.dialog.EditUserAddressDialogFragment
import kr.co.lion.mungnolza.ui.profile.dialog.EditUserNickNameDialogFragment
import kr.co.lion.mungnolza.ui.profile.dialog.EditUserPhoneNumberDialogFragment
import kr.co.lion.mungnolza.util.ProfileFragmentName

class EditUserProfileFragment : Fragment() {

    lateinit var fragmentEditUserProfileBinding: FragmentEditUserProfileBinding
    lateinit var profileActivity: ProfileActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentEditUserProfileBinding = FragmentEditUserProfileBinding.inflate(inflater)
        profileActivity = activity as ProfileActivity

        settingToolbar()
        settingButtonEditUserNickName()
        settingButtonEditUserPhoneNumber()
        settingButtonEditUserAddress()

        return fragmentEditUserProfileBinding.root
    }

    // 툴바 설정
    fun settingToolbar(){
        fragmentEditUserProfileBinding.apply {
            toolbarEditUserProfile.apply {
                // 타이틀
                title = "내 프로필 수정"
                // Back
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                // 사용자 프로필 화면으로 전환
                setNavigationOnClickListener {
                    profileActivity.removeFragment(ProfileFragmentName.EDIT_USER_PROFILE_FRAGMENT)
                }
            }
        }
    }

    fun settingButtonEditUserNickName(){
        fragmentEditUserProfileBinding.apply {
            buttonEditUserProfileNickName.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    val editUserNickNameDialogFragment = EditUserNickNameDialogFragment()
                    editUserNickNameDialogFragment.show(requireActivity().supportFragmentManager, "EditUserNickNameDialogFragment")
                }
            }
        }
    }

    fun settingButtonEditUserPhoneNumber(){
        fragmentEditUserProfileBinding.apply {
            buttonEditUserProfilePhoneNumber.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    val editUserPhoneNumberDialogFragment = EditUserPhoneNumberDialogFragment()
                    editUserPhoneNumberDialogFragment.show(requireActivity().supportFragmentManager, "EditUserPhoneNumberDialogFragment")
                }
            }
        }
    }

    fun settingButtonEditUserAddress(){
        fragmentEditUserProfileBinding.apply {
            buttonEditUserProfileAddress.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    val editUserAddressDialogFragment = EditUserAddressDialogFragment()
                    editUserAddressDialogFragment.show(requireActivity().supportFragmentManager, "EditUserAddressDialogFragment")
                }
            }
        }
    }
}
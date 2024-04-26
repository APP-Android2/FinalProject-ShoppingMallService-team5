package kr.co.lion.mungnolza.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentUserProfileBinding
import kr.co.lion.mungnolza.ui.main.MainActivity
import kr.co.lion.mungnolza.ui.profile.ProfileActivity
import kr.co.lion.mungnolza.ui.profile.dialog.ManagerModeDialogFragment
import kr.co.lion.mungnolza.ui.profile.dialog.PetSitterModeDialogFragment
import kr.co.lion.mungnolza.util.ProfileFragmentName

class TestFragment : Fragment() {

    lateinit var fragmentUserProfileBinding: FragmentUserProfileBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentUserProfileBinding = FragmentUserProfileBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()
        settingButtonPetProfile()
        settingButtonEditUserProfile()
        settingButtonFollowPetSitter()
        settingButtonNotice()
        settingButtonPerSitterMode()
        settingButtonManagerMode()

        return fragmentUserProfileBinding.root
    }

    // 툴바 설정
    private fun settingToolbar(){
        fragmentUserProfileBinding.apply {
            toolbarUserProfile.apply {
                // 타이틀
                title = "내 프로필"
                // Back
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                // 메인 화면으로 전환
                setNavigationOnClickListener {

                }
                // 메뉴
                inflateMenu(R.menu.menu_user_profile)
                setOnMenuItemClickListener {
                    // 메뉴의 id로 분기한다.
                    when(it.itemId){
                        // 환경설정
                        R.id.menuItemSetting -> {
                            // MainActivity의 인스턴스를 가져와서 ProfileActivity로 전환하고 SETTING_FRAGMENT로 교체
                            val intent = Intent(mainActivity, ProfileActivity::class.java)
                            intent.putExtra("fragmentName", ProfileFragmentName.SETTING_FRAGMENT)
                            startActivity(intent)
                            //profileActivity.replaceFragment(ProfileFragmentName.SETTING_FRAGMENT, true, true, null)
                        }
                    }
                    true
                }
            }
        }
    }

    // 반려동물 프로필 버튼
    private fun settingButtonPetProfile(){
        fragmentUserProfileBinding.apply {
            buttonUserProfilePetProfile.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    // PetProfileListFragment
                    val intent = Intent(mainActivity, ProfileActivity::class.java)
                    intent.putExtra("fragmentName", ProfileFragmentName.PET_PROFILE_LIST_FRAGMENT)
                    startActivity(intent)
                    //profileActivity.replaceFragment(ProfileFragmentName.PET_PROFILE_LIST_FRAGMENT, true, true, null)
                }
            }
        }
    }
    // 내 프로필 수정 버튼
    private fun settingButtonEditUserProfile(){
        fragmentUserProfileBinding.apply {
            buttonUserProfileEditUserProfile.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    // EditUserProfileFragment
                    val intent = Intent(mainActivity, ProfileActivity::class.java)
                    intent.putExtra("fragmentName", ProfileFragmentName.EDIT_USER_PROFILE_FRAGMENT)
                    startActivity(intent)
                    //profileActivity.replaceFragment(ProfileFragmentName.EDIT_USER_PROFILE_FRAGMENT, true, true, null)
                }
            }
        }
    }
    // 펫시터 찜 버튼
    private fun settingButtonFollowPetSitter(){
        fragmentUserProfileBinding.apply {
            buttonUserProfileFollowPetSitter.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    // FollowPetSitterFragment
                    val intent = Intent(mainActivity, ProfileActivity::class.java)
                    intent.putExtra("fragmentName", ProfileFragmentName.FOLLOW_PET_SITTER_FRAGMENT)
                    startActivity(intent)
                    //profileActivity.replaceFragment(ProfileFragmentName.FOLLOW_PET_SITTER_FRAGMENT, true, true, null)
                }
            }
        }
    }
    // 공지사항 버튼
    private fun settingButtonNotice(){
        fragmentUserProfileBinding.apply {
            buttonUserProfileNotice.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    // NoticeFragment
                    val intent = Intent(mainActivity, ProfileActivity::class.java)
                    intent.putExtra("fragmentName", ProfileFragmentName.NOTICE_FRAGMENT)
                    startActivity(intent)
                    //profileActivity.replaceFragment(ProfileFragmentName.NOTICE_FRAGMENT, true, true, null)
                }
            }
        }
    }
    // 펫시터 모드 전환 버튼
    private fun settingButtonPerSitterMode(){
        fragmentUserProfileBinding.apply {
            buttonUserProfilePetSitterMode.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    // PetSitterModeDialog
                    val petSitterModeDialogFragment = PetSitterModeDialogFragment()
                    petSitterModeDialogFragment.show(requireActivity().supportFragmentManager, "PetSitterModeDialogFragment")
                }
            }
        }
    }
    // 관리자 모드 전환 버튼
    private fun settingButtonManagerMode(){
        fragmentUserProfileBinding.apply {
            buttonUserProfileAdministratorMode.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    // ManagerModeDialog
                    val managerModeDialogFragment = ManagerModeDialogFragment()
                    managerModeDialogFragment.show(requireActivity().supportFragmentManager, "ManagerModeDialogFragment")
                }
            }
        }
    }

}
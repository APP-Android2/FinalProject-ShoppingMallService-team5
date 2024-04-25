package kr.co.lion.mungnolza.ui.profile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetProfileBinding
import kr.co.lion.mungnolza.ui.profile.ProfileActivity
import kr.co.lion.mungnolza.util.ProfileFragmentName

class PetProfileFragment : Fragment() {

    lateinit var fragmentPetProfileBinding: FragmentPetProfileBinding
    lateinit var profileActivity: ProfileActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentPetProfileBinding = FragmentPetProfileBinding.inflate(inflater)
        profileActivity = activity as ProfileActivity

        settingToolbar()

        return fragmentPetProfileBinding.root
    }

    // 툴바 설정
    private fun settingToolbar(){
        fragmentPetProfileBinding.apply {
            toolbarPetProfile.apply {
                // 타이틀
                title = "반려동물 프로필"
                // Back
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                // 반려동물 프로필 목록 화면으로 전환
                setNavigationOnClickListener {
                    profileActivity.removeFragment(ProfileFragmentName.PET_PROFILE_FRAGMENT)
                }
                // 메뉴
                inflateMenu(R.menu.menu_pet_profile)
                setOnMenuItemClickListener {
                    // 메뉴의 id로 분기한다.
                    when(it.itemId){
                        // 반려동물 프로필 수정 화면으로 전환
                        R.id.menuItemEditPetProfile -> {
                            profileActivity.replaceFragment(ProfileFragmentName.EDIT_PET_PROFILE_FRAGMENT, true, true, null)
                        }
                    }
                    true
                }
            }
        }
    }
}
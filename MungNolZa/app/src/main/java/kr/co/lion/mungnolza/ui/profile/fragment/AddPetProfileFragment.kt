package kr.co.lion.mungnolza.ui.profile.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAddPetProfileBinding
import kr.co.lion.mungnolza.ui.profile.ProfileActivity
import kr.co.lion.mungnolza.util.ProfileFragmentName

class AddPetProfileFragment : Fragment() {

    lateinit var fragmentAddPetProfileBinding: FragmentAddPetProfileBinding
    lateinit var profileActivity: ProfileActivity

    // AddPetGender 토글 버튼 상태
    var isToggleButtonAddPetGenderMaleSelected = false
    var isToggleButtonAddPetGenderFemaleSelected = false

    // AddPetNeutering 토글 버튼 상태
    var isToggleButtonAddPetNeuteringYesSelected = false
    var isToggleButtonAddPetNeuteringNoSelected = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentAddPetProfileBinding = FragmentAddPetProfileBinding.inflate(inflater)
        profileActivity = activity as ProfileActivity

        initButtonState()
        settingToolbar()
        toggleButtonAddPetGender()
        toggleButtonAddPetNeutering()

        return fragmentAddPetProfileBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentAddPetProfileBinding.apply {
            toolbarAddPetProfile.apply {
                // 타이틀
                title = "반려동물 프로필 추가"
                // Back
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                // 반려동물 프로필 목록 화면으로 전환
                setNavigationOnClickListener {
                    profileActivity.removeFragment(ProfileFragmentName.ADD_PET_PROFILE_FRAGMENT)
                }

                // 메뉴
                inflateMenu(R.menu.menu_add_pet_profile)
                setOnMenuItemClickListener {
                    // 메뉴의 id로 분기한다.
                    when(it.itemId){
                        // 반려동물 프로필 목록 화면으로 전환
                        R.id.menuItemAddPetProfileDone -> {
                            profileActivity.removeFragment(ProfileFragmentName.ADD_PET_PROFILE_FRAGMENT)
                        }
                    }
                    true
                }
            }
        }
    }

    // 반려동물 성별 토글 버튼
    fun toggleButtonAddPetGender() {
        fragmentAddPetProfileBinding.apply {
            // 남자아이 버튼
            toggleButtonAddPetProfileGenderMale.setOnClickListener {
                isToggleButtonAddPetGenderMaleSelected = !isToggleButtonAddPetGenderMaleSelected
                updateButtonState(toggleButtonAddPetProfileGenderMale, isToggleButtonAddPetGenderMaleSelected)
                updateButtonState(toggleButtonAddPetProfileGenderFemale, false)
                isToggleButtonAddPetGenderFemaleSelected = false
            }
            // 여자아이 버튼
            toggleButtonAddPetProfileGenderFemale.setOnClickListener {
                isToggleButtonAddPetGenderFemaleSelected = !isToggleButtonAddPetGenderFemaleSelected
                updateButtonState(toggleButtonAddPetProfileGenderFemale, isToggleButtonAddPetGenderFemaleSelected)
                updateButtonState(toggleButtonAddPetProfileGenderMale, false)
                isToggleButtonAddPetGenderMaleSelected = false
            }
        }
    }

    // 반려동물 중성화 여부 토글 버튼
    fun toggleButtonAddPetNeutering() {
        fragmentAddPetProfileBinding.apply {
            // 했어요 버튼
            toggleButtonAddPetProfileNeuteringYes.setOnClickListener {
                isToggleButtonAddPetNeuteringYesSelected = !isToggleButtonAddPetNeuteringYesSelected
                updateButtonState(toggleButtonAddPetProfileNeuteringYes, isToggleButtonAddPetNeuteringYesSelected)
                updateButtonState(toggleButtonAddPetProfileNeuteringNo, false)
                isToggleButtonAddPetNeuteringNoSelected = false
            }
            // 안했어요 버튼
            toggleButtonAddPetProfileNeuteringNo.setOnClickListener {
                isToggleButtonAddPetNeuteringNoSelected = !isToggleButtonAddPetNeuteringNoSelected
                updateButtonState(toggleButtonAddPetProfileNeuteringNo, isToggleButtonAddPetNeuteringNoSelected)
                updateButtonState(toggleButtonAddPetProfileNeuteringYes, false)
                isToggleButtonAddPetNeuteringYesSelected = false
            }
        }
    }

    // 토글 버튼 상태 업데이트
    fun updateButtonState(button: Button, isSeleted: Boolean) {
        fragmentAddPetProfileBinding.apply {
            if (isSeleted) {
                button.setBackgroundResource(R.drawable.shared_button_design)
                button.setTextColor(Color.BLACK)
            } else {
                button.setBackgroundResource(R.drawable.shared_button_design_grey)
                button.setTextColor(Color.WHITE)
            }
        }
    }

    // 토글 버튼 상태 초기화
    fun initButtonState() {
        fragmentAddPetProfileBinding.apply {
            updateButtonState(toggleButtonAddPetProfileGenderMale, false)
            updateButtonState(toggleButtonAddPetProfileGenderFemale, false)
            updateButtonState(toggleButtonAddPetProfileNeuteringYes, false)
            updateButtonState(toggleButtonAddPetProfileNeuteringNo, false)
        }
    }
}
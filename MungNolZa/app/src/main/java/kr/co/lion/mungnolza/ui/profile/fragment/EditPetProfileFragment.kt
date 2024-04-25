package kr.co.lion.mungnolza.ui.profile.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentEditPetProfileBinding
import kr.co.lion.mungnolza.ui.profile.ProfileActivity
import kr.co.lion.mungnolza.util.ProfileFragmentName

class EditPetProfileFragment : Fragment() {

    lateinit var fragmentEditPetProfileBinding: FragmentEditPetProfileBinding
    lateinit var profileActivity: ProfileActivity

    // EditPetGender 토글 버튼 상태
    private var isToggleButtonEditPetGenderMaleSelected = false
    private var isToggleButtonEditPetGenderFemaleSelected = false

    // EditPetNeutering 토글 버튼 상태
    private var isToggleButtonEditPetNeuteringYesSelected = false
    private var isToggleButtonEditPetNeuteringNoSelected = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentEditPetProfileBinding = FragmentEditPetProfileBinding.inflate(inflater)
        profileActivity = activity as ProfileActivity

        initButtonState()
        settingToolbar()
        toggleButtonEditPetGender()
        toggleButtonEditPetNeutering()

        return fragmentEditPetProfileBinding.root
    }

    // 툴바 설정
    private fun settingToolbar() {
        fragmentEditPetProfileBinding.apply {
            toolbarEditPetProfile.apply {
                // 타이틀
                title = "반려동물 프로필 수정"
                // Back
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                // 반려동물 프로필 화면으로 전환
                setNavigationOnClickListener {
                    profileActivity.removeFragment(ProfileFragmentName.EDIT_PET_PROFILE_FRAGMENT)
                }

                // 메뉴
                inflateMenu(R.menu.menu_edit_pet_profile)
                setOnMenuItemClickListener {
                    // 메뉴의 id로 분기한다.
                    when(it.itemId){
                        // 반려동물 프로필 화면으로 전환
                        R.id.menuItemEditPetProfileDone -> {
                            profileActivity.removeFragment(ProfileFragmentName.EDIT_PET_PROFILE_FRAGMENT)
                        }
                    }
                    true
                }
            }
        }
    }

    // 반려동물 성별 토글 버튼
    private fun toggleButtonEditPetGender() {
        fragmentEditPetProfileBinding.apply {
            // 남자아이 토글 버튼
            toggleButtonEditPetProfileGenderMale.setOnClickListener {
                isToggleButtonEditPetGenderMaleSelected = !isToggleButtonEditPetGenderMaleSelected
                updateButtonState(toggleButtonEditPetProfileGenderMale, isToggleButtonEditPetGenderMaleSelected)
                updateButtonState(toggleButtonEditPetProfileGenderFemale, false)
                isToggleButtonEditPetGenderFemaleSelected = false
            }
            // 여자아이 토글 버튼
            toggleButtonEditPetProfileGenderFemale.setOnClickListener {
                isToggleButtonEditPetGenderFemaleSelected = !isToggleButtonEditPetGenderFemaleSelected
                updateButtonState(toggleButtonEditPetProfileGenderFemale, isToggleButtonEditPetGenderFemaleSelected)
                updateButtonState(toggleButtonEditPetProfileGenderMale, false)
                isToggleButtonEditPetGenderMaleSelected = false
            }
        }
    }

    // 토글 버튼 상태 업데이트
    private fun updateButtonState(button: Button, isSeleted: Boolean) {
        fragmentEditPetProfileBinding.apply {
            if (isSeleted) {
                button.setBackgroundResource(R.drawable.shared_button_design)
                button.setTextColor(Color.BLACK)
            } else {
                button.setBackgroundResource(R.drawable.shared_button_design_grey)
                button.setTextColor(Color.WHITE)
            }
        }
    }

    // 반려동물 중성화 여부 토글 버튼
    private fun toggleButtonEditPetNeutering() {
        fragmentEditPetProfileBinding.apply {
            // 했어요 토글 버튼
            toggleButtonEditPetProfileNeuteringYes.setOnClickListener {
                isToggleButtonEditPetNeuteringYesSelected = !isToggleButtonEditPetNeuteringYesSelected
                updateButtonState(toggleButtonEditPetProfileNeuteringYes, isToggleButtonEditPetNeuteringYesSelected)
                updateButtonState(toggleButtonEditPetProfileNeuteringNo, false)
                isToggleButtonEditPetNeuteringNoSelected = false
            }
            // 안했어요 토글 버튼
            toggleButtonEditPetProfileNeuteringNo.setOnClickListener {
                isToggleButtonEditPetNeuteringNoSelected = !isToggleButtonEditPetNeuteringNoSelected
                updateButtonState(toggleButtonEditPetProfileNeuteringNo, isToggleButtonEditPetNeuteringNoSelected)
                updateButtonState(toggleButtonEditPetProfileNeuteringYes, false)
                isToggleButtonEditPetNeuteringYesSelected = false
            }
        }
    }

    // 토글 버튼 상태 초기화
    private fun initButtonState() {
        fragmentEditPetProfileBinding.apply {
            updateButtonState(toggleButtonEditPetProfileGenderMale, false)
            updateButtonState(toggleButtonEditPetProfileGenderFemale, false)
            updateButtonState(toggleButtonEditPetProfileNeuteringYes, false)
            updateButtonState(toggleButtonEditPetProfileNeuteringNo, false)
        }
    }
}
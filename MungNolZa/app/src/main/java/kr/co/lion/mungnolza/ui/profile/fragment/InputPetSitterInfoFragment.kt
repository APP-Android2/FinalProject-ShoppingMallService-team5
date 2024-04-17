package kr.co.lion.mungnolza.ui.profile.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentInputPetSitterInfoBinding
import kr.co.lion.mungnolza.ui.profile.ProfileActivity
import kr.co.lion.mungnolza.ui.profile.dialog.PetSitterSubmitDialogFragment
import kr.co.lion.mungnolza.util.ProfileFragmentName

class InputPetSitterInfoFragment : Fragment() {

    lateinit var fragmentInputPetSitterInfoBinding: FragmentInputPetSitterInfoBinding
    lateinit var profileActivity: ProfileActivity

    var isToggleButtonPetSitterCareYesSeleted = false
    var isToggleButtonPetSitterCareNoSeleted = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentInputPetSitterInfoBinding = FragmentInputPetSitterInfoBinding.inflate(inflater)
        profileActivity = activity as ProfileActivity

        initButtonState()
        settingToolbar()
        toggleButtonPetCare()
        settingButtonPetSitterSubmit()

        return fragmentInputPetSitterInfoBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentInputPetSitterInfoBinding.apply {
            toolbarInputPetSitterInfo.apply {
                // 타이틀
                title = "펫시터 지원하기"
                // Back
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                // 사용자 프로필 화면으로 전환
                setNavigationOnClickListener {
                    profileActivity.removeFragment(ProfileFragmentName.INPUT_PET_SITTER_FRAGMENT)
                }
            }
        }
    }

    // 반려동물 반려 경험 유무 토글 버튼
    fun toggleButtonPetCare() {
        fragmentInputPetSitterInfoBinding.apply {
            toggleButtonPetSitterCareYes.setOnClickListener {
                isToggleButtonPetSitterCareYesSeleted = !isToggleButtonPetSitterCareYesSeleted
                updateButtonState(toggleButtonPetSitterCareYes, isToggleButtonPetSitterCareYesSeleted)
                updateButtonState(toggleButtonPetSitterCareNo, false)
                isToggleButtonPetSitterCareNoSeleted = false

                layoutPetSitterCareYes.visibility = if (isToggleButtonPetSitterCareYesSeleted) View.VISIBLE else View.GONE
            }
            toggleButtonPetSitterCareNo.setOnClickListener {
                isToggleButtonPetSitterCareNoSeleted = !isToggleButtonPetSitterCareNoSeleted
                updateButtonState(toggleButtonPetSitterCareNo, isToggleButtonPetSitterCareNoSeleted)
                updateButtonState(toggleButtonPetSitterCareYes, false)
                isToggleButtonPetSitterCareYesSeleted = false

                layoutPetSitterCareYes.visibility = View.GONE
            }
        }
    }

    fun updateButtonState(button: Button, isSeleted: Boolean) {
        fragmentInputPetSitterInfoBinding.apply {
            if (isSeleted) {
                button.setBackgroundColor(Color.parseColor("#ffea9f"))
                button.setTextColor(Color.BLACK)
            } else {
                button.setBackgroundColor(Color.GRAY)
                button.setTextColor(Color.WHITE)
            }
        }
    }

    fun initButtonState() {
        fragmentInputPetSitterInfoBinding.apply {
            updateButtonState(toggleButtonPetSitterCareYes, false)
            updateButtonState(toggleButtonPetSitterCareNo, false)
            layoutPetSitterCareYes.visibility = View.GONE
        }
    }

    fun settingButtonPetSitterSubmit(){
        fragmentInputPetSitterInfoBinding.apply {
            buttonInputPetSitterSubmit.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    val petSitterSubmitDialogBinding = PetSitterSubmitDialogFragment()
                    petSitterSubmitDialogBinding.show(requireActivity().supportFragmentManager, "PetSitterSubmitDialogFragment")
                }
            }
        }
    }
}
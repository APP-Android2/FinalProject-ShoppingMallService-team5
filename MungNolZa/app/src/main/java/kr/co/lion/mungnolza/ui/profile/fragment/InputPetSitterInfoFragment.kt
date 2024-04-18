package kr.co.lion.mungnolza.ui.profile.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentInputPetSitterInfoBinding
import kr.co.lion.mungnolza.ui.profile.ProfileActivity
import kr.co.lion.mungnolza.ui.profile.dialog.PetSitterSubmitDialogFragment
import kr.co.lion.mungnolza.util.ProfileFragmentName

class InputPetSitterInfoFragment : Fragment() {

    lateinit var fragmentInputPetSitterInfoBinding: FragmentInputPetSitterInfoBinding
    lateinit var profileActivity: ProfileActivity

    // PetSitterCare 토글 버튼 상태
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

    // 펫시터 지원하기 버튼
    fun settingButtonPetSitterSubmit(){
        fragmentInputPetSitterInfoBinding.apply {
            buttonInputPetSitterSubmit.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    // PetSitterSubmitDialog
                    val petSitterSubmitDialogBinding = PetSitterSubmitDialogFragment()
                    petSitterSubmitDialogBinding.show(requireActivity().supportFragmentManager, "PetSitterSubmitDialogFragment")
                }
            }
        }
    }



    // 반려동물 반려 경험 유무 토글 버튼
    fun toggleButtonPetCare() {
        fragmentInputPetSitterInfoBinding.apply {
            // 있어요
            toggleButtonPetSitterCareYes.setOnClickListener {
                isToggleButtonPetSitterCareYesSeleted = !isToggleButtonPetSitterCareYesSeleted
                updateButtonState(toggleButtonPetSitterCareYes, isToggleButtonPetSitterCareYesSeleted)
                updateButtonState(toggleButtonPetSitterCareNo, false)
                isToggleButtonPetSitterCareNoSeleted = false

                // 레이아웃 출력
                layoutPetSitterCareYes.visibility = if (isToggleButtonPetSitterCareYesSeleted) View.VISIBLE else View.GONE
            }
            // 없어요
            toggleButtonPetSitterCareNo.setOnClickListener {
                isToggleButtonPetSitterCareNoSeleted = !isToggleButtonPetSitterCareNoSeleted
                updateButtonState(toggleButtonPetSitterCareNo, isToggleButtonPetSitterCareNoSeleted)
                updateButtonState(toggleButtonPetSitterCareYes, false)
                isToggleButtonPetSitterCareYesSeleted = false

                // 레이아웃 숨기기
                layoutPetSitterCareYes.visibility = View.GONE
            }
        }
    }

    // 토글 버튼 상태 업데이트
    fun updateButtonState(button: Button, isSeleted: Boolean) {
        fragmentInputPetSitterInfoBinding.apply {
            // 버튼이 클릭 됐을 때
            if (isSeleted) {
                button.setBackgroundResource(R.drawable.shared_button_design)
                button.setTextColor(Color.BLACK)
            }
            // 클릭이 해제 됐을 때
            else {
                button.setBackgroundResource(R.drawable.shared_button_design_grey)
                button.setTextColor(Color.WHITE)
            }
        }
    }

    // 토글 버튼 상태 초기화
    fun initButtonState() {
        fragmentInputPetSitterInfoBinding.apply {
            updateButtonState(toggleButtonPetSitterCareYes, false)
            updateButtonState(toggleButtonPetSitterCareNo, false)
            layoutPetSitterCareYes.visibility = View.GONE
        }
    }
}
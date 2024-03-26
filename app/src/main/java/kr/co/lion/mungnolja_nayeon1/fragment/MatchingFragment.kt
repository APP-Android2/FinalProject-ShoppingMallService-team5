package kr.co.lion.mungnolja_nayeon1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.mungnolja_nayeon1.MainActivity
import kr.co.lion.mungnolja_nayeon1.MainFragmentName
import kr.co.lion.mungnolja_nayeon1.R
import kr.co.lion.mungnolja_nayeon1.databinding.FragmentMatchingBinding

class MatchingFragment : Fragment() {

    lateinit var fragmentMatchingBinding: FragmentMatchingBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentMatchingBinding = FragmentMatchingBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        setToolbar()
        setButtonMatching()

        return fragmentMatchingBinding.root
    }

    // 툴 바 설정
    fun setToolbar(){
        fragmentMatchingBinding.apply {
            toolbarMatching.apply {
                // 타이틀
                title = "펫시터 매칭"
                // 뒤로가기
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    // 코드 합치면 그때 작성할 것
                }
            }
        }
    }

    // 선택 완료 버튼
    fun setButtonMatching(){
        fragmentMatchingBinding.apply {
            buttonMatching.apply {
                // 버튼을 눌렀을떄
                setOnClickListener {
                    // PetSitterInfoFragment가 보여지게 한다
                    mainActivity.replaceFragment(MainFragmentName.PETSITTER_INFO_FRAGMENT,true, true, null)
                }
            }
        }
    }
}
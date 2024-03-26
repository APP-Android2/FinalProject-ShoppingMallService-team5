package kr.co.lion.mungnolja_nayeon1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.mungnolja_nayeon1.MainActivity
import kr.co.lion.mungnolja_nayeon1.MainFragmentName
import kr.co.lion.mungnolja_nayeon1.R
import kr.co.lion.mungnolja_nayeon1.databinding.FragmentPetSitterInfoBinding

class PetSitterInfoFragment : Fragment() {

    lateinit var fragmentPetSitterInfoBinding: FragmentPetSitterInfoBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentPetSitterInfoBinding = FragmentPetSitterInfoBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        setToolbar()

        return fragmentPetSitterInfoBinding.root
    }

    // 툴바 설정
    fun setToolbar(){
        fragmentPetSitterInfoBinding.apply {
            toolbarPetsitterInfo.apply {
                // 타이틀 설정
                title = "펫시터 정보"
                // 뒤로가기
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    // 코드 합치면 그때 작성할 것
                    mainActivity.removeFragment(MainFragmentName.PETSITTER_INFO_FRAGMENT)
                }
            }
        }
    }
}
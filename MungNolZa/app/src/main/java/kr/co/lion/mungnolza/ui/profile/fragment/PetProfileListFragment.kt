package kr.co.lion.mungnolza.ui.profile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetProfileListBinding
import kr.co.lion.mungnolza.databinding.RowPetProfileListBinding
import kr.co.lion.mungnolza.ui.profile.ProfileActivity
import kr.co.lion.mungnolza.util.ProfileFragmentName

class PetProfileListFragment : Fragment() {

    lateinit var fragmentPetProfileListBinding: FragmentPetProfileListBinding
    lateinit var profileActivity: ProfileActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentPetProfileListBinding = FragmentPetProfileListBinding.inflate(inflater)
        profileActivity = activity as ProfileActivity

        settingToolbar()
        settingRecyclerViewPetProfile()

        return fragmentPetProfileListBinding.root
    }

    // 툴바 설정
    fun settingToolbar(){
        fragmentPetProfileListBinding.apply {
            toolbarPetProfileList.apply {
                // 타이틀
                title = "내 반려동물 목록"
                // Back
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                // 사용자 프로필 화면
                setNavigationOnClickListener {
                    profileActivity.removeFragment(ProfileFragmentName.PET_PROFILE_LIST_FRAGMENT)
                }
                // 메뉴
                inflateMenu(R.menu.menu_pet_profile_list)
                setOnMenuItemClickListener {
                    // 메뉴의 id로 분기한다.
                    when(it.itemId){
                        // 반려동물 프로필 추가 화면
                        R.id.menuItemAddPetProfile -> {
                            profileActivity.replaceFragment(ProfileFragmentName.ADD_PET_PROFILE_FRAGMENT, true, true, null)
                        }
                    }
                    true
                }
            }
        }
    }

    // PetProfileList 화면의 RecyclerView 설정
    fun settingRecyclerViewPetProfile(){
        fragmentPetProfileListBinding.apply {
            recyclerViewPetProfileList.apply {
                // 어댑터
                adapter = PetProfileRecyclerViewAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(profileActivity)
//                // 데코레이션
//                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
//                addItemDecoration(deco)

            }
        }
    }

    // PetProfileList 화면의 RecyclerView 어댑터
    inner class PetProfileRecyclerViewAdapter : RecyclerView.Adapter<PetProfileRecyclerViewAdapter.PetProfileViewHolder>(){
        inner class PetProfileViewHolder(rowPetProfileListBinding: RowPetProfileListBinding) : RecyclerView.ViewHolder(rowPetProfileListBinding.root){
            val rowPetProfileListBinding: RowPetProfileListBinding

            init {
                this.rowPetProfileListBinding = rowPetProfileListBinding
                this.rowPetProfileListBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetProfileViewHolder {
            val petProfileListBinding = RowPetProfileListBinding.inflate(layoutInflater)
            val petProfileViewHolder = PetProfileViewHolder(petProfileListBinding)
            return petProfileViewHolder
        }

        override fun getItemCount(): Int {
            return 50
        }

        override fun onBindViewHolder(holder: PetProfileViewHolder, position: Int) {
            // position 번째 항목 출력
            holder.rowPetProfileListBinding.textViewRowPetProfileListPetName.text = "멍뭉이 $position"
            holder.rowPetProfileListBinding.textViewRowPetProfileListPetBreed.text = "보더콜리 $position"
            holder.rowPetProfileListBinding.textViewRowPetProfileListPetAgeWeight.text = "4살 / 6.5Kg $position"

            // position 번째 항목을 눌렀을 때
            holder.rowPetProfileListBinding.root.setOnClickListener {
                // 반려동물 프로필 화면
                profileActivity.replaceFragment(ProfileFragmentName.PET_PROFILE_FRAGMENT, true, true, null)
            }
        }
    }
}
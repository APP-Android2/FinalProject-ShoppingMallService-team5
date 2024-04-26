package kr.co.lion.mungnolza.ui.profile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentFollowPetSitterBinding
import kr.co.lion.mungnolza.databinding.RowFollowPetSitterBinding
import kr.co.lion.mungnolza.ui.profile.ProfileActivity
import kr.co.lion.mungnolza.ui.profile.dialog.DeleteFollowPetSitterDialogFragment
import kr.co.lion.mungnolza.ui.profile.dialog.ManagerModeDialogFragment
import kr.co.lion.mungnolza.util.ProfileFragmentName

class FollowPetSitterFragment : Fragment() {
    lateinit var fragmentFollowPetSitterBinding: FragmentFollowPetSitterBinding
    lateinit var profileActivity: ProfileActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentFollowPetSitterBinding = FragmentFollowPetSitterBinding.inflate(inflater)
        profileActivity = activity as ProfileActivity

        settingToolbar()
        settingRecyclerViewFollowPetSitter()

        return fragmentFollowPetSitterBinding.root
    }

    // 툴바 설정
    private fun settingToolbar(){
        fragmentFollowPetSitterBinding.apply {
            toolbarFollowPetSitter.apply {
                // 타이틀
                title = "펫시터 찜"
                // Back
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                // 사용자 프로필 화면으로 전환
                setNavigationOnClickListener {
                    profileActivity.removeFragment(ProfileFragmentName.FOLLOW_PET_SITTER_FRAGMENT)
                }
            }
        }
    }

    // FollowPetSitter 화면의 RecyclerView 설정
    private fun settingRecyclerViewFollowPetSitter(){
        fragmentFollowPetSitterBinding.apply {
            recyclerVIewFollowPetSitter.apply {
                // 어댑터
                adapter = FollowPetSitterRecyclerViewAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(profileActivity)
//                // 데코레이션
//                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
//                addItemDecoration(deco)

            }
        }
    }

    // FollowPetSitter 화면의 RecyclerView 어댑터
    private inner class FollowPetSitterRecyclerViewAdapter : RecyclerView.Adapter<FollowPetSitterRecyclerViewAdapter.FollowPetSitterViewHolder>(){
        inner class FollowPetSitterViewHolder(rowFollowPetSitterBinding: RowFollowPetSitterBinding) : RecyclerView.ViewHolder(rowFollowPetSitterBinding.root){
            val rowFollowPetSitterBinding: RowFollowPetSitterBinding

            init {
                this.rowFollowPetSitterBinding = rowFollowPetSitterBinding
                this.rowFollowPetSitterBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowPetSitterViewHolder {
            val followPetSitterBinding = RowFollowPetSitterBinding.inflate(layoutInflater)
            val followPetSitterViewHolder = FollowPetSitterViewHolder(followPetSitterBinding)
            return followPetSitterViewHolder
        }

        override fun getItemCount(): Int {
            return 50
        }

        override fun onBindViewHolder(holder: FollowPetSitterViewHolder, position: Int) {
            // position 번째 항목 출력
            holder.rowFollowPetSitterBinding.textViewRowFollowPetSitterName.text = "펫시터 $position"
            holder.rowFollowPetSitterBinding.textViewRowFollowPetSitterReview.text = "5.0 (${position}개의 후기)"
            holder.rowFollowPetSitterBinding.textViewRowFollowPetSitterExp.text = "${position}년 경력"
            holder.rowFollowPetSitterBinding.textViewRowFollowPetSitterDistance.text = "${position}km"

            // 각 항목의 하트 아이콘 설정
            holder.rowFollowPetSitterBinding.imageViewRowFollowPetSitterLike.apply {
                setOnClickListener {
                    setImageResource(R.drawable.ic_favorite_empty_24px)
                    // DeleteFollowPetSitterDialog
                    val deleteFollowPetSitterFragment = DeleteFollowPetSitterDialogFragment()
                    deleteFollowPetSitterFragment.show(requireActivity().supportFragmentManager, "DeleteFollowPetSitterDialogFragment")
                }
            }
        }
    }
}
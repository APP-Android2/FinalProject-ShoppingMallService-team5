package kr.co.lion.mungnolza.ui.profile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentNoticeBinding
import kr.co.lion.mungnolza.databinding.RowNoticeBinding
import kr.co.lion.mungnolza.ui.profile.ProfileActivity
import kr.co.lion.mungnolza.ui.profile.dialog.NoticeDialogFragment
import kr.co.lion.mungnolza.util.ProfileFragmentName

class NoticeFragment : Fragment() {

    lateinit var fragmentNoticeBinding: FragmentNoticeBinding
    lateinit var profileActivity: ProfileActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentNoticeBinding = FragmentNoticeBinding.inflate(inflater)
        profileActivity = activity as ProfileActivity

        settingToolbar()
        settingRecyclerViewNotice()

        return fragmentNoticeBinding.root
    }

    // 툴바 설정
    fun settingToolbar(){
        fragmentNoticeBinding.apply {
            toolbarNotice.apply {
                // 타이틀
                title = "공지사항"
                // Back
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                // 사용자 프로필 화면으로 전환
                setNavigationOnClickListener {
                    profileActivity.removeFragment(ProfileFragmentName.NOTICE_FRAGMENT)
                }
            }
        }
    }

    // Notice 화면의 RecyclerView 설정
    fun settingRecyclerViewNotice(){
        fragmentNoticeBinding.apply {
            recyclerVIewNotice.apply {
                // 어댑터
                adapter = NoticeRecyclerViewAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(profileActivity)
//                // 데코레이션
//                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
//                addItemDecoration(deco)

            }
        }
    }

    // Notice 화면의 RecyclerView 어댑터
    inner class NoticeRecyclerViewAdapter : RecyclerView.Adapter<NoticeRecyclerViewAdapter.NoticeViewHolder>(){
        inner class NoticeViewHolder(rowNoticeBinding: RowNoticeBinding) : RecyclerView.ViewHolder(rowNoticeBinding.root){
            val rowNoticeBinding: RowNoticeBinding

            init {
                this.rowNoticeBinding = rowNoticeBinding
                this.rowNoticeBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
            val NoticeBinding = RowNoticeBinding.inflate(layoutInflater)
            val NoticeViewHolder = NoticeViewHolder(NoticeBinding)
            return NoticeViewHolder
        }

        override fun getItemCount(): Int {
            return 50
        }

        override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
            // position 번째 항목 출력
            holder.rowNoticeBinding.textViewNoticeTitle.text = "만우절 기념 앱 서비스 종료 $position"
            holder.rowNoticeBinding.textViewNoticeDate.text = "2024-04-01 $position"

            // 항목을 눌렀을 때
            holder.rowNoticeBinding.root.setOnClickListener {
                // 공지사항 다이얼로그
                val noticeDialogFragment = NoticeDialogFragment()
                noticeDialogFragment.show(requireActivity().supportFragmentManager, "NoticeDialogFragment")
            }
        }
    }
}
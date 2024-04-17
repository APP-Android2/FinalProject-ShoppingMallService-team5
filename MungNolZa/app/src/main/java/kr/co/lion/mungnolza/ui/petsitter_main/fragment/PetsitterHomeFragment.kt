package kr.co.lion.mungnolza.ui.petsitter_main.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetsitterHomeBinding
import kr.co.lion.mungnolza.databinding.RowPetsitterHomeChatPersonListBinding
import kr.co.lion.mungnolza.ui.petsitter_main.PetsitterMainActivity
import kr.co.lion.mungnolza.ui.realtime_location.RealtimeLocationActivity

class PetsitterHomeFragment : Fragment() {

    lateinit var fragmentPetsitterHomeBinding : FragmentPetsitterHomeBinding
    lateinit var petsitterMainActivity: PetsitterMainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentPetsitterHomeBinding = FragmentPetsitterHomeBinding.inflate(inflater, container, false)
        petsitterMainActivity = activity as PetsitterMainActivity

        return fragmentPetsitterHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        setButtonEvent()
    }

    // 버튼 이벤트 설정
    fun setButtonEvent(){
        fragmentPetsitterHomeBinding.apply {
            // 요청된 예약 목록
            imageViewRowMatchingReviewButton.setOnClickListener {
                // PetsitterReservationListFragment를 띄워준다.

            }
            // 채팅 목록
            // 게시판 목록
            // 실시간 위치
            cardViewPetsitterGoRealtimeLocation.setOnClickListener {
                // RealtimeLocationActivity 실시간 펫 위치 화면을 띄워준다
                startActivity(Intent(requireActivity(), RealtimeLocationActivity::class.java))
            }
            // 나의 리뷰 보기
        }
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        fragmentPetsitterHomeBinding.apply {
            recyclerViewPetsitterHomeChatList.apply {
                // 어뎁터 설정
                adapter = RecyclerMainAdapter()
                // 레이아웃
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                // 데코
                //val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.HORIZONTAL)
                //addItemDecoration(deco)
            }
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerMainAdapter : RecyclerView.Adapter<RecyclerMainAdapter.RecyclerMainViewHolder>(){
       inner class RecyclerMainViewHolder(rowPetsitterHomeChatPersonListBinding: RowPetsitterHomeChatPersonListBinding) : RecyclerView.ViewHolder(rowPetsitterHomeChatPersonListBinding.root){
           val rowPetsitterHomeChatPersonListBinding : RowPetsitterHomeChatPersonListBinding

           init {
               this.rowPetsitterHomeChatPersonListBinding = rowPetsitterHomeChatPersonListBinding
               this.rowPetsitterHomeChatPersonListBinding.root.layoutParams = ViewGroup.LayoutParams(
                   ViewGroup.LayoutParams.WRAP_CONTENT,
                   ViewGroup.LayoutParams.MATCH_PARENT
               )
           }
       }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerMainViewHolder {
            val rowPetsitterHomeChatPersonListBinding = RowPetsitterHomeChatPersonListBinding.inflate(layoutInflater, parent, false)
            val recyclerMainViewHolder = RecyclerMainViewHolder(rowPetsitterHomeChatPersonListBinding)

            return recyclerMainViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: RecyclerMainViewHolder, position: Int) {
            holder.rowPetsitterHomeChatPersonListBinding.imageViewRowPetsitterHomeChatListPerson.setImageResource(R.drawable.eunwoo)
            holder.rowPetsitterHomeChatPersonListBinding.textViewRowPetsitterHomeChatListPersonName.text = "영준"
        }
    }
}
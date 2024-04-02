package kr.co.lion.mungnolza.ui.main.appointment.fragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentAppointmentMainBinding
import kr.co.lion.mungnolza.model.PetInfo
import kr.co.lion.mungnolza.ui.main.MainFragmentName
import kr.co.lion.mungnolza.ui.main.appointment.adapter.SelectPetAdapter
import kr.co.lion.mungnolza.ui.main.showAlertDialog


class AppointmentMainFragment : Fragment() {
    private var _binding: FragmentAppointmentMainBinding? = null
    private val binding get() = _binding!!

    private val dataList = ArrayList<PetInfo>()

    private val rvAdapter: SelectPetAdapter by lazy {
        SelectPetAdapter(dataList)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAppointmentMainBinding.inflate(inflater)

        initView()
        return binding.root
    }

    private fun getBitmapFromDrawable(context: Context, id: Int): Bitmap {
        return BitmapFactory.decodeResource(context.resources, id)
    }

    private fun initView(){
        // 테스트용 입니다!
        dataList.add(PetInfo(getBitmapFromDrawable(requireContext(), R.drawable.doog),
            "바둑이", "진돗개", "남자아이", 6, "20.2", "했어요", ""))

        with(binding){
            with(rv){

                if(dataList.isEmpty()){
                    noPetCardview.visibility = VISIBLE
                    rv.visibility = GONE
                }else{
                    noPetCardview.visibility = GONE
                    rv.visibility = VISIBLE

                    adapter = rvAdapter
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
                }
            }

        }
    }

    //다음화면으로
//    fun settingMainNextButton() {
//        binding.apply {
//            buttonAppointmentMainNext.setOnClickListener {
//                var isAllSelected = true
//
//                // 예약 유형 중 하나라도 선택되지 않았는지 확인
//                if (!buttonmainwalkappointment.isSelected && !buttonmaintakecareappointment.isSelected) {
//                    showAlertDialog("예약 유형을 선택해 주세요.")
//                    isAllSelected = false
//                }
//                // 방문 유형 중 하나라도 선택되지 않았는지 확인
//                if (!selecttimefordoguntil.isSelected && !buttonmainvisitdog.isSelected) {
//                    showAlertDialog("방문 유형을 선택해 주세요.")
//                    isAllSelected = false
//                }
//
//                // 모든 조건을 충족시키면 다음 화면으로 넘어감
//                if(isAllSelected) {
//                    mainActivity.replaceFragment(
//                        MainFragmentName.APPOINTMENT_DOG_TIME_SELECTION,
//                        true,
//                        true,
//                        null
//                    )
//                }
//            }
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

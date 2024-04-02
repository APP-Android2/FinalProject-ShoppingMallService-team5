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
import kr.co.lion.mungnolza.ui.main.appointment.adapter.SelectPetAdapter


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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

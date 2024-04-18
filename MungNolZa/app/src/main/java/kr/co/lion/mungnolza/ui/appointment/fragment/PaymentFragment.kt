package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.Navigation
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initToolbar(view)
    }

    private fun initView(){
        // 스피너
        val spinner1 = resources.getStringArray(R.array.Spinner1)
        val spinner2 = resources.getStringArray(R.array.Spinner2)
        val adapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinner1)
        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinner2)

        with(binding){
            spinnerPaymentCardType.adapter = adapter1
            spinnerPaymentCardPeriod.adapter = adapter2
            btnCredit.setOnClickListener(this@PaymentFragment)
            btnPayco.setOnClickListener(this@PaymentFragment)
            btnKakaoPay.setOnClickListener(this@PaymentFragment)
            btnNaverPay.setOnClickListener(this@PaymentFragment)
            btnDone.setOnClickListener(this@PaymentFragment)
        }
    }

    private fun initToolbar(view: View) {
        with(binding.toolbar) {
            setNavigationOnClickListener {
                val action = PaymentFragmentDirections.ToMatchingFragment()
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_credit -> {

            }
            R.id.btn_payco -> {

            }
            R.id.btn_naver_pay -> {

            }
            R.id.btn_kakao_pay -> {

            }
            R.id.btn_done -> {
                val action = PaymentFragmentDirections.toReservationConfirmedFragment()
                Navigation.findNavController(v).navigate(action)
            }
        }
    }
}



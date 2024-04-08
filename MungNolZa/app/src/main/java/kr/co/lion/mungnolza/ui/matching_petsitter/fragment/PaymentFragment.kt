package kr.co.lion.mungnolza.ui.matching_petsitter.fragment

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPaymentBinding
import kr.co.lion.mungnolza.ui.matching_petsitter.MatchingPetsitterActivity
import kr.co.lion.mungnolza.util.MatchingPetsitterFragmentName

class PaymentFragment : Fragment() {

    lateinit var fragmentPaymentBinding: FragmentPaymentBinding
    lateinit var matchingPetsitterActivity: MatchingPetsitterActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentPaymentBinding = FragmentPaymentBinding.inflate(layoutInflater)
        matchingPetsitterActivity = activity as MatchingPetsitterActivity

        // 스피너
        val spinner1 = resources.getStringArray(R.array.Spinner1)
        val spinner2 = resources.getStringArray(R.array.Spinner2)
        val adapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinner1)
        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinner2)
        fragmentPaymentBinding.spinnerPaymentCardType.adapter = adapter1
        fragmentPaymentBinding.spinnerPaymentCardPeriod.adapter = adapter2

        setToolbar()
        setButtonPaymentDone()

        return fragmentPaymentBinding.root
    }

    // 툴바 설정
    fun setToolbar(){
        fragmentPaymentBinding.apply {
            toolbarPayment.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.ic_arrow_back_24px)
                setNavigationOnClickListener {
                    matchingPetsitterActivity.removeFragment(MatchingPetsitterFragmentName.PAYMENT_FRAGMENT)
                }
            }
        }
    }

    // 결제 완료 버튼
    fun setButtonPaymentDone(){
        fragmentPaymentBinding.apply {
            buttonPaymentDone.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    // 결제완료 다이어로그를 띄운다

                    val materialAlertDialogBuilder = MaterialAlertDialogBuilder(matchingPetsitterActivity)
                    materialAlertDialogBuilder.setMessage("결제가 완료되었습니다\n 감사합니다")
                    materialAlertDialogBuilder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                        // 예약확정fragment로 넘어간다
                        matchingPetsitterActivity.replaceFragment(MatchingPetsitterFragmentName.RESERVATION_CONFIRMED_FRAGMENT, true, true, null)
                    }
                    materialAlertDialogBuilder.show()

                }
            }
        }
    }
}
package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPaymentBinding
import kr.co.lion.mungnolza.ext.moneyFormat
import kr.co.lion.mungnolza.ext.setColorPrimary
import kr.co.lion.mungnolza.ext.setColorWhite
import kr.co.lion.mungnolza.ext.showDialog
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModel
import kr.co.lion.mungnolza.ui.appointment.vm.AppointmentViewModelFactory

class PaymentFragment : Fragment(R.layout.fragment_payment){

    private val viewModel: AppointmentViewModel by activityViewModels { AppointmentViewModelFactory() }
    private val args: PaymentFragmentArgs by navArgs()
    private var selectedPaymentType = -1
    private var selectedCardType = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPaymentBinding.bind(view)

        val spinner1 = resources.getStringArray(R.array.Spinner1)
        val spinner2 = resources.getStringArray(R.array.Spinner2)
        val adapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinner1)
        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinner2)


        with(binding){
            toolbar.setNavigationOnClickListener {
                val action = PaymentFragmentDirections.ToMatchingFragment()
                Navigation.findNavController(view).navigate(action)
            }

            payGroup.setOnCheckedChangeListener { group, checkedId ->
                when(checkedId){
                    R.id.btn_easy_payment -> {
                        selectedPaymentType = 1
                    }
                    R.id.btn_common_payment -> {
                        selectedPaymentType = 2
                    }
                }
            }

            spinnerPaymentCardType.adapter = adapter1
            spinnerPaymentCardPeriod.adapter = adapter2

            totalPrice.text = totalPrice.moneyFormat(viewModel.reserveSchedule.value.totalPrice)

            btnCredit.setOnClickListener{
                selectedCardType = 1
                btnCredit.backgroundTintList = requireContext().setColorPrimary()
                btnCredit.setTextColor(requireContext().setColorWhite())

                btnPayco.backgroundTintList = requireContext().setColorWhite()
                btnPayco.setTextColor(requireContext().setColorPrimary())
                btnNaverPay.backgroundTintList = requireContext().setColorWhite()
                btnNaverPay.setTextColor(requireContext().setColorPrimary())
                btnKakaoPay.backgroundTintList = requireContext().setColorWhite()
                btnKakaoPay.setTextColor(requireContext().setColorPrimary())
            }

            btnPayco.setOnClickListener{
                selectedCardType = 1
                btnPayco.backgroundTintList = requireContext().setColorPrimary()
                btnPayco.setTextColor(requireContext().setColorWhite())

                btnCredit.backgroundTintList = requireContext().setColorWhite()
                btnCredit.setTextColor(requireContext().setColorPrimary())
                btnNaverPay.backgroundTintList = requireContext().setColorWhite()
                btnNaverPay.setTextColor(requireContext().setColorPrimary())
                btnKakaoPay.backgroundTintList = requireContext().setColorWhite()
                btnKakaoPay.setTextColor(requireContext().setColorPrimary())
            }

            btnKakaoPay.setOnClickListener{
                selectedCardType = 1
                btnKakaoPay.backgroundTintList = requireContext().setColorPrimary()
                btnKakaoPay.setTextColor(requireContext().setColorWhite())

                btnCredit.backgroundTintList = requireContext().setColorWhite()
                btnCredit.setTextColor(requireContext().setColorPrimary())
                btnNaverPay.backgroundTintList = requireContext().setColorWhite()
                btnNaverPay.setTextColor(requireContext().setColorPrimary())
                btnPayco.backgroundTintList = requireContext().setColorWhite()
                btnPayco.setTextColor(requireContext().setColorPrimary())
            }

            btnNaverPay.setOnClickListener{
                selectedCardType = 1
                btnNaverPay.backgroundTintList = requireContext().setColorPrimary()
                btnNaverPay.setTextColor(requireContext().setColorWhite())

                btnCredit.backgroundTintList = requireContext().setColorWhite()
                btnCredit.setTextColor(requireContext().setColorPrimary())
                btnNaverPay.backgroundTintList = requireContext().setColorWhite()
                btnNaverPay.setTextColor(requireContext().setColorPrimary())
                btnKakaoPay.backgroundTintList = requireContext().setColorWhite()
                btnKakaoPay.setTextColor(requireContext().setColorPrimary())
            }

            btnDone.setOnClickListener{
                if (selectedPaymentType < 0){
                    childFragmentManager.showDialog("결제 방식을 선택해 볼까요?", "결제 방식을 선택해 주세요 !")
                }else if (selectedPaymentType == 2){
                    if (selectedCardType != 1){
                        childFragmentManager.showDialog("결제 방식을 선택해 볼까요?", "결제 방식을 선택해 주세요 !")
                    }else{
                        viewModel.requestService(args.idx)
                        moveFragment(view)
                    }
                } else{
                    viewModel.requestService(args.idx)
                    moveFragment(view)
                }
            }
        }
    }

    private fun moveFragment(view: View){
        val action = PaymentFragmentDirections.toReservationConfirmedFragment(args.idx)
        Navigation.findNavController(view).navigate(action)
    }
}



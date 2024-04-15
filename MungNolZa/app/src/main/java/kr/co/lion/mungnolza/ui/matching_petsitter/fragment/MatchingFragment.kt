package kr.co.lion.mungnolza.ui.matching_petsitter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kr.co.lion.mungnolza.databinding.FragmentMatchingBinding

class MatchingFragment : Fragment() {

    private var _binding: FragmentMatchingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMatchingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View){
        with(binding){
            buttonMatchingDone.setOnClickListener {
                val action = MatchingFragmentDirections.toPaymentFragment()
                Navigation.findNavController(view).navigate(action)
            }
        }
        initToolbar(view)
    }

    private fun initToolbar(view: View){
        with(binding.toolbar){
            setNavigationOnClickListener {
                val action = MatchingFragmentDirections.toAppointmentUserAddressFragment(null)
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
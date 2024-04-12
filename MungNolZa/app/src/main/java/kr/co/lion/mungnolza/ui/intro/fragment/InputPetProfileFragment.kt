package kr.co.lion.mungnolza.ui.intro.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentInputPetProfileBinding


class InputPetProfileFragment : Fragment() {
    private var _binding: FragmentInputPetProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputPetProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


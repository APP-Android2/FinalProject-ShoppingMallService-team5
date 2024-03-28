package kr.co.lion.mungnolza.ui.intro.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentRequestPermissionBinding
import kr.co.lion.mungnolza.ui.intro.StartActivity

class RequestPermissionFragment : Fragment() {
    private var _binding: FragmentRequestPermissionBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRequestPermissionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
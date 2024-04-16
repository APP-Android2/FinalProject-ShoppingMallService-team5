package kr.co.lion.mungnolza.ui.petsitter_main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetsitterFreeBoardBinding

class PetsitterFreeBoardFragment : Fragment() {

    lateinit var fragmentPetsitterFreeBoardBinding: FragmentPetsitterFreeBoardBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentPetsitterFreeBoardBinding = FragmentPetsitterFreeBoardBinding.inflate(inflater, container, false)

        return fragmentPetsitterFreeBoardBinding.root
    }
}
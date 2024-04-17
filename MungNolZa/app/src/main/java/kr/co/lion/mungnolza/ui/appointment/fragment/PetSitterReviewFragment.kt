package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetSitterReviewBinding
import kr.co.lion.mungnolza.databinding.RowPetSitterReviewBinding
import kr.co.lion.mungnolza.util.MatchingPetsitterFragmentName

class PetSitterReviewFragment : Fragment() {

    private var _binding : FragmentPetSitterReviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPetSitterReviewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(view)
    }

    private fun initView(){

    }

    private fun initToolbar(view: View){
        binding.toolbar.setNavigationOnClickListener{
            val action = PetSitterReviewFragmentDirections.toPetSitterInfoFragment(null)
            Navigation.findNavController(view).navigate(action)
        }

    }
}
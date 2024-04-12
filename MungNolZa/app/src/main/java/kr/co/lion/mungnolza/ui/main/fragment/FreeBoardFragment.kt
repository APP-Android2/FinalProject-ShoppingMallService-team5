package kr.co.lion.mungnolza.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.databinding.FragmentFreeBoardBinding
import kr.co.lion.mungnolza.ui.freeboard.BoardActivity
import kr.co.lion.mungnolza.ui.main.adapter.FreeBoardAdapter
import kr.co.lion.mungnolza.ui.main.viewmodel.MainViewModel
import kr.co.lion.mungnolza.ui.main.viewmodel.MainViewModelFactory

class FreeBoardFragment : Fragment() {

    private var _binding: FragmentFreeBoardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels { MainViewModelFactory() }
    private lateinit var freeBoardAdapter: FreeBoardAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFreeBoardBinding.inflate(inflater, container, false)
        initView()

        return binding.root
    }

    private fun initView() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.boardContentList.collect { boardList ->
                    freeBoardAdapter = FreeBoardAdapter(
                        dataSet = boardList,
                        onClick = { selectedItemIdx ->

                            val selectedItem = boardList.find { it.contentData.boardIdx == selectedItemIdx }
                            val selectedItemData = selectedItem?.contentData
                            val intent = Intent(requireContext(), BoardActivity::class.java)
                            intent.putExtra("content", selectedItemData)
                            startActivity(intent)
                        }
                    )
                    setRecyclerViewFreeBoard()
                }
            }
        }
    }

    private fun setRecyclerViewFreeBoard() {
        with(binding.recyclerViewFreeBoard) {
            adapter = freeBoardAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val deco = MaterialDividerItemDecoration(
                requireContext(),
                MaterialDividerItemDecoration.VERTICAL
            )
            addItemDecoration(deco)
        }
    }



}
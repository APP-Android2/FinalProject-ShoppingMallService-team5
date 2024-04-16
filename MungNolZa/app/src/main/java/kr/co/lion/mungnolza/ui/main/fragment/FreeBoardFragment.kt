package kr.co.lion.mungnolza.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentFreeBoardBinding
import kr.co.lion.mungnolza.ui.freeboard.AddBoardActivity
import kr.co.lion.mungnolza.ui.freeboard.BoardActivity
import kr.co.lion.mungnolza.ui.main.adapter.FreeBoardAdapter
import kr.co.lion.mungnolza.ui.main.vm.MainViewModel
import kr.co.lion.mungnolza.ui.main.vm.MainViewModelFactory

class FreeBoardFragment : Fragment(R.layout.fragment_free_board) {

    private var _binding: FragmentFreeBoardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels { MainViewModelFactory() }
<<<<<<< HEAD
    private lateinit var freeBoardAdapter: FreeBoardAdapter
=======

>>>>>>> 0e73214e0419402e5d3d00412ff2905432afe7a8
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFreeBoardBinding.bind(view)
        initView()
    }

    private fun initView() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.boardContentList.collect { boardList ->
                    val freeBoardAdapter = FreeBoardAdapter(
                        dataSet = boardList,
                        onClick = { selectedItemIdx ->
                            val selectedItem = boardList[selectedItemIdx]

                            val selectedItemData = selectedItem.contentData
                            val writerData = viewModel.findUserData(selectedItemData.boardWriterIdx)

                            val intent = Intent(requireContext(), BoardActivity::class.java)
<<<<<<< HEAD
                            intent.putExtra("boardData", selectedItemData)
                            intent.putExtra("userData", writerData)
=======
                            intent.putExtra("content", selectedItemData)
                            intent.putExtra("writer", writerData)
>>>>>>> 0e73214e0419402e5d3d00412ff2905432afe7a8

                            startActivity(intent)
                        }
                    )
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
        }

        binding.buttonAddBoardFreeBoard.setOnClickListener{
            val intent = Intent(requireContext(), AddBoardActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
<<<<<<< HEAD

=======
>>>>>>> 0e73214e0419402e5d3d00412ff2905432afe7a8
}
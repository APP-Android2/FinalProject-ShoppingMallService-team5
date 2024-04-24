package kr.co.lion.mungnolza.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentFreeBoardBinding
import kr.co.lion.mungnolza.ext.repeatOnViewStarted
import kr.co.lion.mungnolza.ui.freeboard.AddBoardActivity
import kr.co.lion.mungnolza.ui.freeboard.BoardActivity
import kr.co.lion.mungnolza.ui.main.adapter.FreeBoardAdapter
import kr.co.lion.mungnolza.ui.main.vm.MainViewModel
import kr.co.lion.mungnolza.ui.main.vm.MainViewModelFactory

class FreeBoardFragment : Fragment(R.layout.fragment_free_board) {

    private val viewModel: MainViewModel by activityViewModels { MainViewModelFactory(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFreeBoardBinding.bind(view)

        with(binding) {
            repeatOnViewStarted {
                viewModel.boardContentList.collect { boardList ->
                    val freeBoardAdapter = FreeBoardAdapter(
                        dataSet = boardList,
                        onClick = { selectedItemIdx ->
                            val selectedItem = boardList[selectedItemIdx]

                            val selectedItemData = selectedItem.contentData
                            val writerData = viewModel.findUserData(selectedItemData.boardWriterIdx)

                            val intent = Intent(requireContext(), BoardActivity::class.java)
                            intent.putExtra("boardData", selectedItemData)
                            intent.putExtra("userData", writerData)


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

            buttonAddBoardFreeBoard.setOnClickListener {
                val intent = Intent(requireContext(), AddBoardActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
package kr.co.lion.mungnolza.ui.petsitter_main.fragment

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
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetsitterFreeBoardBinding
import kr.co.lion.mungnolza.ui.freeboard.AddBoardActivity
import kr.co.lion.mungnolza.ui.freeboard.BoardActivity
import kr.co.lion.mungnolza.ui.main.adapter.FreeBoardAdapter
import kr.co.lion.mungnolza.ui.main.vm.MainViewModel
import kr.co.lion.mungnolza.ui.main.vm.MainViewModelFactory

class PetsitterFreeBoardFragment : Fragment() {

    lateinit var fragmentPetsitterFreeBoardBinding: FragmentPetsitterFreeBoardBinding

    lateinit var freeBoardAdapter: FreeBoardAdapter

    private val viewModel: MainViewModel by activityViewModels { MainViewModelFactory(requireContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentPetsitterFreeBoardBinding = FragmentPetsitterFreeBoardBinding.inflate(inflater, container, false)

        initView()

        return fragmentPetsitterFreeBoardBinding.root
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

                            intent.putExtra("boardData", selectedItemData)
                            intent.putExtra("userData", writerData)


                            startActivity(intent)
                        }
                    )
                    with(fragmentPetsitterFreeBoardBinding.recyclerViewFreeBoard) {
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

        fragmentPetsitterFreeBoardBinding.buttonAddBoardFreeBoard.setOnClickListener{
            val intent = Intent(requireContext(), AddBoardActivity::class.java)
            startActivity(intent)
        }

    }
}
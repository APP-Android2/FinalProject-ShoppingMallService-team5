package kr.co.lion.mungnolza.ui.freeboard.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.data.repository.BoardRepository
import kr.co.lion.mungnolza.model.BoardModel
import kr.co.lion.mungnolza.repository.user.UserRepository

class BoardViewModel(
    private val boardRepository:BoardRepository,
    private val userRepository: UserRepository
):ViewModel() {

    private var _boardList = MutableStateFlow<List<BoardModel>>(emptyList())
    val boardList = _boardList.asStateFlow()

    init{

    }

    private fun getBoardList():ArrayList<BoardModel> {
        var boardList = ArrayList<BoardModel>()
        viewModelScope.launch(Dispatchers.IO) {
            boardList = boardRepository.getBoardList()
        }
        return boardList
    }

}
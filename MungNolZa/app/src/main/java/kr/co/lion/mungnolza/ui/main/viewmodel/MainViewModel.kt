package kr.co.lion.mungnolza.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.model.BoardAddUerInfoModel
import kr.co.lion.mungnolza.model.UserModel
import kr.co.lion.mungnolza.repository.FreeBoardRepositoryImpl
import kr.co.lion.mungnolza.repository.UserRepositoryImpl
import java.net.URI

class MainViewModel(
    private val freeBoardRepository: FreeBoardRepositoryImpl,
    private val userRepository: UserRepositoryImpl
): ViewModel() {
    private val _userList = MutableStateFlow<List<UserModel>>(emptyList())
    val userList = _userList.asStateFlow()

    private var _boardContentList = MutableStateFlow<List<BoardAddUerInfoModel>>(emptyList())
    val boardContentList = _boardContentList.asStateFlow()

    init {
        fetchAllBoardDataWithUserInfo()
    }

    private fun fetchAllBoardDataWithUserInfo() = viewModelScope.launch(Dispatchers.IO) {
        val response = freeBoardRepository.fetchAllBoardData()
        val contentList = ArrayList<BoardAddUerInfoModel>()

        response.mapIndexed { index, boardModel ->
            val nickName = userRepository.fetchAllUserNickName(boardModel.boardWriterIdx)
            val imgUri = fetchBoardImg(boardModel.boardIdx.toString(), boardModel.boardImagePathList[0].toString())
            val content = imgUri?.let { uri -> BoardAddUerInfoModel(boardModel, nickName[index], uri) }
            if (content != null) {
                contentList.add(content)
            }
        }
        _boardContentList.value = contentList
    }

    private suspend fun fetchBoardImg(boardIdx: String, imgName: String): URI? {
        return freeBoardRepository.fetchAllBoardImage(boardIdx, imgName)
    }

    suspend fun fetchUserProfileImage(path: String): URI? {
        return userRepository.fetchUserProfileImage(path)
    }
}
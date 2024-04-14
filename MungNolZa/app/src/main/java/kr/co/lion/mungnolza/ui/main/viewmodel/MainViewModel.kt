package kr.co.lion.mungnolza.ui.main.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.model.BoardAddUerInfoModel
import kr.co.lion.mungnolza.model.UserModel
import kr.co.lion.mungnolza.repository.freeboard.FreeBoardRepositoryImpl
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl

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

        response.forEach {
            val nickName = userRepository.fetchAllUserNickName(it.boardWriterIdx)
            val imgUri = fetchBoardImg(it.boardIdx.toString(), it.boardImagePathList[0].toString())
            val content = imgUri?.let { uri -> BoardAddUerInfoModel(it, nickName, uri) }
            if (content != null) {
                contentList.add(content)
            }
        }
        _boardContentList.value = contentList
    }
    private suspend fun fetchBoardImg(boardIdx: String, imgName: String): Uri? {
        return freeBoardRepository.fetchAllBoardImage(boardIdx, imgName)
    }

    suspend fun fetchUserProfileImage(path: String): Uri {
        return userRepository.fetchUserProfileImage(path)
    }
}
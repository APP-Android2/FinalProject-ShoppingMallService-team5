package kr.co.lion.mungnolza.ui.main.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.datasource.MainDataStore
import kr.co.lion.mungnolza.model.BoardAddUerInfoModel
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.model.UserModel
import kr.co.lion.mungnolza.repository.freeboard.FreeBoardRepositoryImpl
import kr.co.lion.mungnolza.repository.pet.PetRepositoryImpl
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl
import java.net.URI

class MainViewModel(
    private val freeBoardRepository: FreeBoardRepositoryImpl,
    private val userRepository: UserRepositoryImpl,
    private val petRepositoryImpl: PetRepositoryImpl
): ViewModel() {
    private val _userList = MutableStateFlow<List<UserModel>>(emptyList())
    val userList = _userList.asStateFlow()

    private var _boardContentList = MutableStateFlow<List<BoardAddUerInfoModel>>(emptyList())
    val boardContentList = _boardContentList.asStateFlow()

    private val _myUserNumber = MutableStateFlow<String?>(null)
    private val myUserNumber = _myUserNumber.asStateFlow()

    private val _myPetData = MutableStateFlow<List<PetImgModel>>(emptyList())
    val myPetData = _myPetData.asStateFlow()

    init {
        viewModelScope.launch {
            //MainDataStore.setUserNumber("1234")
            MainDataStore.getUserNumber().collect {
                _myUserNumber.value = it
            }
        }
        fetchAllBoardDataWithUserInfo()
        fetchMyAllPetData()
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

    private fun fetchMyAllPetData() = viewModelScope.launch {
        val petList = ArrayList<PetImgModel>()

        myUserNumber.collect { myUserNumber ->
            val result = myUserNumber?.let { data-> petRepositoryImpl.fetchMyPetData(data) }
            result?.map{
                val imgUri = fetchPetImg(it.ownerIdx, it.imgName)
                val pet = imgUri?.let { data -> PetImgModel(it, data) }

                if (pet != null) {
                    petList.add(pet)
                }
            }
            _myPetData.value = petList
        }
    }

    private suspend fun fetchPetImg(userIdx: String, petName: String): URI? {
        return petRepositoryImpl.fetchMyPetImage(userIdx, petName)
    }
    private suspend fun fetchBoardImg(boardIdx: String, imgName: String): Uri? {
        return freeBoardRepository.fetchAllBoardImage(boardIdx, imgName)
    }

    suspend fun fetchUserProfileImage(path: String): Uri {
        return userRepository.fetchUserProfileImage(path)
    }
}
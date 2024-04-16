package kr.co.lion.mungnolza.ui.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.datasource.MainDataStore
import kr.co.lion.mungnolza.model.BoardAddUerInfoModel
import kr.co.lion.mungnolza.model.UserModel
import kr.co.lion.mungnolza.model.PetImgModel
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
            fetchAllUserData()
            fetchAllBoardDataWithUserInfo()

            //MainDataStore.setUserNumber("1234")
            MainDataStore.getUserNumber().collect {
                _myUserNumber.value = it
            }
        }

    }

    private suspend fun fetchAllUserData(){
        val response = userRepository.fetchAllUserData()
        _userList.value = response
    }

    private suspend fun fetchAllBoardDataWithUserInfo(){
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

    fun findUserData(writerIdx: String): UserModel? {
        return userList.value.find { it.uniqueNumber == writerIdx }
    }

    fun setMyPetData(myPets: List<PetImgModel>){
        _myPetData.value = myPets
    }
}
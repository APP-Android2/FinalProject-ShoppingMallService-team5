package kr.co.lion.mungnolza.ui.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.datasource.MainDataStore
import kr.co.lion.mungnolza.model.BoardAddUerInfoModel
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.model.PetModel
import kr.co.lion.mungnolza.model.UserModel
import kr.co.lion.mungnolza.repository.freeboard.FreeBoardRepositoryImpl
import kr.co.lion.mungnolza.repository.pet.PetRepositoryImpl
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl

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
            fetchAllBoardDataWithUserInfo()

            readMyPetData()
            fetchAllUserData()

            //MainDataStore.setUserNumber("1234")
            MainDataStore.getUserNumber().collect {
                _myUserNumber.value = it
            }
        }
    }

    private suspend fun readMyPetData(){
        petRepositoryImpl.readMyPetData().stateIn(viewModelScope).collect{ data ->
            val result = data.map {
                PetImgModel(
                    PetModel(
                        ownerIdx = it.ownerIdx,
                        petName = it.petName,
                        petBreed = it.petBreed,
                        petGender = it.petGender,
                        petWeight = it.petWeight,
                        petAge = it.petAge,
                        isNeutering = it.isNeutering,
                        petSignificant = it.petSignificant,
                        imgName = it.imgPath
                    ),
                    petRepositoryImpl.fetchMyPetImage(it.ownerIdx, it.imgPath)
                )
            }
            _myPetData.value = result
        }
    }


    private suspend fun fetchAllUserData(){
        val response = userRepository.fetchAllUserData()
        _userList.value = response
    }

    private suspend fun fetchAllBoardDataWithUserInfo(){
        val response = freeBoardRepository.fetchAllBoardData()
        val contentList = mutableListOf<BoardAddUerInfoModel>()

        response.map {  boardModel ->
            val nickName = userRepository.fetchUserNickName(boardModel.boardWriterIdx)
            val imgUri = freeBoardRepository.fetchAllBoardImage(boardModel.boardIdx.toString(), boardModel.boardImagePathList[0].toString())
            val content = BoardAddUerInfoModel(boardModel, nickName, imgUri)
            contentList.add(content)
        }
        _boardContentList.value = contentList
    }

    fun findUserData(writerIdx: String): UserModel? {
        return userList.value.find { it.uniqueNumber == writerIdx }
    }

}
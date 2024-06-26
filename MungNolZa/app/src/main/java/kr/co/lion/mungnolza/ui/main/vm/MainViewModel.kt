package kr.co.lion.mungnolza.ui.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.datasource.MainDataStore
import kr.co.lion.mungnolza.model.BoardAddUerInfoModel
import kr.co.lion.mungnolza.model.PetImgModel
import kr.co.lion.mungnolza.model.PetModel
import kr.co.lion.mungnolza.model.ReviewAddUserInfoModel
import kr.co.lion.mungnolza.model.UserModel
import kr.co.lion.mungnolza.repository.freeboard.FreeBoardRepository
import kr.co.lion.mungnolza.repository.pet.PetRepository
import kr.co.lion.mungnolza.repository.review.ReviewRepository
import kr.co.lion.mungnolza.repository.user.UserRepository
import kr.co.lion.mungnolza.usecase.GetBoardDataWithUserInfoUseCase
import java.net.URI

class MainViewModel(
    private val freeBoardRepository: FreeBoardRepository,
    private val userRepository: UserRepository,
    private val petRepository: PetRepository,
    private val reviewRepository: ReviewRepository,
    private val getAllBoardDataWithUserInfo: GetBoardDataWithUserInfoUseCase,
): ViewModel() {

    private val _userList = MutableStateFlow<List<UserModel>>(emptyList())
    val userList = _userList.asStateFlow()

    private val _boardData = MutableStateFlow<List<BoardAddUerInfoModel>>(emptyList())
    val boardData: StateFlow<List<BoardAddUerInfoModel>> = _boardData.asStateFlow()


    private val _myUserNumber = MutableStateFlow<String?>(null)
    private val myUserNumber = _myUserNumber.asStateFlow()

    private val _myPetData = MutableStateFlow<List<PetImgModel>>(emptyList())
    val myPetData = _myPetData.asStateFlow()

    private val _review = MutableStateFlow<List<ReviewAddUserInfoModel>>(emptyList())
    val review = _review.asStateFlow()

    init {
        viewModelScope.launch {
            _boardData.value = getAllBoardDataWithUserInfo()

            fetchAllBoardDataWithUserInfo()
            fetchAllUserData()

            getAllUserReview()
            readMyPetData()

            //MainDataStore.setUserNumber("1234")
            MainDataStore.getUserNumber().collect {
                _myUserNumber.value = it
            }
        }
    }

    private suspend fun readMyPetData(){
        petRepository.readMyPetData().stateIn(viewModelScope).collect{ data ->
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
                    petRepository.fetchMyPetImage(it.ownerIdx, it.imgPath)
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
        _boardData.value = contentList
    }

    private suspend fun getAllUserReview(){
        val result = ArrayList<ReviewAddUserInfoModel>()
        val reviewData = reviewRepository.fetchAllReview()

        reviewData.map {
            val userProfileImg = findUserData(it.reviewWriterIdx)?.userProfileImgPath
            result.add(ReviewAddUserInfoModel(it, URI.create(userProfileImg)))
        }
        _review.value = result
    }

    fun findUserData(writerIdx: String): UserModel? {
        return userList.value.find { it.uniqueNumber == writerIdx }
    }

}
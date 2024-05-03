package kr.co.lion.mungnolza.usecase

import kr.co.lion.mungnolza.model.BoardAddUerInfoModel
import kr.co.lion.mungnolza.repository.freeboard.FreeBoardRepository
import kr.co.lion.mungnolza.repository.user.UserRepository

class GetBoardDataWithUserInfoUseCase(
    private val freeBoardRepository: FreeBoardRepository,
    private val userRepository: UserRepository,
){
    suspend operator fun invoke(): List<BoardAddUerInfoModel> {
        return freeBoardRepository.fetchAllBoardData().map { boardModel ->
            BoardAddUerInfoModel(
                contentData = boardModel,
                writerNickName = userRepository.fetchUserNickName(boardModel.boardWriterIdx),
                imgUri = freeBoardRepository.fetchAllBoardImage(
                boardIdx = boardModel.boardIdx.toString(),
                imgName = boardModel.boardImagePathList[0].toString()
            ))
        }
    }
}

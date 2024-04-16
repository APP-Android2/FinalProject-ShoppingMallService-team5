package kr.co.lion.mungnolza.repository

import android.net.Uri
import kr.co.lion.mungnolza.model.BoardModel

interface FreeBoardRepository {
    suspend fun fetchAllBoardData(): ArrayList<BoardModel>
    suspend fun fetchAllBoardImage(boardIdx: String, imgName: String): Uri?

    suspend fun insertBoardData(boardModel: BoardModel)

    suspend fun updateBoardData(boardModel:BoardModel, isRemoveImage:Boolean)
}
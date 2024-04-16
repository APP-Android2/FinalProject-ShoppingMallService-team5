package kr.co.lion.mungnolza.data.repository

import android.net.Uri
import kr.co.lion.mungnolza.model.BoardModel


interface BoardRepository {
    suspend fun getBoardList():ArrayList<BoardModel>

    suspend fun insertBoard(boardModel:BoardModel)

    suspend fun deleteBoard(boardModel: BoardModel)

    suspend fun getBoardImageListPath(boardModel: BoardModel):Uri?

    suspend fun updateBoard(boardModel:BoardModel, isRemoveImage:Boolean)

}
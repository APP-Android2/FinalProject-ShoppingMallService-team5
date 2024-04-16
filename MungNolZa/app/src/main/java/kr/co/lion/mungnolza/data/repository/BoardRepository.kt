package kr.co.lion.mungnolza.data.repository

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import kr.co.lion.mungnolza.model.BoardModel


interface BoardRepository {
    suspend fun getBoardList():ArrayList<BoardModel>

    suspend fun insertBoard(boardModel:BoardModel)

    suspend fun deleteBoard(boardModel: BoardModel)


    suspend fun updateBoard(boardModel:BoardModel, isRemoveImage:Boolean)

    suspend fun getBoardImageUri(boardIdx:Int,imageFilePath: String):Uri?

    suspend fun getBoardImageUriList(boardModel:BoardModel): MutableList<Uri?>
}
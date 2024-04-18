package kr.co.lion.mungnolza.data.repository

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import kr.co.lion.mungnolza.model.BoardModel
import java.net.URI


interface BoardRepository {

    suspend fun getBoardData(boardIdx:Int):BoardModel?
    suspend fun getBoardList():ArrayList<BoardModel>

    suspend fun insertBoardData(boardModel:BoardModel)

    suspend fun deleteBoardData(boardModel: BoardModel)


    suspend fun updateBoardData(boardModel:BoardModel, isRemoveImage:Boolean)

    suspend fun getBoardImageUri(boardIdx:Int,imageFilePath: String):Uri?


    suspend fun getBoardImageUriList(boardModel:BoardModel?): ArrayList<Uri?>
}
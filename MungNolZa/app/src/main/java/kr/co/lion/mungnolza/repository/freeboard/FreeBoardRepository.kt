package kr.co.lion.mungnolza.repository.freeboard

import android.net.Uri
import kr.co.lion.mungnolza.model.BoardModel

interface FreeBoardRepository {
    suspend fun fetchAllBoardData(): ArrayList<BoardModel>
    suspend fun fetchAllBoardImage(boardIdx: String, imgName: String): Uri?
}
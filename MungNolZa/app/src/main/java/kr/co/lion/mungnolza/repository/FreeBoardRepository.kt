package kr.co.lion.mungnolza.repository

import java.net.URI
import kr.co.lion.mungnolza.model.BoardModel

interface FreeBoardRepository {
    suspend fun fetchAllBoardData(): List<BoardModel>
    suspend fun fetchAllBoardImage(boardIdx: String, imgName: String): URI?
}
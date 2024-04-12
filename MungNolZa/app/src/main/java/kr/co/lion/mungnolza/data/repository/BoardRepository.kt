package kr.co.lion.mungnolza.data.repository

import kr.co.lion.mungnolza.model.BoardModel


interface BoardRepository {
    suspend fun getBoardData(boardIdx: Int): BoardModel?
}
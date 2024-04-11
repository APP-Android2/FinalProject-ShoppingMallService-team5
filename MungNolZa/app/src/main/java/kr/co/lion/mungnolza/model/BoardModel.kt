package kr.co.lion.mungnolza.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class BoardModel(
    var boardIdx: Int,
    var boardTitle: String,
    var boardContent: String,
    var boardImagePathList: MutableList<String?>,
    var boardWriterIdx: String,
    var boardWriteDate: String,
    var boardModifyDate: String,
    var boardLikeNumber: Int,
    var boardState: Int
){
    constructor() : this(
        boardIdx = 0,
        boardTitle = "",
        boardContent= "",
        boardImagePathList = mutableListOf(),
        boardWriterIdx = "",
        boardWriteDate = "",
        boardModifyDate = "",
        boardLikeNumber = 0,
        boardState = 0
    )
}
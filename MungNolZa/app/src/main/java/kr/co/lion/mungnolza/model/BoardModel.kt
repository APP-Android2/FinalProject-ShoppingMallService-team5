package kr.co.lion.mungnolza.model

import android.os.Parcelable

import kotlinx.parcelize.Parcelize

@Parcelize
data class BoardModel(
    val boardIdx: Int,
    val boardTitle: String,
    val boardContent: String,
    val boardImagePathList: MutableList<String?>,
    val boardWriterIdx: String,
    val boardWriteDate: String,
    val boardModifyDate: String,
    val boardLikeNumber: Int,
    val boardState: Int
): Parcelable{
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
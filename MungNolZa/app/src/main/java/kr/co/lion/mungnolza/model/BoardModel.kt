package kr.co.lion.mungnolza.model

data class BoardModel(
    var boardIdx: Int,
    var boardTitle: String,
    var boardContent: String,
    var boardImagePathList: MutableList<String?>,
    var boardWriterIdx: Int,
    var boardWriteDate: String,
    var boardModifyDate: String,
    var boardLikeNumber: Int,
    var boardState: Int

) {
    constructor() : this(
        boardIdx = 0,
        boardTitle = "",
        boardContent= "",
        boardImagePathList = mutableListOf(),
        boardWriterIdx = 0,
        boardWriteDate = "",
        boardModifyDate = "",
        boardLikeNumber = 0,
        boardState = 0
    )
}
package kr.co.lion.mungnolza.model

data class BoardModel(
    var boardIdx:Int,
    var boardTitle:String,
    var boardType:Int,
    var boardImageList:MutableList<String?>,
    var boardWriterIdx:Int,
    var boardWriteDate:String,
    var boardState:Int
){
    constructor():this(0, "", 0, mutableListOf(), 0, "", 0)
}
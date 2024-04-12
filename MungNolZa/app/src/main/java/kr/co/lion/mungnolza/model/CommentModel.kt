package kr.co.lion.mungnolza.model

data class CommentModel(
    var commentIdx:Int,
    var commentContent:String,
    var commentDate:String,
    var boardIdx:Int,
    var writerIdx:Int,
    var commentState:Int
)

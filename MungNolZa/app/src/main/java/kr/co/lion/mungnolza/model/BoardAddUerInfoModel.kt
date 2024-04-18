package kr.co.lion.mungnolza.model

import java.net.URI

data class BoardAddUerInfoModel (
    val contentData: BoardModel,
    val writerNickName: String,
    val imgUri: URI
)
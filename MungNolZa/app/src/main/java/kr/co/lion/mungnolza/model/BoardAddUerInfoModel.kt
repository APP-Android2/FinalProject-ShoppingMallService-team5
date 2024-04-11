package kr.co.lion.mungnolza.model

import android.net.Uri

data class BoardAddUerInfoModel (
    val contentData: BoardModel,
    val writerNickName: String,
    val imgUri: Uri
)
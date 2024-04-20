package kr.co.lion.mungnolza.model

import android.os.Parcelable

import kotlinx.parcelize.Parcelize

@Parcelize
data class BoardModel(
    val boardIdx: Int = 0,
    val boardTitle: String = "",
    val boardContent: String = "",
    val boardImagePathList: List<String?> = listOf(),
    val boardWriterIdx: String = "",
    val boardWriteDate: String = "",
    val boardModifyDate: String = "",
    val boardLikeNumber: Int = 0,
    val boardState: Int = 0
): Parcelable
package kr.co.lion.mungnolza.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class BoardAddUerInfoModel (
    val contentData: @RawValue BoardModel,
    val writerNickName: String,
    val imgUri: Uri
): Parcelable
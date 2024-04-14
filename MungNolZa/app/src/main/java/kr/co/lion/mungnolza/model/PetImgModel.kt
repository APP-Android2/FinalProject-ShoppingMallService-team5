package kr.co.lion.mungnolza.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.net.URI

@Parcelize
data class PetImgModel (
    val petInfo: @RawValue PetModel,
    val imgUrl: URI
): Parcelable
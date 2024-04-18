package kr.co.lion.mungnolza.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PetSitterModel(
    val petSitterIdx: String,
    val petSitterPhone: String,
    val petSitterName: String,
    val petSitterIntroduce: String,
    val petSitterAddress: String,
    val imgName: String,
    val petSitterCertificate: List<String>,
    val petSitterCareer: List<String>,
    val petCarePeriod: String,
): Parcelable {
    constructor(): this(
        "", "", "", "","", "", emptyList(), emptyList(), ""
    )
}
package kr.co.lion.mungnolza.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URI

@Entity(tableName = "pet_table")
data class MyPetEntity (
    @PrimaryKey
    val petName: String,
    val ownerIdx: String,
    val petBreed: String,
    val petGender: String,
    val petWeight: String,
    val petAge: String,
    val isNeutering: Boolean,
    val petSignificant: String,
    val imgPath: String
)
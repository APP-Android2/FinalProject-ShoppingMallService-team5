package kr.co.lion.mungnolza.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MyPetDao {

    @Query("SELECT * FROM pet_table")
    fun readMyPet(): Flow<List<MyPetEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMyPet(myPet: MyPetEntity)

    @Update
    suspend fun updateMyPet(myPet: MyPetEntity)

    @Query("DELETE FROM pet_table WHERE petName = :petName")
    suspend fun deleteMyPet(petName: String)

}
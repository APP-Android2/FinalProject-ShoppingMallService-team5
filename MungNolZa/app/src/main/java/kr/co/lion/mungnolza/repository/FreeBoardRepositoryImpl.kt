package kr.co.lion.mungnolza.repository

import java.net.URI
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await
import kr.co.lion.mungnolza.model.BoardModel
import kr.co.lion.mungnolza.util.ContentState

class FreeBoardRepositoryImpl : FreeBoardRepository {
    private val boardStore = Firebase.firestore.collection("Board")
    private val storage = Firebase.storage.reference
    override suspend fun fetchAllBoardData(): List<BoardModel> {
        return try{
            var query = boardStore.whereEqualTo("boardState", ContentState.CONTENT_STATE_NORMAL.number)
            query = query.orderBy("boardIdx", Query.Direction.DESCENDING)

            val querySnapshot = query.get().await()
            querySnapshot.map { it.toObject(BoardModel::class.java) }

        }catch (e: Exception) {
            Log.e("FirebaseResult", "Error fetching Board: ${e.message}")
            emptyList()
        }
    }

    override suspend fun fetchAllBoardImage(boardIdx: String, imgName: String): URI?{
        val path = "board/$boardIdx/$imgName"
        return try {
            val response = storage.child(path).downloadUrl.await().toString()
            URI.create(response)
        }catch (e: Exception){
            Log.e("FirebaseResult", "Error fetching BoardImage : ${storage.child(path)}")
            null
        }
    }
}
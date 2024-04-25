package kr.co.lion.mungnolza.repository.freeboard

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kr.co.lion.mungnolza.model.BoardModel
import kr.co.lion.mungnolza.util.ContentState
import java.net.URI

class FreeBoardRepositoryImpl(
    private val reference: CollectionReference,
    private val storage: StorageReference
) : FreeBoardRepository {

    override suspend fun fetchAllBoardData(): List<BoardModel> {
        return withContext(Dispatchers.IO) {
            try {
                var query = reference.whereEqualTo("boardState", ContentState.CONTENT_STATE_NORMAL.number)
                query = query.orderBy("boardIdx", Query.Direction.DESCENDING)
                val querySnapshot = query.get().await()
                querySnapshot.map { it.toObject(BoardModel::class.java) }

            } catch (e: Exception) {
                Log.e("FirebaseResult", "Error fetching Board: ${e.message}")
                emptyList()
            }
        }
    }

    override suspend fun fetchAllBoardImage(boardIdx: String, imgName: String): URI {
        return withContext(Dispatchers.IO) {
            try {
                val path = "board/$boardIdx/$imgName"
                val response = storage.child(path).downloadUrl.await().toString()
                URI.create(response)
            } catch (e: Exception) {
                Log.e("FirebaseResult", "Error fetching BoardImage : ${e.message}")
                URI.create("")
            }
        }
    }
}
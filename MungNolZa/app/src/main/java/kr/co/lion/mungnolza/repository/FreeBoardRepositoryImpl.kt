package kr.co.lion.mungnolza.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await
import kr.co.lion.mungnolza.model.BoardModel
import kr.co.lion.mungnolza.util.ContentState

class FreeBoardRepositoryImpl : FreeBoardRepository {
    private val boardStore = Firebase.firestore.collection("Board")
    private val storage = Firebase.storage.reference
    override suspend fun fetchAllBoardData(): ArrayList<BoardModel> {
        val boardList = ArrayList<BoardModel>()

        try{
            var query = boardStore.whereEqualTo("boardState", ContentState.CONTENT_STATE_NORMAL.number)
            query = query.orderBy("boardIdx", Query.Direction.DESCENDING)

            val querySnapshot = query.get().await()
            querySnapshot.forEach {
                val contentModel = it.toObject(BoardModel::class.java)
                boardList.add(contentModel)
            }

        }catch (e: Exception) {
            Log.e("FirebaseResult", "Error fetching Board: ${e.message}")
        }

        return boardList
    }

    override suspend fun fetchAllBoardImage(boardIdx: String, imgName: String): Uri?{
        var response: Uri? = null
        val path = "board/$boardIdx/$imgName"
        try {
            response = storage.child(path).downloadUrl.await()
        }catch (e: Exception){
            Log.e("FirebaseResult",
                "Error fetching BoardImage path : ${storage.child(path)}")
        }
        return response
    }
}
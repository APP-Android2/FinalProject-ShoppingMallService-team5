package kr.co.lion.mungnolza.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.mungnolza.model.BoardModel
import kr.co.lion.mungnolza.model.UserModel

class BoardRepositoryImpl : BoardRepository{

    override suspend fun getBoardData(boardIdx: Int): BoardModel? {
        var boardModel: BoardModel? = null

        val job = CoroutineScope(Dispatchers.IO).launch {
            val collectionReference = Firebase.firestore.collection("Board")
            val querySnapshot =
                collectionReference.whereEqualTo("boardIdx", boardIdx).get().await()

            if (querySnapshot.isEmpty == false) {
                boardModel = querySnapshot.documents[0].toObject(BoardModel::class.java)
            }
        }
        return boardModel
    }
}
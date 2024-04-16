package kr.co.lion.mungnolza.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.mungnolza.model.BoardModel
import kr.co.lion.mungnolza.model.UserModel
import kr.co.lion.mungnolza.util.ContentState

class BoardRepositoryImpl() : BoardRepository {

    private val boardStore = Firebase.firestore.collection("Board")
    private val storage = Firebase.storage.reference

    override suspend fun getBoardList(): ArrayList<BoardModel> {
        val boardList = arrayListOf<BoardModel>()

        try {
            var query =
                boardStore.whereEqualTo("boardState", ContentState.CONTENT_STATE_NORMAL.number)
            query = query.orderBy("boardIdx", Query.Direction.DESCENDING)
            val querySnapShot = query.get().await()

            querySnapShot.forEach {
                val boardModel = it.toObject(BoardModel::class.java)
                boardList.add(boardModel)
            }

        } catch (e: Exception) {
            Log.e("FirebaseResult", "Error Get Board Data: ${e.message}")
        }
        return boardList
    }

    override suspend fun insertBoard(boardModel: BoardModel) {
        boardStore.add(boardModel)
    }

    override suspend fun updateBoard(boardModel: BoardModel, isRemoveImage: Boolean) {
        try{
            val query = boardStore.whereEqualTo("boardIdx",boardModel.boardIdx).get().await()

            // 저장 데이터를 HashMap으로 만든다.
            val map = mutableMapOf<String, Any?>()
            map["boardTitle"] = boardModel.boardTitle
            map["boardContent"] = boardModel.boardContent

            if(boardModel.boardImagePathList.isNotEmpty()){
                map["boardImagePathList"] = boardModel.boardImagePathList
            }
            if(isRemoveImage == true){
                map["boardImagePathList"] = null
            }

            query.documents[0].reference.update(map)
        }catch (e: Exception) {
            Log.e("FirebaseResult", "Error Update Board Data: ${e.message}")
        }

    }

    override suspend fun deleteBoard(boardModel: BoardModel) {
        // 삭제가 아닌 BoardState 변경
    }

    override suspend fun getBoardImageUri(boardIdx:Int,imageFilePath: String):Uri?{
        var response:Uri? = null
        val path = "board/${boardIdx}/${imageFilePath}"
        try{
            response = storage.child(path).downloadUrl.await()
        }catch (e:Exception){
            Log.e("FirebaseResult",
                "Error Get BoardImageListPath : ${storage.child(path)}")
        }
        return response
    }

    override suspend fun getBoardImageUriList(boardModel:BoardModel): MutableList<Uri?> {
        var imageUriList:MutableList<Uri?> = mutableListOf()

        boardModel.boardImagePathList.forEach {
            imageUriList.add(getBoardImageUri(boardModel.boardIdx,it!!)!!)
        }
        return imageUriList
    }

}
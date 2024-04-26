package kr.co.lion.mungnolza.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.mungnolza.model.PetsitterReviewModel

class PetsitterReviewRepository {

    companion object{
        // 후기글 번호 시퀀스값을 가져온다.
        suspend fun getReviewIdx() : Int{

            var reviewIdxSequence = 0

            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다
                val collectionReference = Firebase.firestore.collection("petsitterReviewModel")
                // 후기글 번호 시퀀스값을 가지고 있는 문서에 접근할 수 있는 객체를 가져온다.
                val documentReference = collectionReference.document("reviewIdxSequence")
                // 문서내에 있는 데이터를 가져올 수 있는 객체를 가져온다.
                val documentSnapShot = documentReference.get().await()
                //reviewIdxSequence = documentSnapShot.getLong("petsitterIdx")?.toInt()!!
                reviewIdxSequence = documentSnapShot.getLong("reviewIdx")?.toInt() ?: 0

            }
            job1.join()

            return reviewIdxSequence
        }

        // 후기글번호 시퀀스값을 업데이트 한다.
        suspend fun updateReviewIdx(reviewIdxSequence:Int){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = Firebase.firestore.collection("petsitterReviewModel")
                // 후기글 번호 시퀀스값을 가지고 있는 문서에 접근할 수 있는 객체를 가져온다.
                val documentReference = collectionReference.document("reviewIdxSequence")
                // 저장할 데이터를 담을 HaskMap을 만들어준다.
                val map = mutableMapOf<String, Long>()
                map["reviewIdx"] = reviewIdxSequence.toLong()
                // 저장한다
                documentReference.set(map)
            }
            job1.join()
        }

        // 펫시터후기 정보를 저장한다.
        suspend fun insertPetsitterReviewData(petsitterReviewModel: PetsitterReviewModel){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = Firebase.firestore.collection("petsitterReviewModel")
                // 컬렉션에 문서를 추가한다.
                // 문서를 추가할 때 객체나 맵을 지정한다.
                // 추가된 문서 내부의 필드는 객체가 가진 프로퍼티의 이름이나 맵에 있는 데이터의 이름과 동일하게 결정된다.
                collectionReference.add(petsitterReviewModel)
            }
            job1.join()
        }
    }
}
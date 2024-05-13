package kr.co.lion.mungnolza.ui.appointment.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.model.ReviewAddUserInfoModel
import kr.co.lion.mungnolza.repository.petsitter.PetSitterRepository
import kr.co.lion.mungnolza.repository.review.ReviewRepository
import kr.co.lion.mungnolza.repository.review.ReviewRepositoryImpl
import kr.co.lion.mungnolza.repository.user.UserRepository
import kr.co.lion.mungnolza.repository.user.UserRepositoryImpl
import java.net.URI

class PetSitterInfoViewModel(
    private val userRepository: UserRepository,
    private val reviewRepository: ReviewRepository,
    private val petSitterRepository: PetSitterRepository,
): ViewModel() {

    private val _review = MutableStateFlow<List<ReviewAddUserInfoModel>>(emptyList())
    val review = _review.asStateFlow()

    fun onLoadingPetSitterPage(petSitterIdx: Int) = viewModelScope.launch{
        val result = ArrayList<ReviewAddUserInfoModel>()
        val response = reviewRepository.fetchPetSitterReview(petSitterIdx)

        response.map { review->
            val user = userRepository.fetchUserData(review.reviewWriterIdx.toString())
            user?.let {
                result.add(ReviewAddUserInfoModel(
                    review,
                    URI.create(user.userProfileImgPath)
                ))
            }
        }
        _review.value = result
    }


    suspend fun fetchPetSitterImage(petSitterIdx: String, imgName: String): URI {
        return petSitterRepository.fetchPetSitterImage(petSitterIdx, imgName)
    }
}
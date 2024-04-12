package kr.co.lion.mungnolza.ui.freeboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.data.repository.BoardRepository
import kr.co.lion.mungnolza.data.repository.UserRepository
import kr.co.lion.mungnolza.model.BoardModel
import kr.co.lion.mungnolza.model.UserModel
import javax.inject.Inject

class ShowDetailBoardViewModel:ViewModel() {



    fun getUserData():UserModel{
        return UserModel(1,"카리나","최나연","cny123@naver.com","010-2222-3333","송파에서 제일 비싼 집","","22","여")
    }

    fun getBoardData():BoardModel{
        return BoardModel(1,"카리나 어때?","나보다 별로지 않아?\nㅇㅅㅇ",
            mutableListOf(),1,"2024-04-12-10:30","2024-04-12-10:48",2,1)
    }


}
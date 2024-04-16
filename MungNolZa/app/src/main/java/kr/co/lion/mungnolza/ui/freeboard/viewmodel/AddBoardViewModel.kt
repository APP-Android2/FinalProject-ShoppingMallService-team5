package kr.co.lion.mungnolza.ui.freeboard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.lion.mungnolza.data.repository.BoardRepository
import kr.co.lion.mungnolza.repository.UserRepository

class AddBoardViewModel:ViewModel() {

    val editTextTitleAddBoard = MutableLiveData<String>()
    val editTextContentAddBoard = MutableLiveData<String>()


}
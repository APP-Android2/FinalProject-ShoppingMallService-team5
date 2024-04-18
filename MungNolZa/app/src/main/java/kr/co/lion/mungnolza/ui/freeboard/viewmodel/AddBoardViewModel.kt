package kr.co.lion.mungnolza.ui.freeboard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddBoardViewModel:ViewModel() {

    val editTextTitleAddBoard = MutableLiveData<String>()
    val editTextContentAddBoard = MutableLiveData<String>()


}
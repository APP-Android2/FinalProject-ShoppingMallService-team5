package kr.co.lion.mungnolza.ui.intro.vm

import androidx.lifecycle.ViewModel
import kr.co.lion.mungnolza.datasource.MainDataStore

class StartViewModel: ViewModel() {
    suspend fun checkFistFlag(): Boolean{
        return MainDataStore.getFirstFlag()
    }
}
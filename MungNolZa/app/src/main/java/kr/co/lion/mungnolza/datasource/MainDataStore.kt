package kr.co.lion.mungnolza.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.co.lion.mungnolza.util.App

<<<<<<< HEAD

=======
>>>>>>> 0e73214e0419402e5d3d00412ff2905432afe7a8
object MainDataStore {
    private fun getContext(): Context = App.context()

    private val mDataStore: DataStore<Preferences>
        get() = getContext().dataStore

    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("user_pref")
    private val FIRST_FLAG = booleanPreferencesKey("FIRST_FLAG")
    private val USER_NUMBER = stringPreferencesKey("USER_NUMBER")

    suspend fun setupFirstData(){
        mDataStore.edit { pref ->
            pref[FIRST_FLAG] = true
        }
    }

    suspend fun getFirstFlag(): Boolean{
        var currentValue = false

        mDataStore.edit { pref->
            currentValue = pref[FIRST_FLAG] ?: false
        }

        return currentValue
    }

    suspend fun setUserNumber(uniqueNumber: String){
        mDataStore.edit { pref->
            pref[USER_NUMBER] = uniqueNumber
        }
    }

    fun getUserNumber(): Flow<String> {
        return mDataStore.data.map { preferences ->
            preferences[USER_NUMBER] ?: ""
        }
    }
}
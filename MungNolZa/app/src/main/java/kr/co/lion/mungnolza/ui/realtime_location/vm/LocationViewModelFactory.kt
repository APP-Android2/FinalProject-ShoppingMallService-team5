package kr.co.lion.mungnolza.ui.realtime_location.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.mungnolza.repository.location.LocationRepositoryImpl
import java.lang.IllegalArgumentException

class LocationViewModelFactory: ViewModelProvider.Factory {
    private val locationRepository = LocationRepositoryImpl()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)){
            return LocationViewModel(locationRepository) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}
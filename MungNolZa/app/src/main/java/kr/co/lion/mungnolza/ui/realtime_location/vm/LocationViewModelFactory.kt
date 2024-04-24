package kr.co.lion.mungnolza.ui.realtime_location.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kr.co.lion.mungnolza.repository.location.LocationRepositoryImpl
import java.lang.IllegalArgumentException

class LocationViewModelFactory: ViewModelProvider.Factory {
    private val locationRepository = LocationRepositoryImpl(
        Firebase.database.getReference("Location")
    )
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)){
            return LocationViewModel(locationRepository) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}
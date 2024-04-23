package kr.co.lion.mungnolza.ui.realtime_location.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.model.Location
import kr.co.lion.mungnolza.repository.location.LocationRepositoryImpl

class LocationViewModel(
    private val locationRepository: LocationRepositoryImpl
): ViewModel() {

    private val _currentLocation = MutableSharedFlow<List<Location>>()
    val currentLocation get() = _currentLocation.asSharedFlow()

    fun getCurrentLocation(reservationIdx: String) = viewModelScope.launch {
        val location = locationRepository.readCurrentLocation(reservationIdx)
        _currentLocation.emit(location)
    }

}
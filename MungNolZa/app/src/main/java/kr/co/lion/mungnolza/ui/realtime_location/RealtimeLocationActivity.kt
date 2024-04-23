package kr.co.lion.mungnolza.ui.realtime_location

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.overlay.PathOverlay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityRealtimeLocationBinding
import kr.co.lion.mungnolza.ui.realtime_location.vm.LocationViewModel
import kr.co.lion.mungnolza.ui.realtime_location.vm.LocationViewModelFactory

class RealtimeLocationActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityRealtimeLocationBinding
    private val viewModel: LocationViewModel by viewModels{ LocationViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRealtimeLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCurrentLocation("1")


        val sheetBehavior = BottomSheetBehavior.from(findViewById(R.id.chat_bottomSheet))
        with(sheetBehavior) {
            isHideable = true
            state = BottomSheetBehavior.STATE_HIDDEN
        }

        binding.btnChat.setOnClickListener {
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        }


        // 네이버맵 동적으로 불러오기
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(naverMap: NaverMap) {

        val coords = mutableListOf<LatLng>()

        val pathOverlay = PathOverlay()
        pathOverlay.width = resources.getDimensionPixelSize(R.dimen.path_width)
        pathOverlay.color = ContextCompat.getColor(this, R.color.app_main_color)
        pathOverlay.outlineColor = ContextCompat.getColor(this, R.color.app_main_color)

        with(naverMap.locationOverlay) {
            isVisible = true
            icon = OverlayImage.fromResource(R.drawable.dogface)
            iconWidth = 80
            iconHeight = 80

            lifecycleScope.launch {
                viewModel.currentLocation.collect {
                    it.mapIndexed { idx, location ->
                        if (idx != 0){ delay(1000) }

                        if (idx > 1){
                            pathOverlay.coords = coords
                            pathOverlay.map = naverMap
                        }

                        val currentPosition = LatLng(location.latitude.toDouble(), location.longitude.toDouble())
                        position = currentPosition

                        naverMap.moveCamera(CameraUpdate.scrollTo(currentPosition))
                        coords.add(currentPosition)

                    }
                }
            }
        }

        // 건물 내부 표시
        naverMap.isIndoorEnabled = true

        with(naverMap.uiSettings){
            // 줌버튼
            isZoomControlEnabled = true

            // 실내지도 층 피커
            isIndoorLevelPickerEnabled = true

            // 축적바
            isScaleBarEnabled = true
        }
    }
}


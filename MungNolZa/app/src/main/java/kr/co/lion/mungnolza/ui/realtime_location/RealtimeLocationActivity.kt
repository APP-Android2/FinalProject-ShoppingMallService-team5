package kr.co.lion.mungnolza.ui.realtime_location

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityRealtimeLocationBinding
import kr.co.lion.mungnolza.databinding.RowRealtimeLocationDetailChatBinding
import kr.co.lion.mungnolza.ui.chat.fragment.DetailChatFragment
import kr.co.lion.mungnolza.ui.realtime_location.fragment.RealtimeChattingFragment

class RealtimeLocationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRealtimeLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRealtimeLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
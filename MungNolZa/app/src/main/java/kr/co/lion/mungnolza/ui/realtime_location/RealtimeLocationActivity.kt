package kr.co.lion.mungnolza.ui.realtime_location

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityRealtimeLocationBinding
import kr.co.lion.mungnolza.databinding.RowRealtimeLocationDetailChatBinding
import kr.co.lion.mungnolza.ui.chat.fragment.DetailChatFragment

class RealtimeLocationActivity : AppCompatActivity() {

    lateinit var activityRealtimeLocationBinding: ActivityRealtimeLocationBinding

    // 위치 정보를 관리하는 객체
    lateinit var locationManager: LocationManager
    // 위치 측정이 성공하면 동작할 리스너
    var gpsLocationListener: MyLocationListener? = null
    var networkLocationListener: MyLocationListener? = null

    // 구글 지도 객체를 담을 프로퍼티
    lateinit var mainGoogleMap: GoogleMap

    // 현재 사용자 위치를 표시하기 위한 마커
    var myMarker: Marker? = null

    // 확인할 권한 목록
    val permissionList = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapsInitializer.initialize(this, MapsInitializer.Renderer.LATEST, null)

        activityRealtimeLocationBinding = ActivityRealtimeLocationBinding.inflate(layoutInflater)
        setContentView(activityRealtimeLocationBinding.root)

        // 권한을 확인 받는다.
        requestPermissions(permissionList, 0)

        setToolbar()
        settingGoogleMap()
        setRecyclerView()
    }

    // 툴바 설정
    fun setToolbar(){
        activityRealtimeLocationBinding.apply {
            toolbarRealtimeLocation.apply {
                // 메뉴아이템
                inflateMenu(R.menu.menu_realtime_location_activivty)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menuitem_realtime_location_notification -> {
                            // 알림화면으로 이동한다.
                        }
                    }

                    true
                }
            }
        }
    }

    // 구글 지도 셋팅
    fun settingGoogleMap(){
        // MapFragment 객체를 가져온다.
        val supportMapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        // 리스너를 설정한다.
        // 구글 지도 사용이 완료되면 동작한다.
        supportMapFragment.getMapAsync {
            // Snackbar.make(activityMainBinding.root, "구글 지도가 나타났습니다", Snackbar.LENGTH_SHORT).show()

            // 구글 지도 객체 객체를 담아준다.
            mainGoogleMap = it

            // 확대 축소 버튼
            mainGoogleMap.uiSettings.isZoomControlsEnabled = true

            // 현재 위치로 이동시키는 버튼을 둔다.
            // 사용자 현재 위치도 표시된다.
            mainGoogleMap.isMyLocationEnabled = true

            // isMyLocationEnabled 에 true를 넣어주면 현재 위치를 표시하는 것 뿐만 아니라
            // 현재 위치로 이동하는 버튼도 표시된다.
            // 이 버튼을 제거하겠다면...
            // mainGoogleMap.uiSettings.isMyLocationButtonEnabled = false

            // 위치 정보를 관리하는 객체를 가지고 온다.
            locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            // 위치 정보 수집에 성공하면 동작할 리스너
            // myLocationListener = MyLocationListener()

            // 단말기에 저장되어 있는 위치 값을 가져온다.
            // 위치정보 사용 권한 허용 여부 확인
            val a1 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            val a2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

            // 모든 권한이 허용되어 있다면
            if(a1 && a2){
                // 저장되어 있는 위치값을 가져온다.(없으면 null이 반환된다)
                val location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                val location2 = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                // 현재 위치를 지도에 표시한다.
                if(location1 != null){
                    setMyLocation(location1)
                } else if(location2 != null){
                    setMyLocation(location2)
                }

                // 현재 위치를 측정한다.
                getMyLocation()
            }
        }
    }


    // 현재 위치를 가져오는 메서드
    fun getMyLocation(){
        // 위치정보 사용 권한 허용 여부 확인
        val a1 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
        val a2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED

        if(a1 || a2){
            return
        }

        // GPS 프로바이더 사용이 가능하다면
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true){
            // 첫 번째 : 사용할 프로바이더
            // 두 번째 : 리스너의 메서드가 호출 될 시간 주기(ms). 0을 넣어주면 최대한 짧 은 시간 주기
            // 세 번째 : 리스너의 메서드가 호출 될 거리 주기(m). 0을 넣어주면 최대한 짧 은 거리 주기
            if(gpsLocationListener == null) {
                gpsLocationListener = MyLocationListener()
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                    0.0f, gpsLocationListener!!)
            }
        }

        // Network 프로바이더 사용이 가능하다면
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ==
            true){
            if(networkLocationListener == null) {
                networkLocationListener = MyLocationListener()

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0.0f,
                    networkLocationListener!!)
            }
        }
    }



    // 지도의 위치를 설정하는 메서드
    fun setMyLocation(location:Location){
        // 위도와 경도를 출력한다.
        Snackbar.make(activityRealtimeLocationBinding.root, "provider : ${location.provider}, 위도 :${location.latitude}, 경도 : ${location.longitude}",
        Snackbar.LENGTH_SHORT).show()

        // 위도와 경도를 관리하는 객체를 생성한다.
        val userLocation = LatLng(location.latitude, location.longitude)

        // 지도를 이동시키기 위한 객체를 생성한다.
        // 첫 번째 : 표시할 지도의 위치(위도 경고)
        // 두 번째 : 줌 배율
        //val cameraUpdate = CameraUpdateFactory.newLatLngZoom(userLocation, 20.0f)

        val cameraUpdate = CameraUpdateFactory.newLatLng(userLocation)

        // 카메라를 이동시킨다.
        // mainGoogleMap.moveCamera(cameraUpdate)
        mainGoogleMap.animateCamera(cameraUpdate)

        // 현재위치를 담은 마커를 생성한다.
        val markerOptions = MarkerOptions()
        markerOptions.position(userLocation)

        // 이미 마커가 있다면 제거한다.
        if(myMarker != null){
            myMarker?.remove()
            myMarker = null
        }

        // 마커의 이미지를 변경한다.
        val markerBitmap = BitmapDescriptorFactory.fromResource(R.drawable.dogface)
        markerOptions.icon(markerBitmap)

        // 마커를 지도에 표시해준다.
        myMarker = mainGoogleMap.addMarker(markerOptions)

    }

    // 위치 측정이 성공하면 동작하는 리스너
    inner class MyLocationListener() : LocationListener{
        // 위치가 측정되면 호출되는 메서드
        // location : 측정된 위치 정보가 담긴 객체
        override fun onLocationChanged(location: Location) {
            // 사용한 위치 정보 프로바이더로 분기한다.
            when(location.provider){
                // GPS 프로바이더 라면
                LocationManager.GPS_PROVIDER -> {
                    // GPS 리스너 연결을 해제해 준다.
                    locationManager.removeUpdates(gpsLocationListener!!)
                    gpsLocationListener = null
                }
                // NetworkProvider 라면
                LocationManager.NETWORK_PROVIDER -> {
                    // 네트워크 리스너 연결을 해제해 준다.
                    locationManager.removeUpdates(networkLocationListener!!)
                    networkLocationListener = null
                }
            }

            // 측정된 위치로 지도를 움직인다.
            setMyLocation(location)
        }
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        activityRealtimeLocationBinding.apply {
            recyclerViewRealtimeLocationChat.apply {
                // 어뎁터 설정
                adapter = RecyclerMainAdapter()
                // 레이아웃
                layoutManager = LinearLayoutManager(this@RealtimeLocationActivity)
            }
        }
    }

    // recyclerView의 어뎁터
    inner class RecyclerMainAdapter : RecyclerView.Adapter<RecyclerMainAdapter.RecyclerMainViewHolder>(){
        inner class RecyclerMainViewHolder(rowRealtimeLocationDetailChatBinding: RowRealtimeLocationDetailChatBinding) : RecyclerView.ViewHolder(rowRealtimeLocationDetailChatBinding.root){
            var rowRealtimeLocationDetailChatBinding: RowRealtimeLocationDetailChatBinding

            init {
                this.rowRealtimeLocationDetailChatBinding = rowRealtimeLocationDetailChatBinding

                this.rowRealtimeLocationDetailChatBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerMainViewHolder {
            val rowRealtimeLocationDetailChatBinding = RowRealtimeLocationDetailChatBinding.inflate(layoutInflater)
            val recyclerMainViewHolder = RecyclerMainViewHolder(rowRealtimeLocationDetailChatBinding)

            return recyclerMainViewHolder

        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: RecyclerMainViewHolder, position: Int) {
            holder.rowRealtimeLocationDetailChatBinding.editTextRowRealtimeLocationDetailChat.setText("펫시터님 왜 거기계세요?")
            holder.rowRealtimeLocationDetailChatBinding.textViewRowRealtimeLocationChatDate.text = "오전 10:40"
        }
    }
}
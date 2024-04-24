package kr.co.lion.mungnolza.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.databinding.ActivityTestBinding
import kr.co.lion.mungnolza.model.Location
import kr.co.lion.mungnolza.model.LocationRequestModel
import kr.co.lion.mungnolza.repository.location.LocationRepositoryImpl

class TestActivity : AppCompatActivity() {

    lateinit var binding:ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val locationRepositoryImpl = LocationRepositoryImpl(
            Firebase.database.getReference("Location")
        )
        

        lifecycleScope.launch {
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2833976", "127.0210229")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2819877", "127.0200183")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2826917", "127.018956")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2829394", "127.0182815")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2848141", "127.0187277")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2862496", "127.017945")))

            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2875289", "127.0180362")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2883993", "127.0174991")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2889193", "127.0163898")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2887167", "127.0134243")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.288227", "127.012556")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2871202", "127.0119379")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.28642", "127.0107111")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2859119", "127.0093633")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2850896","127.0081542")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2843503", "127.0084235")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2837471" ,"127.0088842")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2836908", "127.0089848")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2834822", "127.0092023")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.28307", "127.0100299")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2824233", "127.0107025")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.281439", "127.0124341")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2811598", "127.0130805 ")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2817108", "127.014852")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.281729", "127.0157003")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2819962", "127.01642")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2819014", "127.0170462")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2818809", "127.0177778")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2819185", "127.0186283")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2819481", "127.0193563")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2820593", "127.0200381")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2823353", "127.020477")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2828074", "127.0205559")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2830607", "127.0210272")))
            locationRepositoryImpl.insertLocation(LocationRequestModel("1","1","1", Location("37.2833976", "127.0210229")))

        }

    }
}
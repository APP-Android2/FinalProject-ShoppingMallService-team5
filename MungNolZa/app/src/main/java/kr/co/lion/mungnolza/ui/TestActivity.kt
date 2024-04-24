package kr.co.lion.mungnolza.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.databinding.ActivityTestBinding
import kr.co.lion.mungnolza.model.Location
import kr.co.lion.mungnolza.model.LocationRequestModel
import kr.co.lion.mungnolza.repository.location.LocationRepositoryImpl
import kr.co.lion.mungnolza.ui.chat.ChatActivity
import kr.co.lion.mungnolza.ui.freeboard.BoardActivity

class TestActivity : AppCompatActivity() {

    lateinit var binding:ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)

        binding.button.setOnClickListener {
            val intent = Intent(this,BoardActivity::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
        lifecycleScope.launch {
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2833976", "127.0210229")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2819877", "127.0200183")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2826917", "127.018956")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2829394", "127.0182815")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2848141", "127.0187277")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2862496", "127.017945")))

            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2875289", "127.0180362")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2883993", "127.0174991")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2889193", "127.0163898")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2887167", "127.0134243")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.288227", "127.012556")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2871202", "127.0119379")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.28642", "127.0107111")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2859119", "127.0093633")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2850896","127.0081542")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2843503", "127.0084235")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2837471" ,"127.0088842")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2836908", "127.0089848")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2834822", "127.0092023")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.28307", "127.0100299")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2824233", "127.0107025")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.281439", "127.0124341")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2811598", "127.0130805 ")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2817108", "127.014852")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.281729", "127.0157003")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2819962", "127.01642")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2819014", "127.0170462")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2818809", "127.0177778")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2819185", "127.0186283")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2819481", "127.0193563")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2820593", "127.0200381")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2823353", "127.020477")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2828074", "127.0205559")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2830607", "127.0210272")))
            LocationRepositoryImpl().insertLocation(LocationRequestModel("1","1","1", Location("37.2833976", "127.0210229")))

        }

    }
}
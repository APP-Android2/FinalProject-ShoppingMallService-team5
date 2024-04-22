package kr.co.lion.mungnolza.repository.location

import android.app.Application
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kr.co.lion.mungnolza.model.Location
import kr.co.lion.mungnolza.model.LocationRequestModel

class LocationRepositoryImpl : LocationRepository {
    private val db = Firebase.database
    private val locationRef = db.getReference("Location")
    override suspend fun insertLocation(locationRequest: LocationRequestModel) {
        locationRef.push().setValue(locationRequest)
    }
    override suspend fun readCurrentLocation(reservationIdx: String): Location {
        return withContext(Dispatchers.IO) {
            val query = locationRef.orderByChild("reservationIdx").equalTo(reservationIdx)

            val deferred = CompletableDeferred<Location>()

            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val location = dataSnapshot.children.map {
                        it.child("location").getValue(Location::class.java)
                    }[0]

                    location?.let { deferred.complete(location) }
                }

                override fun onCancelled(error: DatabaseError) {
                    deferred.completeExceptionally(error.toException())
                }
            })

            deferred.await()
        }
    }
}

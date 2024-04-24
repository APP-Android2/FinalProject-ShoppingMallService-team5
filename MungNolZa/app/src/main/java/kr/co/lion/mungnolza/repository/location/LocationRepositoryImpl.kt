package kr.co.lion.mungnolza.repository.location

import android.app.Application
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
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

class LocationRepositoryImpl(private val reference: DatabaseReference) : LocationRepository {

    override suspend fun insertLocation(locationRequest: LocationRequestModel) {
        withContext(Dispatchers.IO) {
            val locationKey = reference.push().key
            if (locationKey != null) {
                reference.child(locationKey).setValue(locationRequest)
            }
        }
    }

    override suspend fun readCurrentLocation(reservationIdx: String): List<Location> {
        return withContext(Dispatchers.IO) {
            val query = reference.orderByChild("reservationIdx").equalTo(reservationIdx)

            val deferred = CompletableDeferred<List<Location>>()

            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val locations = dataSnapshot.children.mapNotNull {
                        it.child("location").getValue(Location::class.java)
                    }

                    deferred.complete(locations)
                }

                override fun onCancelled(error: DatabaseError) {
                    deferred.completeExceptionally(error.toException())
                }
            })

            deferred.await()
        }
    }

}

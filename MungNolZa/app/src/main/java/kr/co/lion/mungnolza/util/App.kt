package kr.co.lion.mungnolza.util

import android.app.Application
import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class App : Application() {
    companion object {
        private lateinit var instance: App

        fun context(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}

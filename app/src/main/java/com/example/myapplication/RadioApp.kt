package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import androidx.core.app.ActivityCompat
import com.example.myapplication.data.AppContainer
import com.example.myapplication.data.DefaultAppContainer
import com.example.myapplication.util.Constants
import timber.log.Timber

class RadioApp : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        // Setup AppContainer
        container = DefaultAppContainer(this)

        // Set path for recordings
        Constants.RECORDINGS_PATH = this.getExternalFilesDir(Environment.DIRECTORY_MUSIC)?.absolutePath.toString()

        // Plant more trees
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        fun getAppContainer(context: Context): AppContainer {
            return (context.applicationContext as RadioApp).container
        }
    }
}

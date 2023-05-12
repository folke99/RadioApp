package com.example.myapplication

import android.app.Application
import android.content.Context
import com.example.myapplication.data.AppContainer
import com.example.myapplication.data.DefaultAppContainer
import timber.log.Timber

class RadioApp : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        // Setup AppContainer
        container = DefaultAppContainer(this)

        // Plant more trees
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        fun getAppContainer(context: Context): AppContainer {
            return (context.applicationContext as RadioApp).container
        }
    }
}

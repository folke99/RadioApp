package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mMediaPlayerIntent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* Get binding of the main activity*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* Bottom nav bar */
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_map, R.id.navigation_search, R.id.navigation_favorites))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        startMediaService("https://boxradio-edge-00.streamafrica.net/jpopchill")

        val pButton = findViewById<ImageButton>(R.id.playButton)

        pButton.setOnClickListener {
            changeMediaServiceStreamUrl("https://mangoradio.stream.laut.fm/mangoradio?t302=2023-04-18_23-11-18&uuid=5069bdcc-de20-4b7a-85a5-7c3771e9a88d")
        }

    /*
        val mediaPlayer = MediaPlayer()
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .build()

        mediaPlayer.setAudioAttributes(audioAttributes)
        mediaPlayer.setDataSource("https://boxradio-edge-00.streamafrica.net/jpopchill")

        try {
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (e: Exception) {
            Log.e("MainActivity", "Error playing radio station", e)
        }
        */

    }

    /**
     * Setup new media player service
     */
    private fun startMediaService(streamUrl: String) {
        // Setup a service to prepare a media player asynchronously and allow background play
        mMediaPlayerIntent = Intent(this, MediaPlayerService::class.java).apply {
            action = MediaPlayerService.ACTION_PLAY
            putExtra(MediaPlayerService.EXTRA_STREAM_URL, streamUrl)
        }
        startService(mMediaPlayerIntent)
    }

    /**
     * Change the media player stream url
     */
    private fun changeMediaServiceStreamUrl(streamUrl: String) {
        // Setup a service to prepare a media player asynchronously and allow background play
        val intent = Intent(this, MediaPlayerService::class.java).apply {
            action = MediaPlayerService.ACTION_CHANGE_STREAM_URL
            putExtra(MediaPlayerService.EXTRA_STREAM_URL, streamUrl)
        }
        startService(intent)
    }

    /**
     * Stop media player service
     */
    private fun stopMediaService() {
        mMediaPlayerIntent.action = MediaPlayerService.ACTION_STOP
        startService(mMediaPlayerIntent)
    }
}

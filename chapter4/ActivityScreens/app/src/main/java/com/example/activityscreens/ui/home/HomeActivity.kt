package com.example.activityscreens.ui.home

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.activityscreens.R
import com.example.activityscreens.databinding.ActivityHomeBinding
import com.example.activityscreens.utils.KeyboardHelper
import com.example.activityscreens.utils.SharedPreferenceHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.random.Random


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val CHANNEL_ID = "NotificationChannel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPreferenceHelper.initialize(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel
        homeViewModel.updateHeadingName("Chat")
        KeyboardHelper.setupKeyboardHiding(binding.root, this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_host) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvHome.setupWithNavController(navController)

        lifecycleScope.launch {
            navController.currentBackStackEntryFlow.collectLatest {
                val label = it.destination.label.toString().orEmpty()
                homeViewModel.updateHeadingName(label)
                when (it.destination.label.toString()) {
                    getString(R.string.label_call) -> homeViewModel.updateTrailingTopIcon(2)
                    getString(R.string.label_profile) -> homeViewModel.updateTrailingTopIcon(1)
                    getString(R.string.label_meeting) -> homeViewModel.updateTrailingTopIcon(null)
                    getString(R.string.label_fax) -> homeViewModel.updateTrailingTopIcon(0)
                }
            }
        }

        createNotificationChannel()

        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:${generateRandomNumber()}")

        val callPendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, callIntent, PendingIntent.FLAG_IMMUTABLE)

        val pendingIntent: PendingIntent = NavDeepLinkBuilder(this)
            .setComponentName(HomeActivity::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.callFragment)
            .createPendingIntent()

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.email)
            .setContentTitle("My notification")
            .setContentText(getString(R.string.channel_description))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setTimeoutAfter(5000L)
            .addAction(R.drawable.email, "Call", callPendingIntent)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            if (ActivityCompat.checkSelfPermission(
                    this@HomeActivity,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@HomeActivity,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    CALL_PERMISSION_REQUEST_CODE
                )
                return
            }

        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CALL_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, proceed with making the call
                    with(NotificationManagerCompat.from(this)) {
//                        notify(0, builder.build())
                    }

                } else {
                    // Permission denied, show a message or handle it accordingly
                    // For example, you can display a message to the user
                    Toast.makeText(
                        this,
                        "Call permission is required to make a call.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private val CALL_PERMISSION_REQUEST_CODE = 101

    private fun makeCall() {
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:${generateRandomNumber()}")
        startActivity(callIntent)
    }

    private fun generateRandomNumber(): Long {
        val random = Random(System.currentTimeMillis())
        return random.nextLong(1000000000L, 9999999999L)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

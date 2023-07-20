package com.example.firstandroidui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class PracticeActivity : AppCompatActivity() {

    private val txtHintEnabeled: TextInputEditText by lazy {
        findViewById(R.id.txtHintEnable)
    }
    private val btnImplicitIntent: Button by lazy {
        findViewById(R.id.btn_2)

    }

    private val msgGiverContract = MessageGiverContract()
    private val activityLauncher = registerForActivityResult(msgGiverContract) {

        it?.getStringExtra("morning")?.let { it1 -> Log.d("getted", it1) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)

        btnImplicitIntent.setOnClickListener {

            activityLauncher.launch("Helllo")
//            val sendIntent = Intent()
//            sendIntent.action = Intent.ACTION_SEND
//            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello, this is some shared text!")
//            sendIntent.type = "text/plain"
//            startActivity(sendIntent)
//            sendIntent.setClassName("com.example.firstandroidui", "com.example.firstandroidui.PracticeActivity")


        }

    }
}
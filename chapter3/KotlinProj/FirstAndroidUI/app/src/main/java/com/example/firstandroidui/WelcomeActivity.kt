package com.example.firstandroidui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText

class WelcomeActivity : AppCompatActivity() {
    private val tvEmail: EditText by lazy {
        findViewById(R.id.emailEditText)
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_welcome)

        val intent = Intent()
        intent.putExtra("morning","returned value")
        setResult(Activity.RESULT_OK,intent)
        finish()
//        val receivedIntent: Intent? = intent
//        val receivedData: Uri? = receivedIntent?.data
//
//        if (receivedData != null){
//            Log.d("Received",receivedData.toString())
//        }
//
//
//        val extras = intent.getBundleExtra("Enc Data")
//        val message = extras?.getString("msg")
//
//        tvEmail.setText(message)


    }
}
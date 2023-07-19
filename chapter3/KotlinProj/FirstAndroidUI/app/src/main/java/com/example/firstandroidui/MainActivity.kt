package com.example.firstandroidui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val trackJobButton: Button = findViewById(R.id.btn_track_job)
        val browseJobButton: Button = findViewById(R.id.btn_browse_job)

        trackJobButton.setOnClickListener {
            Toast.makeText(applicationContext, "Job has been tracked", Toast.LENGTH_SHORT).show()
        }

        browseJobButton.setOnClickListener {

        }

        browseJobButton.setOnClickListener {
            // create an instance of the snackbar
            val snackbar = Snackbar.make(it, "Browsed Job", Snackbar.LENGTH_LONG)
                .setAction("UNDO") {
                    val snackbar =
                        Snackbar.make(it, "Here is your result", Snackbar.LENGTH_LONG)
                    snackbar.show()
                }
            // call show() method to
            // display the snackbar
            snackbar.show()
        }

    }
}
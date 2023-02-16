package com.example.demofirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val imageButton= findViewById<ImageButton>(R.id.imageButton)
        val textView=findViewById<TextView>(R.id.textView)
//        imageButton.setOnClickListener(
//            Toast.makeText(this, "You clicked me.", Toast.LENGTH_SHORT).show()
//        )
        imageButton.setOnClickListener {
            // Do some work here
            textView.setText(getString(R.string.textfield))

        }
    }
}


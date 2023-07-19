package com.example.anotheractivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnClick=findViewById<Button>(R.id.button)
        val editText=findViewById<EditText>(R.id.editTextTextPersonName)

        btnClick.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)
            val name=editText.getText().toString()
            intent.putExtra("Myname",name)
            startActivity(intent)
        }

    }
}
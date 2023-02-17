package com.example.android.marsphotos.ui.overview

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.marsphotos.R
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request.Builder
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HttpActivity : AppCompatActivity() {
    var txtString: TextView? = null
    var url = "https://reqres.in/api/users/2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
        txtString = findViewById<View>(R.id.textView) as TextView
        try {
            run()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    fun run() {
        val client = OkHttpClient()
        val request: Request = Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                call.cancel()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val myResponse = response.body!!.string()
                runOnUiThread { txtString!!.text = myResponse }
            }
        })
    }
}
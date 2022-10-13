package com.prianshuprasad.targetcp

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

class launchActivity : AppCompatActivity() {
    private lateinit var handler:Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()



        setContentView(R.layout.activity_launch)
//
   handler= Handler();
        val serintent= Intent(this, notificationServices::class.java)

        startForegroundService(serintent)

        wait();
       

    }
    fun wait(){
        
        
          handler.postDelayed({
             val intent= Intent(this, MainActivity::class.java)

             startActivity(intent)
            finish()

         },900)

        
        
    }
    
    
    override fun onDestroy() {
        super.onDestroy()

        val serintent= Intent(this, notificationServices::class.java)

        startService(serintent)
        sendBroadcast(Intent("YouWillNeverKillMe"))
//        Toast.makeText(this, "YouWillNeverKillMe TOAST!!", Toast.LENGTH_LONG).show()

    }
}

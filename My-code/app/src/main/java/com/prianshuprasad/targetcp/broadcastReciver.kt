package com.prianshuprasad.targetcp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class StartAppOnBoot : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        // Notifies when a service is goin to start
        Toast.makeText(context,"Strating service",Toast.LENGTH_LONG).show()
 
        launchService()
         
    }
    
    fun launchService(){
        runonuithread {
            val serintent = Intent(context, notificationServices::class.java)

            context!!.startService(serintent)

        }
    }
    
}

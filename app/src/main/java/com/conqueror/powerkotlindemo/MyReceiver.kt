package com.conqueror.powerkotlindemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        var action = intent.action
        when (action) {
            Intent.ACTION_BOOT_COMPLETED -> {
                context.startService(Intent(context, MyService::class.java))
            }
        }

    }
}

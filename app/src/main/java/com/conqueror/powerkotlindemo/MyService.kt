package com.conqueror.powerkotlindemo

import android.app.Service
import android.content.Intent
import android.os.Environment
import android.os.IBinder
import android.util.Log
import java.io.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class MyService : Service() {
    val CAR_BATTERY_VOLTAGE = "/sys/conqueror_car_battery/car_battery_voltage"// 读取整个电频
    val TAG: String = MyService::class.java.simpleName

    private var threadPool: ScheduledExecutorService? = null
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "create service!!!")

        threadPool = Executors.newScheduledThreadPool(2)
        threadPool?.scheduleWithFixedDelay(Runnable {

            Log.d(TAG, "运行!!!")
            recorder()
        }, 0, 1, TimeUnit.MINUTES)
    }

    private fun recorder() {

        val path = Environment.getExternalStorageDirectory().path
        val filePath: String = path + "/PowerKotlin"
        val file = File(filePath)
        if (!file.exists()) {
            file.mkdirs()
        }
        val nowFile = file.path + "/test.txt"
        Log.e(TAG, "file paths : " + file.path)
        val reader = BufferedReader(FileReader(CAR_BATTERY_VOLTAGE))
        val writer = BufferedWriter(FileWriter(nowFile, true))
        var line: String
        Log.e(TAG, "reader : " + reader)
        while (true) {
            line = reader.readLine() ?: break
            Log.e(TAG, "lines content : " + line)
            Log.d(TAG, line)
            writer.write(line)
            writer.newLine()
            writer.flush()
        }

        reader.close()
        writer.close()

    }

    private fun record1() {
        val path = Environment.getExternalStorageDirectory().path
        val filePath: String = path + "/PowerKotlin"
        val file = File(filePath)
        if (!file.exists()) {
            file.mkdirs()
        }
        val nowFile = file.path + "/test.txt"
        Log.e(TAG, "file paths : " + file.path)
        val reader = InputStreamReader(FileInputStream(CAR_BATTERY_VOLTAGE))
        val writer = OutputStreamWriter(FileOutputStream(nowFile, true))
        var length : Int
        val byte :Char
        Log.e(TAG, "reader : " + reader)
        while (true) {
            writer.flush()
        }

        reader.close()
        writer.close()


    }


    override fun onDestroy() {
        super.onDestroy()
        threadPool?.shutdown()
        threadPool = null
        Log.d(TAG, "destroy service")
    }

}

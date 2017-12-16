package com.conqueror.powerkotlindemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv.setText("hello world !!!")
        initListener()
    }

    private fun initListener() {
//        bt_start.setOnClickListener(View.OnClickListener { startService(Intent(this, MyService::class.java)) })
//        bt_end?.setOnClickListener(View.OnClickListener { stopService(Intent(this, MyService::class.java)) })

        bt_start.setOnClickListener(this)
        bt_end.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            bt_start -> {
                startService(Intent(this, MyService::class.java))
                Toast.makeText(this, "开始", Toast.LENGTH_SHORT).show()
            }
            bt_end -> {
                stopService(Intent(this, MyService::class.java))
                Toast.makeText(this, "停止", Toast.LENGTH_SHORT).show()
            }
        }
    }


}

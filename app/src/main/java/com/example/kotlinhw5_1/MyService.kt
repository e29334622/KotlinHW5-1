package com.example.kotlinhw5_1

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {
    override fun onCreate() {
        super.onCreate()
        //使用Thread來執行耗時工作
        Thread(Runnable {
            try {
                //延遲5秒
                Thread.sleep(5000)
                //宣告從MyService啟動MainActivity2
                val intent = Intent(this@MyService, MainActivity2::class.java)
                //service要啟動Activity,則加入Flag定義去產生一個新的Activity
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                this@MyService.startActivity(intent)
            }catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }).start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        //結束後不再重啟
        return Service.START_NOT_STICKY
    }
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
package com.example.myaidl2

import android.content.ComponentName
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.example.myaidl.MyRemoteService
import android.content.Intent

import android.R.string.no
import android.content.Context
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    var myRomoteService:MyRemoteService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        findViewById<Button>(R.id.botton).setOnClickListener{
            val bookName = myRomoteService?.getBookName()
            findViewById<TextView>(R.id.tv_msg).setText(bookName)
        }
    }
    fun init(){
        val serviceConnection = object :ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
               myRomoteService = MyRemoteService.Stub.asInterface(service)
            }
            override fun onServiceDisconnected(name: ComponentName?) {
                TODO("Not yet implemented")
            }
        }
        // 通过Intent指定服务端服务名和所在包，绑定远程服务；
//action需要和远程服务的action一致
        // 通过Intent指定服务端服务名和所在包，绑定远程服务；
//action需要和远程服务的action一致
        val intent = Intent("com.example.myaidl.RemoteService")
//android5.0后无法使用隐式绑定远程服务，设置指定包名
//android5.0后无法使用隐式绑定远程服务，设置指定包名
        intent.setPackage("com.example.myaidl")
//绑定服务，需要传入intent和ServiceContection对象
//绑定服务，需要传入intent和ServiceContection对象
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
//只后就可以调用上面截图圈出的远程方法了


    }
}
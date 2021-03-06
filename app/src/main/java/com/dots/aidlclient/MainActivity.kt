package com.dots.aidlclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.dots.aidlserver.RemoteAidlInterface

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var serviceConnecion: RemoteServiceConnection?=null
    var service : RemoteAidlInterface?=null
            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        connectService()
        fab.setOnClickListener { view ->

            Snackbar.make(view, service?.getName("Vishnu")?:"not connected", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
//            service?.getName("Vishnu")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun connectService(){
        serviceConnecion = RemoteServiceConnection()
        var intent = Intent("com.dots.aidlserver.RemoteService")
        intent.setPackage("com.dots.aidlserver")
//        convertImp
        var ret = bindService(intent,serviceConnecion!!, Context.BIND_AUTO_CREATE)
    }

    inner class RemoteServiceConnection : ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {

            Toast.makeText(this@MainActivity, "not connected", Toast.LENGTH_LONG).show()
        }

        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            service =  RemoteAidlInterface.Stub.asInterface(binder)
            Toast.makeText(this@MainActivity, " connected", Toast.LENGTH_LONG).show()
        }
    }
}

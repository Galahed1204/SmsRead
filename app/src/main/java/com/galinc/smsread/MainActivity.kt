package com.galinc.smsread

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var pref: SharedPreferences
    private val APP_PREFERENCES = "mysettings"
    private val APP_PREFERENCES_SITE = "site"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        if (pref.contains(APP_PREFERENCES_SITE)) {
            siteApi.setText(pref.getString(APP_PREFERENCES_SITE, "5.8.207.81:18083"))
        }else{
            siteApi.setText("5.8.207.81:18083")
        }

        button_save_api.setOnClickListener {
            val editor = pref.edit()
            editor.putString(APP_PREFERENCES_SITE, siteApi.text.toString())
            editor.apply()
        }
        //siteApi.text =
        if (ContextCompat.checkSelfPermission(applicationContext,
                        Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requestPermissions(
                    arrayOf(Manifest.permission.RECEIVE_SMS), 3004)
        }
    }
}

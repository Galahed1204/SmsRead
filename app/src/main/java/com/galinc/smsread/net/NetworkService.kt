package com.galinc.smsread.net

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkService private constructor() {
    private val mRetrofit: Retrofit
    private lateinit var pref: SharedPreferences
    private val APP_PREFERENCES = "mysettings"
    private val APP_PREFERENCES_SITE = "site"
    lateinit var context:Context

    companion object {
        private var mInstance: NetworkService? = null
        private const val BASE_URL = "http://5.8.207.81:18083"
        val instance: NetworkService?
            get() {
                if (mInstance == null) {
                    mInstance = NetworkService()
                }
                return mInstance
            }
    }

    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

//        pref = getSharedPreferences(APP_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
    }


    fun getJSONApi(): JSONPlaceHolderApi? {
        return mRetrofit.create(JSONPlaceHolderApi::class.java)
    }
}
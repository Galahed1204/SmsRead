package com.galinc.smsread.net


import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST




interface JSONPlaceHolderApi {

    @POST("/gwex387bk-banksmshook")
    fun postData(@Body data: String?): Call<String>
}
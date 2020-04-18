package com.galinc.smsread

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log
import com.galinc.smsread.net.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SMSReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val call = NetworkService.instance
        Log.d("SMSReceiver", "onReceive")
        if (intent!!.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            val extras = intent.extras

            if (extras != null ){
                val sms = extras.get("pdus") as Array<Any>

                for (i in sms.indices){
                    val format = extras.getString("format")

                    val smsMessage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        SmsMessage.createFromPdu(sms[i] as ByteArray,format)
                    }else{
                        SmsMessage.createFromPdu(sms[i] as ByteArray)
                    }
                    val phoneNumber = smsMessage.originatingAddress
                    val messageText = smsMessage.messageBody.toString()

                    Log.d("SMSReceiver","phone number: $phoneNumber")
                    Log.d("SMSReceiver","message text: $messageText")

                    call!!.getJSONApi()!!.postData(messageText).enqueue(object:Callback<String>{
                        override fun onFailure(call: Call<String>, t: Throwable) {
                            Log.e("SMSReceiver","fail to connect $t")
                        }

                        override fun onResponse(call: Call<String>, response: Response<String>) {
                            Log.d("SMSReceiver","post sended")
                        }

                    })

                }
            }

            // Will do stuff with message here
        }

    }
}
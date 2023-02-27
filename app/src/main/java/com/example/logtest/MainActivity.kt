package com.example.logtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener

class MainActivity : AppCompatActivity() {

    val TAG = "AAA"
    val test = "https://play.google.com/store/apps/details?id=com.test.test_project&referrer=utm_source%3Dtest_source%26utm_medium%3Dtest_medium%26utm_term%3Dtest-term%26utm_content%3Dtest_content%26utm_campaign%3Dtest_name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val referrerClient = InstallReferrerClient.newBuilder(this).build()
        referrerClient.startConnection(object: InstallReferrerStateListener {

            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when (responseCode){
                    InstallReferrerClient.InstallReferrerResponse.OK -> {
                        Log.i(TAG, "Connection established.")
                        val response = referrerClient.installReferrer
                        val referrerURl = response.installReferrer
                        ReferrerParser.parse(referrerURl)
                        val result = ReferrerParser.map
                        Log.i(TAG, result.toString())
                    }
                    InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> {
                        Log.i(TAG, "API not available on the current Play Store app.")
                    }
                    InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> {
                        Log.i(TAG, "Connection couldn't be established.")
                    }
                }
            }

            override fun onInstallReferrerServiceDisconnected() {
                TODO("Not yet implemented")
            }
        })
    }
}
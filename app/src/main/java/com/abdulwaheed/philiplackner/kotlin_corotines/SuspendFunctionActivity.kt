package com.abdulwaheed.philiplackner.kotlin_corotines

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abdulwaheed.philiplackner.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SuspendFunctionActivity :  AppCompatActivity() {

    val TAG = "SuspendFunctionActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suspend_function)
        /*
        * suspend function can be executed by another suspend function or any coroutines. failing to do so cause compilation error.
        * we can write our own suspend function. if there are multiple suspend function calls and all of them have delay function,
        * since all of them are under same coroutine it will add up all suspend delay time and then execute the calls
        * */

        GlobalScope.launch {
            val networkCallResponse = doNetworkCall()
            val networkCallResponse2 = doNetworkCall2()
            val networkCallResponse3 = doNetworkCall3()

            Log.d(TAG, networkCallResponse)
            Log.d(TAG, networkCallResponse2)
            Log.d(TAG, networkCallResponse3)
        }

        GlobalScope.launch {
            val networkCallResponseSepareteCoroutine = doNetworkCallSepareteCoroutine()
            Log.d(TAG, networkCallResponseSepareteCoroutine)
        }
    }

    suspend fun doNetworkCallSepareteCoroutine(): String {
        delay(5_000)
        return "This is the response separate coroutine"
    }

    suspend fun doNetworkCall(): String {
        delay(5_000)
        return "This is the response1"
    }

    suspend fun doNetworkCall2(): String {
        delay(5_000L)
        return "This is the response2"
    }

    suspend fun doNetworkCall3(): String {
        delay(5_000L)
        return "This is the response3"
    }
}
package com.abdulwaheed.philiplackner.kotlin_corotines

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abdulwaheed.philiplackner.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineContextActivity :  AppCompatActivity() {

    val TAG = "CoroutineContextActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_coroutine_context)

        /*
        * in general coroutine are started in specific context and context will decide in which
        * thread coroutine will be started. GlobalScope does not give much control of it. Dispatcher
        * can change the context. this is useful when need to update UI with IO dispatched. newSingleThreadContext
        * is useful when you want to start a new thread with own name. withContext actually changes the context of current
        * coroutine
        * */
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, " strating coroutine in thread ${Thread.currentThread().name}")
            val answer = doNetworkCall()
            withContext(Dispatchers.Main) {
                Log.d(TAG, "Setting text in thread ${Thread.currentThread().name}")
                val textView = findViewById<TextView>(R.id.textView2)
                textView.text = answer
            }
        }

    }

    suspend fun doNetworkCall(): String {
        delay(5_000)
        return "This is the response1"
    }
}
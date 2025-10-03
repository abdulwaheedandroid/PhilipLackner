package com.abdulwaheed.philiplackner.kotlin_corotines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.abdulwaheed.philiplackner.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RunBlockingActivity :  AppCompatActivity() {

    val TAG = "RunBlockingActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_blocking)

        /*
        * as we know that delay function won't block thread in which it is running. however runBlocking blocks the
        * thread. runBlocking starts a new coroutine in the main thread. difference between GlobalScope Vs runBlocking
        * is that if you call delay function in GlobalScope it won't block the main thread but runBlocking will.
        * then why we need runBlocking if this blocks the main thread. this is because when we need to call any suspend function
        * we need a coroutine this is where we can use it. runBlocking will only block the Main Thread
        *  */

        Log.d(TAG, "before run blocking")
        runBlocking {
            launch(Dispatchers.IO) {
                delay(3000)
                Log.d(TAG, "finished IO Coroutine 1")

            }

            launch(Dispatchers.IO) {
                delay(3000)
                Log.d(TAG, "finished IO Coroutine 2")

            }
            Log.d(TAG, "starting run blocking")
            delay(5000)
            Log.d(TAG, "end of run blocking")

        }
        Log.d(TAG, "after run blocking")

    }
}
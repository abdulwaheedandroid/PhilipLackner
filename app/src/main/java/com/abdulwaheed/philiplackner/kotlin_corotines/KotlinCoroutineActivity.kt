package com.abdulwaheed.philiplackner.kotlin_corotines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.abdulwaheed.philiplackner.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class KotlinCoroutineActivity :  AppCompatActivity() {

    val TAG = "KotlinCoroutineActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_coroutine)

        /*this is very simplest way to start a coroutine. this is not really the best way.
        this means that this coroutine will as long as our app does. Every coroutine should be in a scope. in this case this is global.
        This code means this will launch our coroutine in a separate thread. Suspended function can be paused and resumed. Delay and Sleep
        are different because delay will only pause only current coroutine wile sleep pause complete thread. One important point to note is
        if the main thread has finished its work, this means all other thread and coroutines will be cancelled. even though they started on
        another new thread and asynchronously, they will be cancelled if the main thread finishes its work.
         */

        GlobalScope.launch {
            delay(3000)
            Log.d(TAG, "Coroutine says hello from thread ${Thread.currentThread().name}")
        }

        Log.d(TAG, "Coroutine says from thread ${Thread.currentThread().name}")

        /*
        * Above code represents that both lines of the code will be printed in difference thread.
        * */
    }
}
package com.abdulwaheed.philiplackner.kotlin_corotines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.abdulwaheed.philiplackner.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class AsyncAndAwaitActivity : AppCompatActivity() {

    val TAG = "AsyncAndAwaitActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_and_await)

        /*
        * if we have several suspend functions and execute them both in coroutine they are sequential by default.
        * this means first function will be executed first and once its finished then second function will be
        * executed. What if we want to call both of the function asyncronously. we can start a new coroutine to execute every function.
        * whenever we want to do something asyncronously and get a result out of that we should use async instead of launch
        * */

        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val answer1 = async {
                    networkCall()
                }

                val answer2 = async {
                    networkCall2()
                }
                Log.d(TAG, "Answer 1 is ${answer1.await()}")
                Log.d(TAG, "Answer 2 is ${answer2.await()}")

                //This is a very bad practice
              /*  var answer: String? = null
                var answer2: String? = null

                val job1 = launch { answer = networkCall() }
                val job2 =launch { answer2 = networkCall2() }
                job1.join()
                job2.join()
                Log.d(TAG, "answer 1 is $answer")
                Log.d(TAG, "answer 2 is $answer2")*/

                /*val answer = networkCall()
                val answer2 = networkCall2()
                Log.d(TAG, "answer 1 is $answer")
                Log.d(TAG, "answer 2 is $answer2")*/
            }
            Log.d(TAG, "Request took $time ms.")
        }
    }

    suspend fun networkCall(): String {
        delay(3000L)
        return "This is the result of network call"
    }

    suspend fun networkCall2(): String {
        delay(3000L)
        return "This is the result of network call2"
    }
}
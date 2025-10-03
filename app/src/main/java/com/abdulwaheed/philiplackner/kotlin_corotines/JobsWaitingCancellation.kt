package com.abdulwaheed.philiplackner.kotlin_corotines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.abdulwaheed.philiplackner.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

class JobsWaitingCancellation  :  AppCompatActivity() {

    val TAG = "JobsWaitingCancellation"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobs_waiting_cancellation)

        /*
        * when ever we launch a coroutine it returns a job. cancelling a coroutine is not always easy,
        * because cancellation is actually cooperative which means our coroutine should be correctly setup.
        * there needs to be enough time to tell our coroutine has beed cancelled
        * */

        val job = GlobalScope.launch (Dispatchers.Default){
            repeat(5) {
                Log.d(TAG, "strating long running calculation...")
                withTimeout(3000L) {
                    /*
                    * what till will do it will be running until 3 sec if the function does not complete
                    * it will cancel
                    * */
                    for (i in 30..40) {
                        if (isActive) // to check if coroutine is not cancelled
                            Log.d(TAG, "Result for i = $i: ${fib(i)}")
                    }
                }
                Log.d(TAG, "Ending long running calculation...")
             /*   Log.d(TAG, "Coroutine is still working...")
                delay(1000L)*/
            }
        }
        runBlocking {
            //job.join() // this will block our thread until coroutine finishes
            delay(2000L)
            job.cancel() // this will cancel our coroutine
            /*
            * after canceling this job it will keep working. The reason is our coroutine is so busy in calculation
            * inside this for loop
            * */
            Log.d(TAG, "Canceled job")
            Log.d(TAG, "Main thread is continuing")
        }
    }

    fun fib(n: Int): Long {
        return if(n == 0) 0
        else if(n == 1) 1
        else fib(n -1) + fib(n -2)
    }
}
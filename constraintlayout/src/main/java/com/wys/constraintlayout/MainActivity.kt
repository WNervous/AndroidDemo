package com.wys.constraintlayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            val job=GlobalScope.launch {
                // launch new coroutine in background and continue
                delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
                Log.d("MainActivity", "World") // print after delay
            }
            Log.d("MainActivity", "Hello") // main thread continues while coroutine is delayed
            job.join()
        }
//        runBlocking {
//            delay(2000L)
//        }
//        Thread.sleep(2000L) //
        Log.d("MainActivity", "block")

        image.setOnClickListener {
            imageHolder.setContentId(R.id.image)
        }

        replaceTextView.setOnClickListener {
            textView1.text = "abc"
        }
    }
}

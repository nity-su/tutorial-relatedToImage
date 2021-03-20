package com.anlyn.imagezoom

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.*

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.anlyn.imagezoom", appContext.packageName)
        var temp = 0
            runBlocking(Dispatchers.IO) {
                val userOne = withContext(Dispatchers.IO) { fetchFirstUser() }
                val userTwo = withContext(Dispatchers.IO) { fetchSecondUser() }
                temp += userOne
                temp += userTwo
                Log.d("temp",temp.toString())
        }
    }
    fun showUsers(a:Int,b:Int){
        Log.d("thread",Thread.currentThread().name)
    }

    suspend fun fetchFirstUser() :Int {
        // make network call
        // return user
        delay(2000)
        return 1
    }

    suspend fun fetchSecondUser() :Int {
        // make network call
        // return user
        delay(1000)
        return 1
    }
}
package com.shammer.udemytdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shammer.udemytdd.`coroutine-example`.Car
import com.shammer.udemytdd.`coroutine-example`.Engine

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val engine = Engine(500, 195)
        val car = Car(6.0, engine)
        car.start()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
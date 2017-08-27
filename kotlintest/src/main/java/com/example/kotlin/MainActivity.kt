package com.example.kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin.activity.TestDataActivity
import  kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val int=Intent(this@MainActivity, TestDataActivity::class.java)
            startActivity(int)

        }
        button2.setOnClickListener {
            val int=Intent(this@MainActivity, TestDataActivity::class.java)
            startActivity(int)

        }

    }
}

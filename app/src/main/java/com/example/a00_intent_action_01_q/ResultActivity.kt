package com.example.a00_intent_action_01_q

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        intentTextView1.text = intent.getStringExtra("ALARM")
        textView2.text = "今日も一日がんばろう"
    }
}

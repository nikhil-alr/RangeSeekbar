package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var redButton = findViewById<Button>(R.id.redButton)
        var greenButton = findViewById<Button>(R.id.greenButton)

        var increase = findViewById<Button>(R.id.increase)
        var decrease = findViewById<Button>(R.id.decrease)


        var rangeSeekBar = findViewById<RangeSeekBar>(R.id.rangeSeekBar)
        redButton.setOnClickListener {
            rangeSeekBar.updateColor(Color.RED)
        }
        greenButton.setOnClickListener {
            rangeSeekBar.updateColor(Color.GREEN)
        }

        increase.setOnClickListener {
            rangeSeekBar.updateSize(0f,0f,0f,0f)
        }

        decrease.setOnClickListener {
            rangeSeekBar.updateSize(50f,50f,50f,50f)
        }

    }
}



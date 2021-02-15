package com.example.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var display:Int = 0
    var totalTime:Int =0
    lateinit var time: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playButton.visibility = View.INVISIBLE
        pauseButton.visibility = View.INVISIBLE
        stopButton.visibility = View.INVISIBLE
        setButton.setOnClickListener {
            display = Integer.parseInt(editTextNumber.text.toString())
            totalTime = display
            playButton.isEnabled = true
            textView.text = display.toString() + "s"
            progressCounter.max = totalTime
            setButton.visibility = View.INVISIBLE
            editTextNumber.visibility = View.INVISIBLE
            playButton.visibility = View.VISIBLE
            editTextNumber.text = null
        }
        playButton.setOnClickListener {
            progressCounter.visibility = View.VISIBLE
            playButton.visibility = View.INVISIBLE
            pauseButton.visibility = View.VISIBLE
            stopButton.visibility = View.VISIBLE
            startTimer()
        }
        pauseButton.setOnClickListener {
            time.cancel()
            playButton.visibility = View.VISIBLE
            pauseButton.visibility = View.INVISIBLE
            stopButton.visibility = View.VISIBLE
        }
        stopButton.setOnClickListener {
            time.cancel()
            textView.text = display.toString() + "s"
            playButton.visibility = View.INVISIBLE
            pauseButton.visibility = View.INVISIBLE
            stopButton.visibility = View.INVISIBLE
            setButton.visibility = View.VISIBLE
            editTextNumber.visibility = View.VISIBLE
            progressCounter.visibility = View.INVISIBLE
        }
    }
    fun startTimer() {
        time = object : CountDownTimer((display*1000).toLong(), 1000){
            override fun onFinish() {
                textView.text = "finish"
                textView.text = display.toString()
                playButton.visibility = View.INVISIBLE
                pauseButton.visibility = View.INVISIBLE
                stopButton.visibility = View.INVISIBLE
                setButton.visibility = View.VISIBLE
                editTextNumber.visibility = View.VISIBLE
                progressCounter.visibility = View.INVISIBLE
            }

            override fun onTick(millisUntilFinished: Long) {
                display = (millisUntilFinished / 1000).toInt()
                updateUI()
            }
        }.start()
    }
    fun updateUI(){
        textView.text = display.toString() + "s"
        progressCounter.progress = totalTime-display
    }
}
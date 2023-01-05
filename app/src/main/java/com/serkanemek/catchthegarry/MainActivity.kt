package com.serkanemek.catchthegarry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.serkanemek.catchthegarry.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler(Looper.getMainLooper())
    var runnable = Runnable{}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        imageArray.add(binding.imageView1)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)

        hideImages()

        increaseScore(binding)

        var timer = object : CountDownTimer(15000,1000) {
            override fun onTick(p0: Long) {
                binding.timeTextView.text = "Time: " + p0/1000
            }

            override fun onFinish() {
                binding.timeTextView.text = "Time: 0"


                handler.removeCallbacks(runnable)

                for (images in imageArray){
                    images.visibility = View.INVISIBLE
                }


                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Try again?")
                alert.setPositiveButton("Yes") {dialog, which ->
                    val intent = getIntent()
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") {dialog, which ->
                    Toast.makeText(this@MainActivity,"Game over", Toast.LENGTH_LONG).show()
                }

                var alertDialog : AlertDialog = alert.create()
                alertDialog.show()
                alertDialog.window?.setBackgroundDrawableResource(R.drawable.bgforalert)

            }

        }

        timer.start()

    }


    fun increaseScore(binding: ActivityMainBinding){
        binding.imageView1.setOnClickListener {
            score++
            binding.pointTextView.text = "Score: $score"
        }

        binding.imageView2.setOnClickListener {
            score++
            binding.pointTextView.text = "Score: $score"
        }

        binding.imageView3.setOnClickListener {
            score++
            binding.pointTextView.text = "Score: $score"
        }

        binding.imageView4.setOnClickListener {
            score++
            binding.pointTextView.text = "Score: $score"
        }

        binding.imageView5.setOnClickListener {
            score++
            binding.pointTextView.text = "Score: $score"
        }

        binding.imageView6.setOnClickListener {
            score++
            binding.pointTextView.text = "Score: $score"
        }

        binding.imageView7.setOnClickListener {
            score++
            binding.pointTextView.text = "Score: $score"
        }

        binding.imageView8.setOnClickListener {
            score++
            binding.pointTextView.text = "Score: $score"
        }
        binding.imageView9.setOnClickListener {
            score++
            binding.pointTextView.text = "Score: $score"
        }


    }

    fun hideImages(){

        runnable = object : Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,600)
            }
        }
        handler.post(runnable)

    }

}
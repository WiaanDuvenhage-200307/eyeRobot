package com.example.irobot

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.irobot.databinding.ActivityQuestionBinding
import com.example.irobot.databinding.ActivityResultBinding
import com.example.irobot.models.Constants
import com.example.irobot.models.Constants.LAST_USER

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //get scores
        val finalScore = intent.getIntExtra("currentScore", 0)
        Log.i("!!! Final Score: " , finalScore.toString())
        val username = intent.getStringExtra("username")
        //Update UI
        binding.tvResults.text = "$finalScore/2"
        if(finalScore >= 2){
            binding.tvMessage.text = "You are A Robot!"
            binding.ivImage.setImageResource(R.drawable.ic_success)
//            binding.tvResults.setTextColor(R.color.success_color)
        } else {
            binding.tvMessage.text = "Oof! You Are Human"
            binding.ivImage.setImageResource(R.drawable.ic_baseline_cancel_24)
//            binding.tvResults.setTextColor(R.color.error_color)
        }



        //handle the navigation clicks
        binding.btnHome.setOnClickListener{
            saveLastResults(username.toString(), finalScore)

            //intent navigation (context from where this intent happens, new activity)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnAgain.setOnClickListener{
            saveLastResults(username.toString(), finalScore)

            //intent navigation (context from where this intent happens, new activity)
            val intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
            finish()
        }




    }

    fun saveLastResults(username: String, result: Int){
        val sharedpref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedpref.edit()

        editor.apply{
            putString(Constants.LAST_USER, username)
            putInt(Constants.LAST_RESULT, result)
            apply() //to end
        }
    }
}
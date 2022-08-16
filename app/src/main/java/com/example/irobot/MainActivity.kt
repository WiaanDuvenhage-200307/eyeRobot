package com.example.irobot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.irobot.databinding.ActivityMainBinding
import com.example.irobot.models.Constants
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // this sets what XML frontend to use

        //Example of getting access to my button for clicks
        binding.btnStart.setOnClickListener{

            val username = binding.etUsername.text
            //Check if it empty
            if(username.toString() == ""){
                //TODO: Add Validation (Toast or Snackbar)
//                var toast = Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG)
//                toast.show()
                var snackbar = Snackbar.make(it, "Please Enter your Name", Snackbar.LENGTH_LONG)
                snackbar.setAction("OK", {
                    //perform a function
                    Log.i("OK SnackBar", "Yes")
                })

                snackbar.show()

                //Add validation icon on view itself
                binding.etUsername.error = "please add username"
            }else {
                // Navigate to next Activity

                //intent navigation (context from where this intent happens, new activity)
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("username", username.toString())

                startActivity(intent)
                //inish()//close current Activity
            }

        }
        //Get the last results
        binding.fabInfo.setOnClickListener{

            val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
            val lastUser = sharedPref.getString(Constants.LAST_USER, "None")
            val lastResult = sharedPref.getInt(Constants.LAST_RESULT, 0)

            val toast = Toast.makeText(this, "Last User: $lastUser ($lastResult)", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}
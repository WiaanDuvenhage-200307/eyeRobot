package com.example.irobot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.irobot.databinding.ActivityQuestionBinding
import com.example.irobot.models.Constants.getAllQuestions
import com.example.irobot.models.Question

class QuestionActivity : AppCompatActivity() {

    //UPDATED: We need to define our binding view
    private lateinit var binding: ActivityQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //UPDATED: inflates our activities binding into our content
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //GET INTENT VALUES

        //get extra from intent
        val username = intent.getStringExtra("username").toString()

        //get the question number from previous activity
        //0 means index 0 of the array or the id of the question meaning the first que will display
        var questionNumber = intent.getIntExtra("questionNumber", 0)

        //get the current score from previous question
        var currentScore = intent.getIntExtra("current score", 0)

        //set our action bar to the current question number
        title = "Question " + (questionNumber+1).toString()

        //Call for all my questions
        val questions = getAllQuestions()

        //Select Current Question
        val currentQuestion = questions[questionNumber]

        //Example of log to Logcat
        Log.i("Question Count: ", "${currentQuestion.questionText}")

        //bind all question to view values
        updateUI(currentQuestion, username)

        //handle next question click
        binding.buttonNext.setOnClickListener {

            //capture the answered question (validation)
            var selectedAnswer = binding.rgAnswers.checkedRadioButtonId

            if(selectedAnswer == -1){ //-1 means that the user has not selected an answer
                val toast = Toast.makeText(this, "please select your answer", LENGTH_SHORT)
                toast.show()
            } else {
                var userAnswer = findViewById<RadioButton>(selectedAnswer) //find the view that has been selected by their radio id
                Log.i("Selected answer:", userAnswer.text.toString())

                //check correct answer
                if(userAnswer.text.toString() == currentQuestion.optionOne) { //you'll have this equal to your correct answer
                    currentScore += 1
                }
                Log.i("Correct:", "Yes")

                //TODO: Update the UI to show the results if you want them to see the correctness in real time
            }

            Log.i("Question Count" , "${questions.count().toString()}")
            //check if it is last question
            if(questionNumber + 1 == questions.count()){
                //Navigate to Results Activity
                val intent = Intent(this, ResultActivity::class.java)
                //pass the final socre
                intent.putExtra("currentScore", currentScore)
                startActivity(intent)
                finish()
            } else {
                //Navigate to next question

                //Capture the answered question (validation)
                val intent = Intent(this, QuestionActivity::class.java)
                //pass username and next question value
                intent.putExtra("username", username)
                intent.putExtra("questionNumber", questionNumber + 1)
                //pass score
                intent.putExtra("currentScore", currentScore)

                startActivity(intent)
                finish()
            }

        }

    }

    private fun updateUI(currentQuestion: Question, username: String){
        if(currentQuestion.id == 1){
            binding.tvQuestionText.text = "Welcome $username! Your first question is: ${currentQuestion.questionText}"
        } else {
            binding.tvQuestionText.text = "$username! Your next question is: ${currentQuestion.questionText}"
        }

        binding.rbAnswerOne.text = currentQuestion.optionOne
        binding.rbAnswerTwo.text = currentQuestion.optionTwo
        binding.ivIcon.setImageResource(currentQuestion.icon)

        //progress bar
        binding.pbLine.progress = currentQuestion.id
        binding.tvProgress.text = currentQuestion.id.toString() + "/3"
    }
}
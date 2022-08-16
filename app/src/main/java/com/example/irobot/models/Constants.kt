package com.example.irobot.models

import com.example.irobot.R

object Constants {

    //constanrs for sharedpreferences consistency
    val USER_NAME: String = "username"
    val LAST_USER: String = "lastuser"
    val LAST_RESULT: String = "lastresult"

    //this function will return all the questions
    fun getAllQuestions() : ArrayList<Question> {
        //Create our empty ArrayList
        var allQuestions = ArrayList<Question>()

        //TODO: 2. Create Data
        val que1 = Question(
            1,
            "Are you a robot?",
            R.drawable.ic_help,
            "Yes",
            "No"
        )

        val que2 = Question(
            2,
            "What is love?",
            R.drawable.ic_help,
            "I don't know, something you can eat",
            "An emotion I get when looking at you ;)"
        )

        //add question to array
        allQuestions.add(que1)
        allQuestions.add(que2)

        //3. return data
        return allQuestions
    }

    //TODO: Add get functions for all categories | fun getRobotCategoryQuestions()

}
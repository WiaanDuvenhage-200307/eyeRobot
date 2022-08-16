package com.example.irobot.models
//data keyword = blueprint
data class Question(
        val id: Int,
        val questionText: String,
        val icon: Int,
        val optionOne: String,
        val optionTwo: String
        //val answer: Int

)
//
//data class Option (
//        val text: String
//        )
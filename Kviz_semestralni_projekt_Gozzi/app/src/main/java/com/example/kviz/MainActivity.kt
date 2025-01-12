package com.example.kviz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAddQuestion: Button = findViewById(R.id.btn_add_question)
        val btnRemoveQuestions: Button = findViewById(R.id.btn_remove_questions)
        val btnStartQuiz: Button = findViewById(R.id.btn_start_quiz)

        btnAddQuestion.setOnClickListener {
            val intent = Intent(this, PridatOtazkuActivity::class.java)
            startActivity(intent)
        }

        btnRemoveQuestions.setOnClickListener {
            val intent = Intent(this, OdebratOtazkuActivity::class.java)
            startActivity(intent)
        }

        btnStartQuiz.setOnClickListener {
            val intent = Intent(this, SpustitKvizActivity::class.java)
            startActivity(intent)
        }
    }
}

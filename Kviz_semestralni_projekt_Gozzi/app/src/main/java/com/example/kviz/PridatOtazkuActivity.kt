package com.example.kviz

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PridatOtazkuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pridat_otazku)

        // Inicializace databáze
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "quiz-database"
        ).build()

        // Odkazy na prvky z XML
        val otazkaField = findViewById<EditText>(R.id.et_question_text)
        val odpoved1Field = findViewById<EditText>(R.id.et_answer1)
        val odpoved2Field = findViewById<EditText>(R.id.et_answer2)
        val odpoved3Field = findViewById<EditText>(R.id.et_answer3)
        val odpoved4Field = findViewById<EditText>(R.id.et_answer4)
        val spravnaField = findViewById<EditText>(R.id.et_correct_answer)
        val submitButton = findViewById<Button>(R.id.btn_submit_question)

        // Ukládání otázky do databáze
        submitButton.setOnClickListener {
            val otazka = otazkaField.text.toString()
            val odpoved1 = odpoved1Field.text.toString()
            val odpoved2 = odpoved2Field.text.toString()
            val odpoved3 = odpoved3Field.text.toString()
            val odpoved4 = odpoved4Field.text.toString()
            val spravna = spravnaField.text.toString().toIntOrNull()

            if (otazka.isEmpty() || odpoved1.isEmpty() || odpoved2.isEmpty() ||
                odpoved3.isEmpty() || odpoved4.isEmpty() || spravna == null || spravna !in 1..4
            ) {
                Toast.makeText(this, "Vyplňte všechna pole správně!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch(Dispatchers.IO) {
                db.otazkaDao().insert(
                    Otazka(
                        textOtazky = otazka,
                        odpoved1 = odpoved1,
                        odpoved2 = odpoved2,
                        odpoved3 = odpoved3,
                        odpoved4 = odpoved4,
                        spravnaOdpoved = spravna
                    )
                )
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@PridatOtazkuActivity, "Otázka přidána!", Toast.LENGTH_SHORT).show()
                    finish() // Zavře aktivitu
                }
            }
        }
    }
}
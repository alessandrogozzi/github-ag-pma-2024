package com.example.kviz

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OdebratOtazkuActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var questionAdapter: QuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_odebrat_otazku)

        // Inicializace databáze
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "quiz-database"
        ).build()

        // Odkazy na prvky z XML
        val idField = findViewById<EditText>(R.id.id_field)
        val deleteButton = findViewById<Button>(R.id.delete_button)
        val resetButton = findViewById<Button>(R.id.reset_button) // Nové tlačítko
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.questions_recycler_view)

        // Nastavení RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        questionAdapter = QuestionAdapter(emptyList())
        recyclerView.adapter = questionAdapter

        // Načítání otázek a aktualizace RecyclerView
        lifecycleScope.launch {
            db.otazkaDao().getAllOtazky().collect { questions ->
                questionAdapter = QuestionAdapter(questions)
                recyclerView.adapter = questionAdapter
            }
        }

        // Mazání otázky podle ID
        deleteButton.setOnClickListener {
            val id = idField.text.toString().toIntOrNull()
            if (id == null) {
                Toast.makeText(this, "Zadejte platné ID!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch(Dispatchers.IO) {
                db.otazkaDao().deleteById(id)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@OdebratOtazkuActivity, "Otázka odstraněna!", Toast.LENGTH_SHORT).show()

                    // Znovu načteme seznam otázek po smazání
                    db.otazkaDao().getAllOtazky().collect { updatedQuestions ->
                        questionAdapter = QuestionAdapter(updatedQuestions)
                        recyclerView.adapter = questionAdapter
                    }
                }
            }
        }

        // Resetování ID
        resetButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                db.otazkaDao().clearAllOtazky()
                db.otazkaDao().resetOtazkaId()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@OdebratOtazkuActivity, "Databáze vyčištěna a ID resetováno!", Toast.LENGTH_SHORT).show()

                    // Aktualizace RecyclerView
                    questionAdapter = QuestionAdapter(emptyList())
                    recyclerView.adapter = questionAdapter
                }
            }
        }
    }
}

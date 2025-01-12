package com.example.kviz

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.kviz.databinding.ActivitySpustitKvizBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SpustitKvizActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpustitKvizBinding
    private lateinit var db: AppDatabase
    private var questions: List<Otazka> = emptyList()
    private var currentQuestionIndex = 0
    private var score = 0 // Počítadlo skóre

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpustitKvizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializace databáze
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "quiz-database"
        ).build()

        // Načítání otázek pomocí Flow
        lifecycleScope.launch {
            db.otazkaDao().getAllOtazky().collect { otazky ->
                questions = otazky.shuffled() //promichani otazek
                if (questions.isNotEmpty()) {
                    displayQuestion(questions[currentQuestionIndex])
                } else {
                    binding.questionView.text = "Žádné otázky nebyly nalezeny."
                }
            }
        }

        // Nastavení akcí tlačítek pro odpovědi
        binding.answer1Button.setOnClickListener { checkAnswer(1) }
        binding.answer2Button.setOnClickListener { checkAnswer(2) }
        binding.answer3Button.setOnClickListener { checkAnswer(3) }
        binding.answer4Button.setOnClickListener { checkAnswer(4) }
    }

    // Zobrazení aktuální otázky
    private fun displayQuestion(otazka: Otazka) {
        binding.questionView.text = otazka.textOtazky
        binding.answer1Button.text = otazka.odpoved1
        binding.answer2Button.text = otazka.odpoved2
        binding.answer3Button.text = otazka.odpoved3
        binding.answer4Button.text = otazka.odpoved4
    }

    // Kontrola odpovědi a přechod na další otázku
    private fun checkAnswer(answer: Int) {
        val currentQuestion = questions[currentQuestionIndex]

        // Zkontroluj, zda je odpověď správná
        if (answer == currentQuestion.spravnaOdpoved) {
            score++ // Zvýšení skóre
            Toast.makeText(this, "Správně!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Špatně! Správná odpověď byla ${currentQuestion.spravnaOdpoved}", Toast.LENGTH_SHORT).show()
        }

        // Přejdi na další otázku, nebo ukonči kvíz
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            displayQuestion(questions[currentQuestionIndex])
        } else {
            endQuiz() // Konec kvízu
        }
    }

    // Konec kvízu - zobraz skóre a přejdi na hlavní menu
    private fun endQuiz() {
        binding.questionView.text = "Kvíz dokončen! Vaše skóre: $score / ${questions.size}"
        // Skryj tlačítka, protože kvíz skončil
        binding.answer1Button.isEnabled = false
        binding.answer2Button.isEnabled = false
        binding.answer3Button.isEnabled = false
        binding.answer4Button.isEnabled = false

        // Zpoždění před návratem do hlavního menu (volitelné)
        binding.root.postDelayed({
            // Spuštění hlavní aktivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Zavře aktuální aktivitu, aby se nevracela zpět
        }, 3000) // 3 sekundy
    }

}

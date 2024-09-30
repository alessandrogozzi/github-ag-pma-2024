package com.example.myapp001linearlayout

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

         */

        val cislo1 = findViewById<EditText>(R.id.cislo1)
        val cislo2 = findViewById<EditText>(R.id.cislo2)
        val tvInformation = findViewById<TextView>(R.id.tvInformation)
        val secist = findViewById<Button>(R.id.secist)
        val odecist = findViewById<Button>(R.id.odecist)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        //Nastavení obsluhy pro tlačítko Secist

        secist.setOnClickListener {
            val prvniCislo = cislo1.text.toString().toInt()
            val druheCislo = cislo2.text.toString().toInt()

            //Zobrazení textu v TextView
            if (prvniCislo != null && druheCislo != null) {
                val soucet = prvniCislo + druheCislo
                val formattedText = "Výsledek = $soucet"
                tvInformation.text = formattedText
            } else {
                tvInformation.text = "Zadejte platná čísla!"
            }
        }

        //Nastavení obsluhy pro tlačítko Odecist

        odecist.setOnClickListener {
            val prvniCislo = cislo1.text.toString().toIntOrNull()
            val druheCislo = cislo2.text.toString().toIntOrNull()

            //Zobrazení textu v TextView
            if (prvniCislo != null && druheCislo != null) {
                val rozdil = prvniCislo - druheCislo
                val formattedText = "Výsledek = $rozdil"
                tvInformation.text = formattedText
            } else {
                tvInformation.text = "Zadejte platná čísla!"
            }
        }

        //Nastavení obsluhy pro tlačítko Smazat

        btnDelete.setOnClickListener {
            cislo1.text.clear()
            cislo2.text.clear()

            tvInformation.text = ""
        }

    }
}
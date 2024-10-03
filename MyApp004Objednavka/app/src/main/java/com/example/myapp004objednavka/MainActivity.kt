package com.example.myapp004objednavka

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp004objednavka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        //binding settings
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        title = "Objednávka kola"

        binding.btnObjednavka.setOnClickListener {
            val kolobezkaRbId = binding.rgKolobezky.checkedRadioButtonId

            val kolobezka = findViewById<RadioButton>(kolobezkaRbId)

            val vidlice = binding.cbVidlice.isChecked
            val kola = binding.cbKola.isChecked
            val vlakna = binding.cbVlakna.isChecked

            val objednavkaText = "Souhrn objednávky: " + "${kolobezka.text}"

            (if(vidlice) "; lepší vidlice" else "") +
                    (if(kola) "; lepší kola" else "") +
                    (if(vlakna) "; karbonová nanovlákna" else "")

                    binding.tvObjednavka.text = objednavkaText
        }

        //testovaci komentar ignoruj
        //Změna obrázku v závislosti na vybraném radioButtonu
        binding.kolobezka1.setOnClickListener {
            binding.ivKolobezka.setImageResource(R.drawable.kolobezka1)
        }

        binding.kolobezka2.setOnClickListener {
            binding.ivKolobezka.setImageResource(R.drawable.kolobezka2)
        }

        binding.kolobezka3.setOnClickListener {
            binding.ivKolobezka.setImageResource(R.drawable.kolobezka3)
        }

    }
}
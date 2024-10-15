package com.example.myapp006moreactivities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp006moreactivities.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializace bindingu
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nastavení Toolbaru jako ActionBar (pokud používáte Toolbar)
        setSupportActionBar(binding.myToolbar)

        // Načtení dat z intentu
        val color = intent.getStringExtra("favorite_color")
        binding.twInfoTwo.text = "Vaše oblíbená barva: $color"

        // Nastavení listeneru pro tlačítko zpět
        binding.btnBackTwo.setOnClickListener {
            finish()
        }
    }
}
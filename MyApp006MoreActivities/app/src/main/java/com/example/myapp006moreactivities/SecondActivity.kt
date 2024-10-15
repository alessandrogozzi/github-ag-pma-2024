package com.example.myapp006moreactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp006moreactivities.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializace bindingu
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Načtení dat z intentu
        val nickname = intent.getStringExtra("NICK_NAME")
        binding.twInfo.text = "Data z první aktivity. Jméno: $nickname"

        // Nastavení listeneru pro tlačítko zpět
        binding.btnBack.setOnClickListener {
            finish()
        }

        // treti aktivita button
        binding.btnThird.setOnClickListener {
            val color = binding.etColor.text.toString() // získáme text z edit text pole
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("favorite_color", color)
            startActivity(intent)
        }
    }
}
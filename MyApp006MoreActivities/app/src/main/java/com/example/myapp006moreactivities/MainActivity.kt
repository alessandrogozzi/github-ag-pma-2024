package com.example.myapp006moreactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp006moreactivities.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializace bindingu
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nyní místo findViewById používáme binding
        binding.btnSecond.setOnClickListener {
            val nickname = binding.etNickname.text.toString() // získáme text z edit text pole
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("NICK_NAME", nickname)
            startActivity(intent)
        }

        // Nastavení akce pro tlačítko Zobrazit TOAST
        binding.btnShowToast.setOnClickListener {

            // Nafouknutí vlastního layoutu pro Toast
            val inflater = layoutInflater
            val layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.toast_container))

            // Vytvoření Toastu s vlastním layoutem
            val toast = Toast(applicationContext)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = layout
            toast.show()
        }
    }

}
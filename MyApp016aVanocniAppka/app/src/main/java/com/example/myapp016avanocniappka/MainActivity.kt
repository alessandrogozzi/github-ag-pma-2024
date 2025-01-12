package com.example.myapp016avanocniappka

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private lateinit var layout: ConstraintLayout
    private var layoutInitialized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout = findViewById(R.id.layout)

        // Nastavíme listener, který zjistí rozměry layoutu po vykreslení
        layout.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (!layoutInitialized) {
                    layoutInitialized = true
                    setupButtons()
                }
                layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun setupButtons() {
        // Přidání tlačítek pro přidání ozdob
        findViewById<Button>(R.id.addOzdoba1).setOnClickListener {
            addOzdoba(R.drawable.kulicka)
        }
        findViewById<Button>(R.id.addOzdoba2).setOnClickListener {
            addOzdoba(R.drawable.vlocka)
        }
        findViewById<Button>(R.id.addOzdoba3).setOnClickListener {
            addOzdoba(R.drawable.hvezda)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun addOzdoba(drawableId: Int) {
        val ozdoba = ImageView(this)
        ozdoba.setImageResource(drawableId)

        // Nastavení velikosti ozdoby
        val size = 150
        val params = ConstraintLayout.LayoutParams(size, size)
        ozdoba.layoutParams = params

        // Náhodná výchozí pozice v rámci layoutu
        val maxX = layout.width - size
        val maxY = layout.height - size
        ozdoba.x = (Math.random() * maxX).toFloat()
        ozdoba.y = (Math.random() * maxY).toFloat()

        // Přidání posluchače pro přetahování
        ozdoba.setOnTouchListener(DragTouchListener())
        layout.addView(ozdoba)
    }

    // Třída pro přetahování ozdob
    private class DragTouchListener : View.OnTouchListener {
        private var dX = 0f
        private var dY = 0f

        override fun onTouch(view: View, event: MotionEvent): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    view.x = event.rawX + dX
                    view.y = event.rawY + dY
                }
            }
            return true
        }
    }
}

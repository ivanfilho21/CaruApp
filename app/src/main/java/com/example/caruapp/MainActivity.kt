package com.example.caruapp

import android.os.Bundle
import android.os.StrictMode
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = PokemonRepository()

        CoroutineScope(Dispatchers.IO).launch {
            val pokes = repo.get1stGen()

            if (pokes.isNotEmpty()) {
                val poke = pokes[4]

                runOnUiThread {
                    findViewById<TextView>(R.id.text).text = poke.name
                }
            }
        }

        // TODO: Chamar a lista de pokemon e exibir num recyclerView
    }
}
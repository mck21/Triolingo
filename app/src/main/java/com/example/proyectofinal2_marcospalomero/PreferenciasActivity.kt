package com.example.proyectofinal2_marcospalomero

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager

class PreferenciasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, PreferenciasFragment())
            .commit()
    }

    private fun cambiarTamañoFuente() {
        val prefe = PreferenceManager.getDefaultSharedPreferences(this)

        val fuenteGrande = prefe.getBoolean("FuenteGrande", false)

        val configuration = Configuration(resources.configuration)

        if (fuenteGrande) {
            // Aumentar el tamaño de la fuente (un 1.4 veces)
            configuration.fontScale = 1.4f
        } else {
            // Restaurar el tamaño de la fuente predeterminado
            configuration.fontScale = 1.0f
        }

        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    // Si se pulsa el botón de Atras cambiamos el tamaño de la fuente
    override fun onBackPressed() {
        super.onBackPressed()

        cambiarTamañoFuente()
    }
}

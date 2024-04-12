package com.example.proyectofinal2_marcospalomero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proyectofinal2_marcospalomero.databinding.ActivityActualizarBinding
import com.example.proyectofinal2_marcospalomero.modelo.Traduccion
import com.google.android.material.snackbar.Snackbar

class ActualizarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityActualizarBinding
    private var traduccionActualizar: Traduccion? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("Traduccion") && intent.hasExtra("Position")) {
            val bundle = intent.getBundleExtra("Traduccion")
            val traduccion: Traduccion? = bundle?.getParcelable("Traduccion")
            val positionAct = intent.getIntExtra("Position", 0)

            binding.espanolAct.setText(traduccion?.espanol.toString())

            binding.fabActualizar.setOnClickListener {
                if (binding.inglesAct.text.isNotEmpty()) {
                    traduccionActualizar = Traduccion(
                        traduccion?.id,
                        traduccion?.espanol,
                        binding.inglesAct.text.toString()
                    )
                }
                val item = binding.item.text.toString()
                val bundle = Bundle()
                bundle.putParcelable("TraduccionActualizar", traduccionActualizar)

                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("TraduccionActualizar", bundle)
                    putExtra("Item", item)
                    putExtra("Position", positionAct)
                }

                if(item.equals("numeros") || item.equals("dias") || item.equals("colores")){
                    startActivity(intent)
                }else{
                    Snackbar.make(binding.root, "Escribe un tipo de palabra válido", Snackbar.LENGTH_LONG)
                        .setAction("Aceptar") {
                        }
                        .show()
                }

            }
        }
    }
}
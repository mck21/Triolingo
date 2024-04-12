package com.example.proyectofinal2_marcospalomero

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class Dialogo(
    private val idBorrar: Int?,
    private val item: String,
    private val posicionBorrar: Int,
    private val contexto: Context
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Confirmar Borrado")
            .setMessage("Seguro que quieres eliminar esta traduccion?")
            .setPositiveButton("Sí") { _, _ ->
                val intent = Intent(contexto, MainActivity::class.java).apply {
                    putExtra("IdBorrar", idBorrar)
                    putExtra("PosicionBorrar", posicionBorrar)
                    putExtra("Item", item)
                }
                startActivity(intent)
            }
            .setNegativeButton("No") { _, _ ->
                dismiss()
                Toast.makeText(contexto, "Cancelado", Toast.LENGTH_SHORT).show()
            }
            .create()
    }
}
package com.example.proyectofinal2_marcospalomero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import com.example.proyectofinal2_marcospalomero.databinding.ActivityAdministrarBinding
import com.example.proyectofinal2_marcospalomero.modelo.Traduccion
import com.google.android.material.snackbar.Snackbar

class AdministrarActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityAdministrarBinding
    private var item: String = ""
    private var espanol: String = ""
    private var ingles: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdministrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ArrayAdapter.createFromResource(
            this,
            R.array.tipoPalabra,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }

        binding.spinner.onItemSelectedListener = this

        val fragmentInsertar = InsertarFragment()
        val fragmentManager = supportFragmentManager

        binding.fabAnyadir.setOnClickListener {
            binding.img.visibility = View.GONE
            binding.fabAnyadir.visibility = View.GONE
            binding.fabAceptar.visibility= View.VISIBLE
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, fragmentInsertar)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.fabAceptar.setOnClickListener {
            espanol = InsertarFragment.espanolEditText.text.toString()
            ingles = InsertarFragment.inglesEditText.text.toString()

            val bundle = Bundle()
            val traduccion = Traduccion(0, espanol, ingles)
            bundle.putParcelable("TraduccionInsertar", traduccion)

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("TraduccionInsertar", bundle)
                putExtra("Item", item)
            }

            startActivity(intent)
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        item = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}
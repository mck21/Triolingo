package com.example.proyectofinal2_marcospalomero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment

class InsertarFragment : Fragment() {

    companion object {
        lateinit var espanolEditText: EditText
        lateinit var inglesEditText: EditText
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.insertar_fragment, container, false)

        espanolEditText = view.findViewById(R.id.espanolIns)
        inglesEditText = view.findViewById(R.id.inglesIns)

        return view
    }
}

package com.example.proyectofinal2_marcospalomero

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal2_marcospalomero.adapters.DiasAdapter
import com.example.proyectofinal2_marcospalomero.adapters.NumerosAdapter
import com.example.proyectofinal2_marcospalomero.modelo.Traduccion

class DiasFragment (private val contexto: Context) : Fragment()  {

    private var dias: ArrayList<Traduccion> ?= null
    private var position: Int = -1
    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_dias_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dias = MainActivity.dias!!

        recycler = view.findViewById<RecyclerView>(R.id.recyclerDias)

        recycler.setHasFixedSize(true)

        recycler.addItemDecoration(DividerItemDecoration(context, 1))

        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = DiasAdapter(dias!!)

        adapter.setOnItemClickListener {
            val dia = dias!!.get(recycler.getChildAdapterPosition(it))
            position = recycler.getChildAdapterPosition(it)

            Toast.makeText(context, dia.espanol + ": " + dia.ingles, Toast.LENGTH_SHORT).show()

            val bundle = Bundle()

            bundle.putParcelable("Traduccion", dia)

            val intent = Intent(contexto, ActualizarActivity::class.java).apply {
                putExtra("Traduccion", bundle)
                putExtra("Position", position)
            }

            startActivity(intent)

        }

        adapter.setOnItemLongClickListener{
            position = recycler.getChildAdapterPosition(it)
            val confirmarBorradoDialog = Dialogo(
                dias!![position].id,
                "dias",
                position,
                contexto
            )
            confirmarBorradoDialog.show((contexto as AppCompatActivity).supportFragmentManager, "ConfirmarBorradoDialog")

            true
        }

        recycler.adapter = adapter
    }

    fun actualizarLista(nuevosNumeros: ArrayList<Traduccion>) {
        dias = nuevosNumeros
        val adapter = recycler.adapter as DiasAdapter
        adapter.actualizarDatos(dias!!)
    }
}
package com.example.proyectofinal2_marcospalomero.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal2_marcospalomero.MainActivity
import com.example.proyectofinal2_marcospalomero.R
import com.example.proyectofinal2_marcospalomero.basedatos.Favorito
import com.example.proyectofinal2_marcospalomero.modelo.Traduccion
import com.google.android.material.snackbar.Snackbar

class NumerosAdapter (private val lista: ArrayList<Traduccion>): RecyclerView.Adapter<NumerosAdapter.MiViewHolder>() {

    private var listener: View.OnClickListener? = null
    private var listenerLongClick: View.OnLongClickListener? = null
    private var cont : Int = 0
    private var favs: ArrayList<Favorito> ? = MainActivity.favoritos

    class MiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val espanoltextView: TextView
        val inglestextView: TextView
        val heartImage: ImageView

        init {
            espanoltextView = view.findViewById(R.id.espanolTextView)
            inglestextView = view.findViewById(R.id.inglesTextView)
            heartImage = view.findViewById(R.id.heart)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MiViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.numeros_lista, viewGroup, false)

        view.setOnClickListener(listener)
        view.setOnLongClickListener(listenerLongClick)

        return MiViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MiViewHolder, position: Int) {
        viewHolder.espanoltextView.text = lista[position].espanol
        viewHolder.inglestextView.text = lista[position].ingles

        if (favs!!.contains(Favorito(lista[position].espanol!!, lista[position].ingles!!))) {
            viewHolder.heartImage.setImageResource(R.drawable.filledheart)
            cont = 2

        }

        viewHolder.heartImage.setOnClickListener {
            if (cont == 0) {
                viewHolder.heartImage.setImageResource(R.drawable.filledheart)
                cont++
                val intent = Intent(viewHolder.itemView.context, MainActivity::class.java).apply {
                    putExtra("espanol", lista[position].espanol)
                    putExtra("ingles", lista[position].ingles)
                }
                viewHolder.itemView.context.startActivity(intent)

            } else {
                Snackbar.make(viewHolder.itemView, "Esta palabra ya esta en favoritos", Snackbar.LENGTH_LONG)
                    .setAction("Aceptar") {
                    }
                    .show()
            }

        }
    }

    override fun getItemCount() = lista.size

    fun actualizarDatos(nuevosDatos: ArrayList<Traduccion>) {
        lista.clear()
        lista.addAll(nuevosDatos)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onClickListener: View.OnClickListener){
        listener = onClickListener
    }

    fun setOnItemLongClickListener(onLongClickListener: View.OnLongClickListener){
        listenerLongClick = onLongClickListener
    }
}

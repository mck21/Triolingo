package com.example.proyectofinal2_marcospalomero.adapters

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

class FavoritosAdapter(private val lista: ArrayList<Favorito>): RecyclerView.Adapter<FavoritosAdapter.MiViewHolder>() {

    private var listener: View.OnClickListener? = null
    private var cont : Int = 0

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
            .inflate(R.layout.favoritos_lista, viewGroup, false)

        view.setOnClickListener(listener)

        return MiViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MiViewHolder, position: Int) {
        val favorito = lista[position]

        viewHolder.espanoltextView.text = favorito.espanol
        viewHolder.inglestextView.text = favorito.ingles

        viewHolder.heartImage.setOnClickListener{
            if(cont == 0){
                viewHolder.heartImage.setImageResource(R.drawable.unfilledheart)
                cont++
                val intent = Intent(viewHolder.itemView.context, MainActivity::class.java).apply {
                    putExtra("id", favorito.id)
                }
                viewHolder.itemView.context.startActivity(intent)

            } else {
                viewHolder.heartImage.setImageResource(R.drawable.filledheart)
                cont--
            }

        }
    }

    override fun getItemCount() = lista.size

    fun setOnItemClickListener(onClickListener: View.OnClickListener){
        listener = onClickListener
    }

    fun anyadirALista(lista_: ArrayList<Favorito>){
        lista.clear()
        lista.addAll(lista_)

        notifyDataSetChanged()
    }
}

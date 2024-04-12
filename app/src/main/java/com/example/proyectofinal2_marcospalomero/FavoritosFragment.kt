package com.example.proyectofinal2_marcospalomero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal2_marcospalomero.R
import com.example.proyectofinal2_marcospalomero.adapters.ColoresAdapter
import com.example.proyectofinal2_marcospalomero.adapters.FavoritosAdapter
import com.example.proyectofinal2_marcospalomero.basedatos.Favorito
import com.example.proyectofinal2_marcospalomero.modelo.Traduccion
import com.example.proyectofinal2_marcospalomero.viewmodel.FavoritoViewModel

class FavoritosFragment : Fragment()  {
    private lateinit var favoritoViewModel: FavoritoViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_favoritos_fragment, container, false)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoritos: ArrayList<Favorito> = MainActivity.favoritos!!

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerFavoritos)

        recycler.setHasFixedSize(true)

        recycler.addItemDecoration(DividerItemDecoration(context, 1))

        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = FavoritosAdapter(favoritos)

        adapter.setOnItemClickListener {
            val favorito = favoritos.get(recycler.getChildAdapterPosition(it))

            Toast.makeText(context, favorito.espanol + ": " + favorito.ingles, Toast.LENGTH_SHORT).show()
        }

        favoritoViewModel = ViewModelProvider(this).get(FavoritoViewModel::class.java)


        favoritoViewModel.obtenerTodosFavoritos().observe(viewLifecycleOwner) { favorito ->
            adapter.anyadirALista(favorito as ArrayList<Favorito>)
        }


        recycler.adapter = adapter
    }
}
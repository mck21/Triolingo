package com.example.proyectofinal2_marcospalomero.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.proyectofinal2_marcospalomero.basedatos.Favorito
import com.example.proyectofinal2_marcospalomero.repositorio.RepositorioFavorito

class FavoritoViewModel(aplicacion: Application) : AndroidViewModel(aplicacion) {

    private var repositorioFavorito: RepositorioFavorito
    private var todosFavoritos: LiveData<List<Favorito>>

    init {
        repositorioFavorito = RepositorioFavorito(aplicacion)

        todosFavoritos = repositorioFavorito.obtenerTodosFavoritos()
    }

    fun obtenerTodosFavoritos () : LiveData<List<Favorito>> {
        return todosFavoritos
    }

    fun insertar(favorito: Favorito) {
        repositorioFavorito.insertar(favorito)
    }

    fun borrar(id: Int){
        repositorioFavorito.borrar(id)
    }
}
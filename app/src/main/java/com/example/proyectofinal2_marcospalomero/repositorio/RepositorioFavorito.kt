package com.example.proyectofinal2_marcospalomero.repositorio

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.proyectofinal2_marcospalomero.basedatos.BaseDatosRoom
import com.example.proyectofinal2_marcospalomero.basedatos.DaoFavorito
import com.example.proyectofinal2_marcospalomero.basedatos.Favorito

// Repositorio para trabajar con el Dao
class RepositorioFavorito() {

    private lateinit var daoFavorito: DaoFavorito
    private lateinit var todosFavoritos: LiveData<List<Favorito>>

    constructor(aplicacion: Application) : this() {
        val db = BaseDatosRoom.obtenerBaseDatos(aplicacion)
        daoFavorito = db.daoFavorito()
        todosFavoritos = daoFavorito.obtenerFavoritos()
    }

    fun obtenerTodosFavoritos() : LiveData<List<Favorito>> {
        return todosFavoritos
    }

    fun insertar(favorito: Favorito) {
        BaseDatosRoom.databaseWriterExecutor.execute {
            daoFavorito.insertar(favorito)
        }
    }

    fun borrar(id: Int){
        BaseDatosRoom.databaseWriterExecutor.execute {
            daoFavorito.borrar(id)
        }
    }
}
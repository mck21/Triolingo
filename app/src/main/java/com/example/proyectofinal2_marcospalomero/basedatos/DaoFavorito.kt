package com.example.proyectofinal2_marcospalomero.basedatos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DaoFavorito {
    @Query("SELECT * FROM tabla_favorito")
    fun obtenerFavoritos() : LiveData<List<Favorito>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(favorito: Favorito)

    @Query("DELETE FROM tabla_favorito WHERE id =:id")
    fun borrar(id: Int)

}
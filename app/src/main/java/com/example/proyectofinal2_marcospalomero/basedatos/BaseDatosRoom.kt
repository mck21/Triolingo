package com.example.proyectofinal2_marcospalomero.basedatos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.Executors

@Database(entities = [Favorito::class], version = 1, exportSchema = false)
abstract class BaseDatosRoom : RoomDatabase(){
    abstract fun daoFavorito() : DaoFavorito

    companion object {
        @Volatile
        private var INSTANCIA : BaseDatosRoom? = null

        private const val NUM_HILOS = 4

        val databaseWriterExecutor = Executors.newFixedThreadPool(NUM_HILOS)

        fun obtenerBaseDatos (context: Context) : BaseDatosRoom {
                synchronized(this) {
                    var instancia = INSTANCIA

                    if(instancia == null) {
                        instancia = Room.databaseBuilder(context.applicationContext,
                            BaseDatosRoom::class.java, "basedatos_favoritos")
                            .build()

                        INSTANCIA = instancia
                    }
                    return instancia
                }
        }
    }
}
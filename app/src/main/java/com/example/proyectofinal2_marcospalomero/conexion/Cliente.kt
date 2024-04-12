package com.example.proyectofinal2_marcospalomero.conexion

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Cliente {
    companion object{
        const val URL:String = "http://10.0.2.2:3000"
        var retrofit: Retrofit?= null

        fun obtenerCliente(): Retrofit? {
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            }

            return retrofit
        }
    }
}
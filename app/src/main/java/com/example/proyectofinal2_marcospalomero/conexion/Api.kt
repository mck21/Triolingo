package com.example.proyectofinal2_marcospalomero.conexion

import com.example.proyectofinal2_marcospalomero.modelo.Palabras
import com.example.proyectofinal2_marcospalomero.modelo.Traduccion
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface Api {
    @GET("palabras")
    fun obtenerPalabras(): Call<Palabras>

    @PUT("palabras")
    fun guardarTraduccion(@Body palabras: Palabras): Call<Palabras>

    @PUT("palabras")
    fun actualizarTraduccion(@Body palabras: Palabras): Call<Palabras>

    @PUT("palabras")
    fun borrarTraduccion(@Body palabras: Palabras): Call<Palabras>

}
package com.example.proyectofinal2_marcospalomero.basedatos

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tabla_favorito")
data class Favorito(@NonNull @ColumnInfo(name = "espanol") var espanol: String, @NonNull @ColumnInfo(name = "ingles") var ingles: String ){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}
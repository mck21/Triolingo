package com.example.proyectofinal2_marcospalomero.modelo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Palabras(
    @SerializedName("numeros") val numeros: ArrayList<Traduccion>?,
    @SerializedName("dias") val dias: ArrayList<Traduccion>?,
    @SerializedName("colores") val colores: ArrayList<Traduccion>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Traduccion),
        parcel.createTypedArrayList(Traduccion),
        parcel.createTypedArrayList(Traduccion)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(numeros)
        parcel.writeTypedList(dias)
        parcel.writeTypedList(colores)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Palabras> {
        override fun createFromParcel(parcel: Parcel): Palabras {
            return Palabras(parcel)
        }

        override fun newArray(size: Int): Array<Palabras?> {
            return arrayOfNulls(size)
        }
    }
}
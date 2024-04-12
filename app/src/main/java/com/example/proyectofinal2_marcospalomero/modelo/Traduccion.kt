package com.example.proyectofinal2_marcospalomero.modelo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Traduccion(
    @SerializedName("id") val id: Int?,
    @SerializedName("espanol") val espanol: String?,
    @SerializedName("ingles") val ingles: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id!!)
        parcel.writeString(espanol)
        parcel.writeString(ingles)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Traduccion> {
        override fun createFromParcel(parcel: Parcel): Traduccion {
            return Traduccion(parcel)
        }

        override fun newArray(size: Int): Array<Traduccion?> {
            return arrayOfNulls(size)
        }
    }
}

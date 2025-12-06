package com.example.gamestore.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoreItem(
    val itemId: Int,
    val titulo: String,
    val descricao: String,
    val preco: Double,
    val imagemId: Int
) : Parcelable {
    
    fun precoFormatado(): String {
        return "$%.2f".format(preco)
    }
}

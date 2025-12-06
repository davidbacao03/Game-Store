package com.example.gamestore.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoGame(
    val gameId: Int,
    val titulo: String,
    val descricaoCompleta: String,
    val iconeResourceId: Int,
    val capaResourceId: Int,
    val itensDisponiveis: List<StoreItem>
) : Parcelable

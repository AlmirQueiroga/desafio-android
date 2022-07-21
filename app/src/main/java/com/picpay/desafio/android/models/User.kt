package com.picpay.desafio.android.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(
    val img: String,
    val name: String,
    @PrimaryKey
    val id: Int,
    val username: String
) : Parcelable
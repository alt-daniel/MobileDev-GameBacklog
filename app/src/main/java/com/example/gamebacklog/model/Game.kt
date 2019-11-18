package com.example.gamebacklog.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
data class Game (

    var title: String,
    var platform: String,
    var Date: Date,
    var id: Long? = null

)
package com.example.videogames.classes

import android.telecom.Call
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GameDetails(


    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("name")
@Expose
    var name:String,
    @SerializedName("description")
@Expose
    var description:String,
    @SerializedName("released")
@Expose
    var released:String,
    @SerializedName("background_image")
@Expose
    var backgroundImage:String,
    @SerializedName("rating")
@Expose
    var rating:Double  )
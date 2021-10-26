package com.example.videogames.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GameData(
    @SerializedName("id")
    @Expose
    var id:Int ,
    @SerializedName("name")
    @Expose
    var name:String ,
    @SerializedName("released")
    @Expose
    var released:String ,
    @SerializedName("background_image")
    @Expose
    var backgroundImage: String ,
    @SerializedName("rating")
    @Expose
    var rating:Double
    )
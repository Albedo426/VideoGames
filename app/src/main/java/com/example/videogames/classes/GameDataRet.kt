package com.example.videogames.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GameDataRet (
    @SerializedName("results")
    @Expose
    var results:List<GameData>

        )
package com.example.videogames.dbConnection

import com.example.videogames.classes.GameDataRet
import com.example.videogames.classes.GameDetails
import retrofit2.Call
import retrofit2.http.*

interface DAOInterface {
    @GET("games?key=4bb9f61fc85a4025a619635287bb86bd")
    fun getGames():Call<GameDataRet>

    @GET("games/{id}?key=4bb9f61fc85a4025a619635287bb86bd")
    fun getGameDetails(@Path("id") id:Int):Call<GameDetails>
}
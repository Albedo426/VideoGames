package com.example.videogames.dbConnection

class ApiUtils {
    companion object{
        private const val BASE_URL = "https://api.rawg.io/api/"
        fun getDaoInterface(): DAOInterface {
            return RetrofitClient.getClient(BASE_URL).create(DAOInterface::class.java)
        }
    }
}
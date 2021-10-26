package com.example.videogames.sqLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context:Context) : SQLiteOpenHelper(context,"Games",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE favorites (Gameid INTEGER PRIMARY KEY AUTOINCREMENT " +
                ",id INTEGER,name TEXT,description TEXT,released TEXT,background_image TEXT,rating TEXT);")
    }
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS favorites")
        onCreate(db)
    }
}
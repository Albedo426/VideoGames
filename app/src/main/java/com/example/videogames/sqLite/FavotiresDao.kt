package com.example.videogames.sqLite

import android.content.ContentValues
import android.util.Log
import com.example.videogames.classes.GameData
class FavotiresDao {
    private fun addFavorites(vt: DBHelper, item:GameData){
        val db = vt.writableDatabase
        val values = ContentValues()
        values.put("id",item.id)
        values.put("name",item.name)
        values.put("released",item.released)
        values.put("background_image",item.backgroundImage)
        values.put("rating",item.rating)
        db.insertOrThrow("favorites",null,values)
        db.close()

    }
    private fun removeFavorites(vt: DBHelper, Gameid:Int){
        val db = vt.writableDatabase
        db.delete("favorites","id=?", arrayOf(Gameid.toString()))
        db.close()
    }
    //if exsistData
    fun exsistFavorites(vt: DBHelper, id:Int) : Boolean {
        var size = 0
        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT count(*) AS exsist FROM favorites WHERE id='$id'",null)
        while(cursor.moveToNext()){
            size = cursor.getInt(cursor.getColumnIndex("exsist"))
        }
        if(size==0){
            return false
        }
        return true
    }
    //removeFavorites or addFavorites
    fun  controlLike(vt: DBHelper, item:GameData): Boolean{
        val itemArrayList = ArrayList<GameData>()
        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM favorites WHERE id = ${item.id}",null)
        while(cursor.moveToNext()){
            val item = GameData(
                cursor.getInt(cursor.getColumnIndex("id"))
                ,cursor.getString(cursor.getColumnIndex("name"))
                ,cursor.getString(cursor.getColumnIndex("released"))
                ,cursor.getString(cursor.getColumnIndex("background_image"))
                ,cursor.getDouble(cursor.getColumnIndex("rating")))

            itemArrayList.add(item)
        }
        if(itemArrayList.isNotEmpty()) {
            removeFavorites(vt, itemArrayList.get(0).id)
            return false
        }
        else
            addFavorites(vt,item)
        return true
    }
    fun allFavorites(vt: DBHelper) : ArrayList<GameData> {
        val itemArrayList = ArrayList<GameData>()
        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM favorites",null)
        while(cursor.moveToNext()){
            val item = GameData(
                cursor.getInt(cursor.getColumnIndex("id"))
                ,cursor.getString(cursor.getColumnIndex("name"))
                ,cursor.getString(cursor.getColumnIndex("released"))
                ,cursor.getString(cursor.getColumnIndex("background_image"))
                ,cursor.getDouble(cursor.getColumnIndex("rating")))
            itemArrayList.add(item)
        }
        return itemArrayList
    }




}
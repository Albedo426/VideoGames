package com.example.videogames.manager

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.example.videogames.myInterfaces.IGameDetails
import com.example.videogames.R
import com.example.videogames.classes.GameData
import com.example.videogames.classes.GameDetails
import com.example.videogames.databinding.ActivityGameDetailsBinding
import com.example.videogames.sqLite.DBHelper
import com.example.videogames.dbConnection.ApiUtils
import com.example.videogames.sqLite.FavotiresDao
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameDetailsManager(var context: Context): IGameDetails {
    private val kdi = ApiUtils.getDaoInterface()
    override fun getDetails(design: ActivityGameDetailsBinding, i:Int):Boolean{
        if(i!=0){
            kdi.getGameDetails(i).enqueue(object : Callback<GameDetails> {
                override fun onResponse(call: Call<GameDetails>?, response: Response<GameDetails>?) {
                    if(response != null){
                        val list= response.body()
                        val vt = DBHelper(context)
                        //change likeImg Color
                        control(FavotiresDao().exsistFavorites(vt,list.id),design.LikeButton)
                        //list data put design
                        putData(design,list)
                        //appears when the data arrives
                        design.CardView.visibility= View.VISIBLE
                        design.LikeButton.setOnClickListener {
                            control(
                                FavotiresDao()
                                .controlLike(vt, GameData(list.id,list.name,list.released,list.backgroundImage,list.rating))
                                ,design.LikeButton)
                        }
                    }
                }
                override fun onFailure(call: Call<GameDetails>?, t: Throwable?) {
                    Log.e("getGameDetails Error", "onFailure: ",t )
                }
            })
            return false
        }else{
            return true
        }
    }
    fun putData(design: ActivityGameDetailsBinding,list:GameDetails){
        Picasso.get().load(list.backgroundImage).into(design.DetailsImage)
        design.DetailsGameName.text = list.name
        design.DetailsRate.text = context.getString(R.string.Rate).plus(": ").plus(list.rating)
        design.DetailsDescription.text  = correctionDescription(list.description)
        design.DetailsDescription.movementMethod = ScrollingMovementMethod();
        design.DetailsDate.text = context.getString(R.string.Release).plus(": ").plus(list.released)
    }
    //setImgFillColor
    fun control(boolean: Boolean,Img: ImageView):Boolean{
        if(boolean){
            Img.setColorFilter(Color.BLUE)
        }else{
            Img.setColorFilter(Color.WHITE)
        }
        return boolean
    }
    //htmlParser
    fun correctionDescription(description:String):String{
        val descriptionHTML:String
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            descriptionHTML= Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT).toString()
        }else {
            descriptionHTML= Html.fromHtml(description).toString()
        }
        return descriptionHTML
    }
}
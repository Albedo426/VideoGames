package com.example.videogames.manager

import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import com.example.videogames.myInterfaces.IGameData
import com.example.videogames.classes.GameData
import com.example.videogames.classes.GameDataRet
import com.example.videogames.dbConnection.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameDataManager(var limit: Int, listGameMain: MutableLiveData<List<GameData>>, listGameMainPageView: MutableLiveData<List<GameData>>, tempGameList:List<GameData>):IGameData {
    private val kdi = ApiUtils.getDaoInterface()
    var listGameMain: MutableLiveData<List<GameData>>
    var listGameMainPageView: MutableLiveData<List<GameData>>
    var tempGameList:List<GameData>
    init{
        this.listGameMain= listGameMain
        this.listGameMainPageView= listGameMainPageView
        this.tempGameList=tempGameList
    }
    override fun searchSet(newText: String):Boolean{
        //set default List
        listGameMain.value=tempGameList
        listGameMain.value= listGameMain.value!!.filter { actor ->  actor.name.contains(newText)  }
        listGameMainPageView.value=listGameMain.value
        //data exsist
        if(listGameMain.value!!.isNotEmpty()){
            //Is the String greater than 3?
            if(newText.length <=limit){
                if(listGameMain.value!!.size<=limit){
                    listGameMain.value= listOf()
                }
                else{
                    setGameData()
                }
            }
            return true
        }
        return false
    }
    override fun getGames(design: ConstraintLayout){
        kdi.getGames().enqueue(object : Callback<GameDataRet> {
            override fun onResponse(call: Call<GameDataRet>?, response: Response<GameDataRet>?) {
                if(response?.body()?.results != null){
                    val list= response.body().results
                    listGameMain.value=list
                    tempGameList=list
                    setGameData()
                    //data looding
                    design.visibility=View.VISIBLE
                }else{
                    Log.e("gemeGetsError", "response=NULL")
                }
            }
            override fun onFailure(call: Call<GameDataRet>?, t: Throwable?) {
                Log.e("gemeGetsError", "onFailure: ", t)
            }
        })
    }
    fun setGameData(){
        listGameMainPageView.value=listGameMain.value!!.take(limit)
        listGameMain.value=listGameMain.value!!.subList(limit, listGameMain.value!!.size)
    }

}
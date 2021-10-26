package com.example.videogames.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.videogames.classes.GameData
import com.example.videogames.databinding.FavoritesFragmentBinding
import com.example.videogames.sqLite.DBHelper
import com.example.videogames.sqLite.FavotiresDao
import com.info.detaylirvkullanimi.MainAdapter


class FavoritesViewModel (application: Application) : AndroidViewModel(application){
    private val context = getApplication<Application>().applicationContext
    var listGameMain= MutableLiveData<List<GameData>>()
    private val vt = DBHelper(context)
    init{
        listGameMain.value= listOf()
    }
    //get sqlite data
    fun getGames(){
        listGameMain.value= FavotiresDao().allFavorites(vt)
    }
    //recyclerView set design attr
    fun setRecyclerViewDesing(FavRecyclerView: RecyclerView) {
        FavRecyclerView.setHasFixedSize(true)
        FavRecyclerView.layoutManager = LinearLayoutManager(context)
    }
    //livedata RecyclerView
    fun refreshRecyclerView(favRecyclerView: RecyclerView, listGameMain: List<GameData>) {
        favRecyclerView.adapter= MainAdapter(context,listGameMain)
    }
}
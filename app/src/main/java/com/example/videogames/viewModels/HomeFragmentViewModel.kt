package com.example.videogames.viewModels

import android.app.Application
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.videogames.myInterfaces.IGameData
import com.example.videogames.adapters.HomeViewAdapter
import com.example.videogames.classes.GameData
import com.example.videogames.databinding.HomeFragmentBinding
import com.example.videogames.manager.GameDataManager
import com.info.detaylirvkullanimi.MainAdapter
import me.relex.circleindicator.CircleIndicator

class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    var listGameMain= MutableLiveData<List<GameData>>()
    var listGameMainPageView= MutableLiveData<List<GameData>>()
    var manager: IGameData
    var tempGameList: List<GameData>
    init{
        listGameMain.value= listOf()
        listGameMainPageView.value= listOf()
        tempGameList= listOf()
        manager=GameDataManager(3,listGameMain,listGameMainPageView,tempGameList)
    }
    fun getGames(design: ConstraintLayout){
        manager.getGames(design)
    }
    //search Game
    fun onQueryTextChange(newText: String):Boolean{
        return manager.searchSet(newText)
    }
    //liveData fun
    fun refreshRecyclerView(recyclerView:RecyclerView, list:List<GameData>) {
        recyclerView.adapter= MainAdapter(context,list)
    }
    //liveData fun
    fun refreshRecyclerViewViewPage(FragmentViewPage:ViewPager,indicator:CircleIndicator,list:List<GameData>) {
        FragmentViewPage.adapter= HomeViewAdapter(list)
        indicator.setViewPager(FragmentViewPage)
    }
}
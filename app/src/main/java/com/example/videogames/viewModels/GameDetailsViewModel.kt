package com.example.videogames.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.videogames.myInterfaces.IGameDetails
import com.example.videogames.databinding.ActivityGameDetailsBinding
import com.example.videogames.manager.GameDetailsManager

class GameDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private var manager: IGameDetails = GameDetailsManager(context)
    //detailsGet and setDesign
    fun pullData(design: ActivityGameDetailsBinding, i:Int):Boolean{
        return manager.getDetails(design,i)
    }
}
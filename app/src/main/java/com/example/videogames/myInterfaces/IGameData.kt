package com.example.videogames.myInterfaces

import androidx.constraintlayout.widget.ConstraintLayout
import com.example.videogames.databinding.HomeFragmentBinding

interface IGameData {
    fun searchSet(newText: String):Boolean
    fun getGames(design: ConstraintLayout)
}
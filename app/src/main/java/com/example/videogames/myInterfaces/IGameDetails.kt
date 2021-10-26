package com.example.videogames.myInterfaces

import com.example.videogames.databinding.ActivityGameDetailsBinding

interface IGameDetails {
    fun getDetails(design: ActivityGameDetailsBinding, i:Int):Boolean
}
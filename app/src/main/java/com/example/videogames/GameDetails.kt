package com.example.videogames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.videogames.viewModels.GameDetailsViewModel
import com.example.videogames.databinding.ActivityGameDetailsBinding

class GameDetails : AppCompatActivity() {
    private lateinit var design:ActivityGameDetailsBinding
    private val viewModel: GameDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design=DataBindingUtil.setContentView(this,R.layout.activity_game_details)
        val i:Int = intent.getIntExtra("id",0)
        val bool:Boolean=viewModel.pullData(design,i)
        //ifItemNotExsist
        if(bool)
            startActivity(Intent(this, MainActivity::class.java))
    }
}
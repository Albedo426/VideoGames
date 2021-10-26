package com.example.videogames.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.videogames.HomeFragment
import com.example.videogames.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.videogames.MainActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    //search Game
    fun onQueryTextChange(newText: String,mainFragment:HomeFragment):Boolean{
        return mainFragment.onQueryTextChange(newText)
    }
}
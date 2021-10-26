package com.example.videogames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.example.videogames.viewModels.MainActivityViewModel
import com.example.videogames.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() ,SearchView.OnQueryTextListener{
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var design:ActivityMainBinding
    lateinit var mainFragment:HomeFragment
    private lateinit var fragmentFav:FavoritesFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design= DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainFragment=HomeFragment()
        fragmentFav=FavoritesFragment()
        setSupportActionBar(design.MainToolbar)
        //setFragment & MainBottomNavigationView
        supportFragmentManager.beginTransaction().replace(R.id.MainFrameLayout, mainFragment, mainFragment.javaClass.simpleName).commit()
        design.MainBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
    //setFragment & MainBottomNavigationView
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.homePage -> {
                supportFragmentManager.beginTransaction().replace(R.id.MainFrameLayout, mainFragment, mainFragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.favorite -> {
                supportFragmentManager.beginTransaction().replace(R.id.MainFrameLayout, fragmentFav, fragmentFav.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    //setMenu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)
        val item=menu.findItem(R.id.app_bar_search)
        val searchView=item.actionView as SearchView
        searchView.setOnQueryTextListener (this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false;
    }
    //search Game
    override fun onQueryTextChange(newText: String): Boolean {
        return viewModel.onQueryTextChange(newText,mainFragment)
    }



}
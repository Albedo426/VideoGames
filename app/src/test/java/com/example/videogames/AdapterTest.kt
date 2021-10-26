package com.example.videogames

import android.app.Activity
import android.content.Context
import com.example.videogames.adapters.HomeViewAdapter
import com.example.videogames.classes.GameData
import com.info.detaylirvkullanimi.MainAdapter
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AdapterTest {
    private lateinit var  context:Context
    lateinit var list :List<GameData>
    @Before
    fun setUp(){
        context = Activity()
        list = listOf(
        GameData(1,"testName","testReleased","tetImg",1.1),
        GameData(2,"testName2","testReleased2","tetImg2",2.1),
        GameData(3,"testName3","testReleased3","tetImg3",3.1))
        assertNotNull(list)
        assertEquals(3,list.size)
    }
    @Test
    fun testMainAdapter() {
        val mainAdapter = MainAdapter(context,list)
        assertNotNull(mainAdapter)
        assertEquals(3,mainAdapter.itemCount)
    }

    @Test
    fun testPageAdapter() {
        val homeViewAdapter = HomeViewAdapter(list)
        assertNotNull(homeViewAdapter)
        assertEquals(3,homeViewAdapter.count)
    }

}
package com.example.videogames

import android.content.Context
import com.example.videogames.manager.GameDetailsManager
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import android.app.Activity
import android.widget.ImageView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GameDetailsManagerTest {

    private lateinit var  context:Context

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()
    @Before
    fun setUp(){
       context = Activity()
    }
    @Test
    fun testMethod() {
        val gameDataManager = GameDetailsManager(context)
        assertNotNull(gameDataManager)
        assertTrue(gameDataManager.control(true, ImageView(context)))
        assertFalse(gameDataManager.control(false, ImageView(context)))

    }


}
package com.example.videogames

import android.app.Activity
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import com.example.videogames.classes.GameData
import com.example.videogames.manager.GameDataManager
import com.example.videogames.myInterfaces.IGameData
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import java.lang.Exception

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GameDataManagerTest {
    private lateinit var  context:Context
    private lateinit var list :List<GameData>
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
    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()
    @Test
    fun testMethod() {



        val listGameMain= MutableLiveData(list)
        assertNotNull(listGameMain)
        assertEquals(3,listGameMain.value!!.size)
        val gameDataManager:IGameData =GameDataManager(1,listGameMain,listGameMain,list)
        assertNotNull(gameDataManager)

        assertTrue(gameDataManager.searchSet("testName"))
        assertFalse(gameDataManager.searchSet("notName"))

        try {
            gameDataManager.getGames(ConstraintLayout(context))
        }catch (e:Exception){
            assertNotNull(null)
        }

    }



}
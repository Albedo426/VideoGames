package com.example.videogames

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.videogames.viewModels.FavoritesViewModel
import com.example.videogames.databinding.FavoritesFragmentBinding
import com.info.detaylirvkullanimi.MainAdapter

class FavoritesFragment : Fragment() {


    private lateinit var viewModel: FavoritesViewModel
    private lateinit var design: FavoritesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design= DataBindingUtil.inflate(inflater,R.layout.favorites_fragment,container,false)
        //serRecyclerView
        viewModel.setRecyclerViewDesing(design.FavRecyclerView)
        //getGame
        viewModel.getGames()

        //recyclerView LiveData
        viewModel.listGameMain.observe(viewLifecycleOwner, {listGameMain->
            viewModel.refreshRecyclerView(design.FavRecyclerView,listGameMain)
        })
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel = ViewModelProviders.of(requireActivity()).get(FavoritesViewModel::class.java)
        this.viewModel=tempViewModel
    }

    //onResume RefreshData
    override fun onResume() {
        super.onResume()
        viewModel.getGames()
    }
}
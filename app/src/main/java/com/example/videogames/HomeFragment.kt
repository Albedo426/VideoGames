package com.example.videogames

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.videogames.viewModels.HomeFragmentViewModel
import com.example.videogames.databinding.HomeFragmentBinding
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videogames.adapters.HomeViewAdapter

class HomeFragment : Fragment() {


    lateinit var viewModel: HomeFragmentViewModel
    private lateinit var design:HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design=DataBindingUtil.inflate(inflater,R.layout.home_fragment,container,false)
        design.FragmentRecyclerView.setHasFixedSize(true)
        design.FragmentRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.getGames(design.constraint)

        //recyclerview LiveData
        viewModel.listGameMain.observe(viewLifecycleOwner, {list->
            viewModel.refreshRecyclerView(design.FragmentRecyclerView,list)
            design.dataRecSize=list.size
        })
        //PageView LiveData
        viewModel.listGameMainPageView.observe(viewLifecycleOwner, {list->
            viewModel.refreshRecyclerViewViewPage(design.FragmentViewPage,design.indicator,list)
            design.dataPViewSize=list.size
        })
        return design.root
    }
    fun onQueryTextChange(newText: String):Boolean{
        design.newText=newText
        return viewModel.onQueryTextChange(newText)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel = ViewModelProviders.of(requireActivity()).get(HomeFragmentViewModel::class.java)
        this.viewModel=tempViewModel
    }
}
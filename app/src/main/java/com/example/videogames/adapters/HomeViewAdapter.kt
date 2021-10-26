package com.example.videogames.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.videogames.GameDetails
import com.example.videogames.R
import com.example.videogames.classes.GameData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_pager_carda.view.*

class HomeViewAdapter(private val list:List<GameData>): PagerAdapter() {
    override fun getCount(): Int {
        return  list.size
    }
    override fun destroyItem(parent: ViewGroup, position: Int, `object`: Any) {
        // Remove the view from view group specified position
        parent.removeView(`object` as View)
    }
    override fun isViewFromObject(view: View, position: Any): Boolean {
        return view == position as View
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.view_pager_carda,container,false)
        Picasso.get().load(list[position].backgroundImage).into(view.image)
        // Add the view to the parent
        view.image.setOnClickListener{
            val intent = Intent(container.context, GameDetails::class.java)
            intent.putExtra("id", list[position].id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            container.context.startActivity(intent)
        }
        container.addView(view)
        return view
    }

}
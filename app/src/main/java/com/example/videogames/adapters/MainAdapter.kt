package com.info.detaylirvkullanimi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.videogames.GameDetails
import com.example.videogames.R
import com.example.videogames.classes.GameData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_card.view.*

class MainAdapter(private val mContext:Context, private var list:List<GameData>)
    : RecyclerView.Adapter<MainAdapter.MainAdapterHolder>() {

    inner class MainAdapterHolder(view:View):RecyclerView.ViewHolder(view){
        var gameImg:ImageView = view.itemImage
        var gameName:TextView = view.ItemName
        var gameRate:TextView = view.ItemRating
        var gameReleased:TextView = view.itemReleased
        var gameCard:CardView = view.CardView
    }

    override fun getItemCount(): Int {
        return list.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapterHolder {
        val desing = LayoutInflater.from(mContext).inflate(R.layout.item_card,parent,false)
        return MainAdapterHolder(desing)
    }

    override fun onBindViewHolder(holder: MainAdapterHolder, position: Int) {
        val item = list[position]
        Picasso.get().load(item.backgroundImage).into(holder.gameImg)
        holder.gameName.text=item.name
        holder.gameRate.text=item.rating.toString()
        holder.gameReleased.text=item.released
        holder.gameCard.setOnClickListener {
            val intent = Intent(mContext, GameDetails::class.java)
            intent.putExtra("id", item.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            mContext.startActivity(intent)
        }
    }
}
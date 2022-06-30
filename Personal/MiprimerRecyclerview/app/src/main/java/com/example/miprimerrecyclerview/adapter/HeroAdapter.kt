package com.example.miprimerrecyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimerrecyclerview.R
import com.example.miprimerrecyclerview.SuperHero

class HeroAdapter(val superHeroList:List<SuperHero>):RecyclerView.Adapter<HeroHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeroHolder(layoutInflater.inflate(R.layout.superhero,parent,false))
    }

    override fun onBindViewHolder(holder: HeroHolder, position: Int) {
        val item = superHeroList[position]
        holder.render(item)

    }

    override fun getItemCount(): Int {
        return superHeroList.size

    }



}
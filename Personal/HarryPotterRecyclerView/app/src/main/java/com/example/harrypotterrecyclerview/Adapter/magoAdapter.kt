package com.example.harrypotterrecyclerview.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotterrecyclerview.R
import com.example.harrypotterrecyclerview.mago



class magoAdapter (val magoList: List<mago>):RecyclerView.Adapter<magoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): magoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return magoHolder(layoutInflater.inflate(R.layout.mago,parent,false))

    }

    override fun onBindViewHolder(holder: magoHolder, position: Int) {
        val item = magoList[position]
        holder.render(item)

    }

    override fun getItemCount(): Int {
        return magoList.size
    }

}
package com.example.miprimerrecyclerview.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.miprimerrecyclerview.R
import com.example.miprimerrecyclerview.SuperHero
import com.example.miprimerrecyclerview.databinding.SuperheroBinding

class HeroHolder(val view: View): RecyclerView.ViewHolder(view) {

    val binding = SuperheroBinding.bind(view)

    fun render(superHeroModel: SuperHero) {
        binding.txtNameHero.text = superHeroModel.nameHero
        binding.txtRealName.text = superHeroModel.realNameHero
        binding.txtPublisher.text = superHeroModel.publisher
        Glide.with(binding.imgHero.context).load(superHeroModel.image).into(binding.imgHero)

        itemView.setOnClickListener(){
            Toast.makeText(binding.imgHero.context,"Este es ${superHeroModel.nameHero}",Toast.LENGTH_SHORT).show()
        }


    }

}
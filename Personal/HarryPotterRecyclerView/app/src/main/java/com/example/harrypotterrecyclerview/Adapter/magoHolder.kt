package com.example.harrypotterrecyclerview.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harrypotterrecyclerview.databinding.MagoBinding
import com.example.harrypotterrecyclerview.mago


class magoHolder(val view: View):RecyclerView.ViewHolder(view){
    val binding = MagoBinding.bind(view)


    fun render(magoModel: mago){
        binding.txtName.text = magoModel.name
        binding.txtActorName.text = magoModel.actor
        binding.txtHouse.text = magoModel.house
        Glide.with(binding.ivPhoto.context).load(magoModel.image).into(binding.ivPhoto)


    }
}
package com.example.tourismapp.Food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.tourismapp.R
import com.example.tourismapp.databinding.ActivityDetail2Binding

class DetailActivity2 : AppCompatActivity() {
    var imageUrl = ""
    private lateinit var binding: ActivityDetail2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetail2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        if (bundle != null) {
            binding.foodName.text = bundle.getString("foodName")
            binding.foodDesc.text = bundle.getString("foodDesc")
            binding.foodPriority.text = bundle.getString("foodPriority")
            imageUrl = bundle.getString("Image")!!
            Glide.with(this).load(bundle.getString("Image"))
                .into(binding.detailImage)
        }
    }
}
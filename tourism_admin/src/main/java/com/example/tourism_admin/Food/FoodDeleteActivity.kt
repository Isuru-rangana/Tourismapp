package com.example.tourism_admin.Food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import com.example.tourism_admin.R
import com.example.tourism_admin.databinding.ActivityDeleteBinding
import com.example.tourism_admin.databinding.ActivityFoodDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FoodDeleteActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    private lateinit var binding: ActivityFoodDeleteBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.deleteButton.setOnClickListener {
            val food = binding.deleteFood.text.toString()
            if (food.isNotEmpty())
                deleteData(food)
            else
                Toast.makeText(this, "Please enter Location", Toast.LENGTH_SHORT).show()
        }
    }
    private fun deleteData(FoodId: String){
        database = FirebaseDatabase.getInstance().getReference("Food")
        database.child(FoodId).removeValue().addOnSuccessListener {
            binding.deleteFood.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
            finish()

        }.addOnFailureListener {
            Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
        }
    }

    //to implement correct backward navigation
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
package com.example.tourism_admin.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import com.example.tourism_admin.R
import com.example.tourism_admin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.deleteButton.setOnClickListener {
            val location = binding.deleteLocation.text.toString()
            if (location.isNotEmpty())
                deleteData(location)
            else
                Toast.makeText(this, "Please enter Location", Toast.LENGTH_SHORT).show()
        }
    }
    private fun deleteData(locationId: String){
        database = FirebaseDatabase.getInstance().getReference("location")
        database.child(locationId).removeValue().addOnSuccessListener {
                binding.deleteLocation.text.clear()
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
package com.example.tourism_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tourism_admin.databinding.ActivityDeleteBinding
import com.example.tourism_admin.databinding.ActivityFbdeleteBinding
import com.example.tourism_admin.databinding.ActivityFeedBackBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FBDeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFbdeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFbdeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteFBButton.setOnClickListener {
            val location = binding.deleteFB.text.toString()
            if (location.isNotEmpty()){
                deleteFB(location)
            } else {
                Toast.makeText(this, "Please enter location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteFB(location: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Feedbacks")
        databaseReference.child(location).removeValue().addOnSuccessListener {
            binding.deleteFB.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }


}
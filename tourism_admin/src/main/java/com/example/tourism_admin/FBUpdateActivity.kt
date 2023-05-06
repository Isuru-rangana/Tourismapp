package com.example.tourism_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tourism_admin.databinding.ActivityFbupdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FBUpdateActivity : AppCompatActivity() {

        private lateinit var binding: ActivityFbupdateBinding
        private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFbupdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {
            val referencelocation = binding.RefUpdate.text.toString()
            val updateFB = binding.feedbackUpdate.text.toString()

            updateFBdata(referencelocation, updateFB)
        }



    }

    private fun updateFBdata(reflocation: String, feedback: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Feedbacks")
        val user = mapOf<String, String>("location" to reflocation, "feedback" to feedback)
        databaseReference.child(reflocation).updateChildren(user).addOnSuccessListener {
            binding.RefUpdate.text.clear()
            binding.feedbackUpdate.text.clear()
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }

}
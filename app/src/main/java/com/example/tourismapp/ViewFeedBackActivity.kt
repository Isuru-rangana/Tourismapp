package com.example.tourismapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.tourismapp.databinding.ActivityViewFeedBackBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ViewFeedBackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewFeedBackBinding
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewFeedBackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchFbButton.setOnClickListener {
            val searchFb: String = binding.searchFb.text.toString()
            if (searchFb.isNotEmpty()){
                readFBdata(searchFb)
            }else
                Toast.makeText(this, "Please Enter Location", Toast.LENGTH_SHORT).show()
        }


    }

    private fun readFBdata(location: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Feedbacks")
        databaseReference.child(location).get().addOnSuccessListener {
            if(it.exists()){
                val feedback = it.child("feedback").value
                Toast.makeText(this, "Results Found", Toast.LENGTH_SHORT).show()
                binding.searchFb.text.clear()
                binding.locationFBView.text = location.toString()
                binding.FeedbackView.text = feedback.toString()

            }else {
                Toast.makeText(this, "This Location does not have any Reviews", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
    }
}
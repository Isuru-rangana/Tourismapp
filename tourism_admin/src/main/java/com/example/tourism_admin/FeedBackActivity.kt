package com.example.tourism_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tourism_admin.databinding.ActivityFeedBackBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FeedBackActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedBackBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFeedBackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateReview.setOnClickListener {
            val intent = Intent(this@FeedBackActivity, FBUpdateActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.deleteReview.setOnClickListener {
            val intent = Intent(this@FeedBackActivity, FBDeleteActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.submitReview.setOnClickListener {
            val location = binding.locationBox.text.toString()
            val feedback = binding.feedbackBox.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Feedbacks")
            val feedbacks = FBData(location, feedback)
            databaseReference.child(location).setValue(feedbacks).addOnSuccessListener {
                binding.locationBox.text.clear()
                binding.feedbackBox.text.clear()

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@FeedBackActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        }
    }



}
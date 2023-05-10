package com.example.tourism_admin.model

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tourism_admin.R
import com.example.tourism_admin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseRef: DatabaseReference
    private var locationId: String? = null
    private var title: String? = null
    private var desc: String? = null
    private var priority: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseRef = FirebaseDatabase.getInstance().getReference("location")

        locationId = intent.getStringExtra("locationId")
        title = intent.getStringExtra("locationTitle")
        desc = intent.getStringExtra("locationDesc")
        priority = intent.getStringExtra("locationPriority")

        // Set the current data in the UI
        binding.updateTitle.setText(title)
        binding.updateDesc.setText(desc)
        binding.updatePriority.setText(priority)

        // Set the click listener for the update button
        binding.btnUpdate.setOnClickListener {

            var  ettitle =  binding.updateTitle.text.toString()
            var  etdesc =  binding.updateDesc.text.toString()
            var  etpriority =  binding.updatePriority.text.toString()


            //updateData()
            val map = HashMap<String,Any>()

            //add data to hashMap
            map["locationTitle"] = ettitle
            map["locationDesc"] = etdesc
            map["locationPriority"] = etpriority


            //update database from hashMap
            databaseRef.child(locationId!!).updateChildren(map).addOnCompleteListener {
                if( it.isSuccessful){
                    intent = Intent(applicationContext, LocationActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }


        }
    }
/*
    private fun updateData() {
        val updatedTitle = binding.updateTitle.text.toString()
        val updatedDesc = binding.updateDesc.text.toString()
        val updatedPriority = binding.updatePriority.text.toString()

        val updatedData = DataClass(locationId!!, updatedTitle, updatedDesc, updatedPriority)

        // Update the data in the database
        databaseRef.child(locationId!!).setValue(updatedData).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this@UpdateActivity, "Data updated successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this@UpdateActivity, "Failed to update data", Toast.LENGTH_SHORT).show()
            }
        }
    }*/

}
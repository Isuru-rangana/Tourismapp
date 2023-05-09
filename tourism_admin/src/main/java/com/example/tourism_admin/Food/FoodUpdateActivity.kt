package com.example.tourism_admin.Food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tourism_admin.R
import com.example.tourism_admin.databinding.ActivityFoodUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FoodUpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodUpdateBinding
    private lateinit var databaseRef: DatabaseReference
    private var foodId: String? = null
    private var title: String? = null
    private var desc: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseRef = FirebaseDatabase.getInstance().getReference("Food")

        foodId = intent.getStringExtra("foodId")
        title = intent.getStringExtra("foodName")
        desc = intent.getStringExtra("foodDesc")


        // Set the current data in the UI
        binding.updateTitle.setText(title)
        binding.updateDesc.setText(desc)


        // Set the click listener for the update button
        binding.btnUpdate.setOnClickListener {

            var  ettitle =  binding.updateTitle.text.toString()
            var  etdesc =  binding.updateDesc.text.toString()



            //updateData()
            val map = HashMap<String,Any>()

            //add data to hashMap
            map["foodName"] = ettitle
            map["foodDesc"] = etdesc



            //update database from hashMap
            databaseRef.child(foodId!!).updateChildren(map).addOnCompleteListener {
                if( it.isSuccessful){
                    intent = Intent(applicationContext, FoodActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }


        }
    }



}
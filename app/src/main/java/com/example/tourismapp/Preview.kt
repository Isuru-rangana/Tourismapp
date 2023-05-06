package com.example.tourismapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.tourismapp.BookingDs
import com.example.tourismapp.databinding.ActivityPreviewBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Preview : AppCompatActivity() {
    private lateinit var binding: ActivityPreviewBinding
    private lateinit var db: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val extras = intent.extras
        if (extras != null) {
            val texts = intent.getStringArrayExtra("texts")
            if(texts != null && texts[3] == "Single Bed Room"){
                val total1 = (texts[7].toIntOrNull() ?: 0) * (texts[4].toIntOrNull() ?: 0) * (texts[6].toIntOrNull() ?: 0)
                binding.pAmount.text = "$total1"
            }
            else if(texts != null && texts[3]  == "Double Bed Room"){
                val total2 = (texts[8].toIntOrNull() ?: 0) * (texts[4].toIntOrNull() ?: 0) * (texts[6].toIntOrNull() ?: 0)
                binding.pAmount.text = "$total2"
            }

            binding.bName.setText(texts?.get(0))
            binding.bPhone.setText(texts?.get(1))
            binding.bDate.setText(texts?.get(2))
            binding.bRoom.setText(texts?.get(3))
            binding.noRooms.setText(texts?.get(4))
            binding.noGuests.setText(texts?.get(5))
            binding.noDays.setText(texts?.get(6))

        }

    }


    fun insert_Booking(view: View) {
        val name = binding.bName.text.toString()
        val phone = binding.bPhone.text.toString()
        val date = binding.bDate.text.toString()
        val roomType = binding.bRoom.text.toString()
        val rooms = binding.noRooms.text.toString()
        val guests = binding.noGuests.text.toString()
        val days = binding.noDays.text.toString()
        val amount = binding.pAmount.text.toString()
        db = FirebaseDatabase.getInstance().getReference("bookings")
        val booking = BookingDs(name,phone,date,roomType,rooms,guests,days,amount)
        val databaseReference = FirebaseDatabase.getInstance().reference
        val id = databaseReference.push().key
        db.child(id.toString()).setValue(booking).addOnSuccessListener {

            Toast.makeText(this, "data inserted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "data not inserted", Toast.LENGTH_SHORT).show()
        }

    }
}
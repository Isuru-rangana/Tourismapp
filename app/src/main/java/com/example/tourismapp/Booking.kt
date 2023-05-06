package com.example.tourismapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.tourismapp.Preview
import com.example.tourismapp.R
import com.example.tourismapp.databinding.ActivityBookingBinding

class Booking : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val details = intent.getStringArrayExtra("message")
            binding.bookingHotel.setText(details?.get(0))

        }


    }

    fun submitBookingData(view: View){
        val details = intent.getStringArrayExtra("message")

        val name = binding.fullName.text.toString()
        val phone= binding.phone.text.toString()
        val bookingDate = binding.bDate.text.toString()
        val roomType = binding.spinner.selectedItem.toString()
        val rooms = binding.rooms.text.toString()
        val guests = binding.guests.text.toString()
        val days = binding.days.text.toString()

        val texts = arrayOf(
            name,
            phone,
            bookingDate,
            roomType,
            rooms,
            guests,
            days,
            details?.get(1),
            details?.get(2)

            )

        val button = findViewById<Button>(R.id.b_btn)
        button.setOnClickListener {
            val intent = Intent(this, Preview::class.java)
            intent.putExtra("texts", texts)
            startActivity(intent)
        }
    }




}
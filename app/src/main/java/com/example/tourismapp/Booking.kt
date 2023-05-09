package com.example.tourismapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
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
        val phoneEditText = findViewById<EditText>(R.id.phone)
        val bdate = findViewById<EditText>(R.id.b_date)

        phoneEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val phoneRegex = "^0\\d{9}\$".toRegex()
                if (!phoneRegex.matches(phoneEditText.text.toString())) {
                    phoneEditText.error = "Enter valid phone number"
                }
            }
        })

        bdate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val dateRegex = "^\\d{4}\\-\\d{1,2}\\-\\d{1,2}\$".toRegex()
                if (!dateRegex.matches(bdate.text.toString())) {
                    bdate.error = "Enter valid Date (2023-05-25)"
                }
            }
        })

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


        val intent = Intent(this, Preview::class.java)
        intent.putExtra("texts", texts)
        startActivity(intent)



    }




}
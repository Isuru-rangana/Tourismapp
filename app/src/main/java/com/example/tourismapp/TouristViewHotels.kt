package com.example.tourismapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.annotation.SuppressLint
import android.content.Intent

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class TouristViewHotels : AppCompatActivity() {
    private lateinit var db: DatabaseReference
    private lateinit var hotelRecyclerView: RecyclerView
    private lateinit var hotelArrayList: ArrayList<hotelDs>
    private val nodeList = ArrayList<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tourist_view_hotels)
        hotelRecyclerView = findViewById(R.id.tourist_hotel_list)
        hotelRecyclerView.layoutManager = LinearLayoutManager(this)
        hotelRecyclerView.hasFixedSize()
        hotelArrayList = arrayListOf<hotelDs>()
        getItemData()
    }

    private fun getItemData() {
        db = FirebaseDatabase.getInstance().getReference("hotels")
        db.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(hotelsnapshot in snapshot.children){
                        val hotel = hotelsnapshot.getValue(hotelDs::class.java)
                        hotelArrayList.add(hotel!!)

                        nodeList.add(hotelsnapshot.key.toString())
                    }
                    var adapter = TouristHotelAdapter(hotelArrayList)
                    hotelRecyclerView.adapter = adapter
                    /*adapter.setOnItemClickListener(object : TouristHotelAdapter.onItemClickListener{
                        override fun onItemClick(position: Int, name: TextView) {
                            val nodePath: String = nodeList[position]
                            val intent = Intent()
                            intent.putExtra("hotel_id", nodePath)
                            setResult(2, intent)
                            finish()
                        }
                    })*/
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}
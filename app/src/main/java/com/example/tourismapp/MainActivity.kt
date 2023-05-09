package com.example.tourismapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tourismapp.model.LocationActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Userdata>
    private lateinit var adapter: MyAdapter
    private lateinit var auth: FirebaseAuth
    lateinit var imgId: Array<Int>
    lateinit var nameList: Array<String>
    //Bottom nav bar variable
    lateinit var bottomNavView: BottomNavigationView
    //navigation controller variable
    private lateinit var navController: NavController

    //variable to remove back icons from top level destinations
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.setHasFixedSize(true)

        imgId = arrayOf(
            R.drawable.food,
            R.drawable.hotel,
            R.drawable.feedback,
            R.drawable.location
        )

        nameList = arrayOf(
            "Food",
            "Hotel",
            "Feedback",
            "Location "
        )

        newArrayList = arrayListOf()
        getUser()

        adapter = MyAdapter(newArrayList, this)
        recyclerView.adapter = adapter
    }

    private fun getUser() {
        for (i in imgId.indices){
            val data = Userdata(imgId[i], nameList[i])
            newArrayList.add(data)
        }
    }

    fun onClick(position: Int){
        when(position){
            //       0 -> startActivity(Intent(this,Jobspage::class.java))
               1 -> startActivity(Intent(this,TouristViewHotels::class.java))
            //     2 -> startActivity(Intent(this,companypage::class.java))
          3 -> startActivity(Intent(this, LocationActivity::class.java))
        }
    }


}
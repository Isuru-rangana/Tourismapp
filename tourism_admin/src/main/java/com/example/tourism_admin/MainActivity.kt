package com.example.tourism_admin

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tourism_admin.model.DetailActivity
import com.example.tourism_admin.model.LocationActivity
import com.example.tourism_admin.model.UploadActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), MyAdapter.MyClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Userdata>
    private lateinit var adapter: MyAdapter
    private lateinit var auth: FirebaseAuth
    lateinit var imgId: Array<Int>
    lateinit var nameList: Array<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        auth = FirebaseAuth.getInstance()

        //redirect user to the main activity if user is already logged in
        if ( auth.currentUser == null ){
            intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }


        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.setHasFixedSize(true)

        imgId = arrayOf(
            R.drawable.jobimages,
            R.drawable.events,
            R.drawable.companypng,
            R.drawable.profile
        )

        nameList = arrayOf(
            "Jobs",
            "Events",
            "Hotels",
            "My profile"
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

    override fun onClick(position: Int){
        when(position){
     //       0 -> startActivity(Intent(this,Jobspage::class.java))
      //      1 -> startActivity(Intent(this,eventspage::class.java))
            2 -> startActivity(Intent(this,HotelMainActivity::class.java))
            3 -> startActivity(Intent(this,LocationActivity::class.java))
        }
    }
}


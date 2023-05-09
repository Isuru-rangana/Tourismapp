package com.example.tourismapp.Food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tourismapp.FoodClass
import com.example.tourismapp.MyAdapter2
import com.example.tourismapp.R
import com.example.tourismapp.databinding.ActivityFoodBinding
import com.google.firebase.database.*
import java.util.*

class FoodActivity : AppCompatActivity() {
    var databaseReference: DatabaseReference? = null
    var eventListener: ValueEventListener? = null
    private lateinit var dataList: ArrayList<FoodClass>
    private lateinit var adapter: MyAdapter2
    private lateinit var binding: ActivityFoodBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val gridLayoutManager = GridLayoutManager(this@FoodActivity, 1)
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.search.clearFocus()

        val builder = AlertDialog.Builder(this@FoodActivity)
        builder.setCancelable(false)
        builder.setView(R.layout.progress_layout)
        val dialog = builder.create()
        dialog.show()

        dataList = ArrayList()
        adapter = MyAdapter2(this@FoodActivity, dataList)
        binding.recyclerView.adapter = adapter
        databaseReference = FirebaseDatabase.getInstance().getReference("Food")
        dialog.show()
        eventListener = databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (itemSnapshot in snapshot.children) {
                    val dataClass = itemSnapshot.getValue(FoodClass::class.java)
                    if (dataClass != null) {
                        dataList.add(dataClass)
                    }
                }
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            override fun onCancelled(error: DatabaseError) {
                dialog.dismiss()
            }


        })



        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                searchList(newText)
                return true
            }
        })
    }
    fun searchList(text: String) {
        val searchList = java.util.ArrayList<FoodClass>()
        for (FoodClass in dataList) {
            if (FoodClass.foodName?.lowercase()
                    ?.contains(text.lowercase(Locale.getDefault())) == true
            ) {
                searchList.add(FoodClass)
            }
        }
        adapter.searchDataList(searchList)
    }

}
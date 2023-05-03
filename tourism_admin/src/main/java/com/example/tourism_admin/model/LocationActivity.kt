package com.example.tourism_admin.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tourism_admin.R

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tourism_admin.DataClass
import com.example.tourism_admin.MyAdapter
import com.example.tourism_admin.MyAdapter1
import com.example.tourism_admin.databinding.ActivityLocationBinding
import com.google.firebase.database.*
import java.util.*
class LocationActivity : AppCompatActivity() {
    var databaseReference: DatabaseReference? = null
    var eventListener: ValueEventListener? = null
    private lateinit var dataList: ArrayList<DataClass>
    private lateinit var adapter: MyAdapter1
    private lateinit var binding: ActivityLocationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val gridLayoutManager = GridLayoutManager(this@LocationActivity, 1)
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.search.clearFocus()

        val builder = AlertDialog.Builder(this@LocationActivity)
        builder.setCancelable(false)
        builder.setView(R.layout.progress_layout)
        val dialog = builder.create()
        dialog.show()

        dataList = ArrayList()
        adapter = MyAdapter1(this@LocationActivity, dataList)
        binding.recyclerView.adapter = adapter
        databaseReference = FirebaseDatabase.getInstance().getReference("location")
        dialog.show()
        eventListener = databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (itemSnapshot in snapshot.children) {
                    val dataClass = itemSnapshot.getValue(DataClass::class.java)
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
        binding.fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@LocationActivity, UploadActivity::class.java)
            startActivity(intent)
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
        val searchList = java.util.ArrayList<DataClass>()
        for (DataClass in dataList) {
            if (DataClass.locationTitle?.lowercase()
                    ?.contains(text.lowercase(Locale.getDefault())) == true
            ) {
                searchList.add(DataClass)
            }
        }
        adapter.searchDataList(searchList)
    }


}
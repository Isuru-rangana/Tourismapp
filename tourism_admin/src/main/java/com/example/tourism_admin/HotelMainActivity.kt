package com.example.tourism_admin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream
import android.util.Base64
import com.example.tourism_admin.databinding.ActivityHotelMainBinding
import com.example.tourism_admin.hotelDs

class HotelMainActivity : AppCompatActivity() {
    var sImage:String? = ""
    var nodeId = ""
    private lateinit var db: DatabaseReference
    private lateinit var binding: ActivityHotelMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun insert_Data(view: View) {
        val hotelName = binding.hotelName.text.toString()
        val address = binding.address.text.toString()
        val singleBedPrice = binding.singleBPrice.text.toString()
        val doubleBedPrice = binding.doubleBPrice.text.toString()
        db = FirebaseDatabase.getInstance().getReference("hotels")
        val hotel = hotelDs(hotelName,address,singleBedPrice,doubleBedPrice,sImage)
        val databaseReference = FirebaseDatabase.getInstance().reference
        val id = databaseReference.push().key
        db.child(id.toString()).setValue(hotel).addOnSuccessListener {
            binding.hotelName.text.clear()
            binding.address.text.clear()
            binding.singleBPrice.text.clear()
            binding.doubleBPrice.text.clear()
            sImage = ""
            Toast.makeText(this, "data inserted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "data not inserted", Toast.LENGTH_SHORT).show()
        }


    }

    fun insert_Img(view: View) {
        var myfileintent = Intent(Intent.ACTION_GET_CONTENT)
        myfileintent.setType("image/*")
        ActivityResultLauncher.launch(myfileintent)
        binding.hotelImage.visibility = View.VISIBLE
    }

    private val ActivityResultLauncher = registerForActivityResult<Intent,ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ){result:ActivityResult ->
        if(result.resultCode== RESULT_OK){
            val uri = result.data!!.data
            try{
                val inputStream = contentResolver.openInputStream(uri!!)
                val myBitmap = BitmapFactory.decodeStream(inputStream)
                val stream = ByteArrayOutputStream()
                myBitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
                val bytes = stream.toByteArray()
                sImage =Base64.encodeToString(bytes,Base64.DEFAULT)
                binding.hotelImage.setImageBitmap(myBitmap)
                inputStream!!.close()
                Toast.makeText(this, "image selected", Toast.LENGTH_SHORT).show()


            }catch (ex: Exception){
                Toast.makeText(this, ex.message.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }

    private val hotelResultLauncher = registerForActivityResult<Intent,ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ){result:ActivityResult ->
        if(result.resultCode== 2){
            val intent = result.data
            if(intent != null){
                nodeId = intent.getStringExtra("hotel_id").toString()
            }
            db = FirebaseDatabase.getInstance().getReference("hotels")
            db.child(nodeId).get().addOnSuccessListener {
                if(it.exists()){
                    binding.hotelName.setText(it.child("hotelName").value.toString())
                    binding.address.setText(it.child("address").value.toString())
                    binding.singleBPrice.setText(it.child("singleBedPrice").value.toString())
                    binding.doubleBPrice.setText(it.child("doubleBedPrice").value.toString())
                    sImage = it.child("hotelImg").value.toString()
                    val bytes = Base64.decode(sImage, Base64.DEFAULT)
                    val bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
                    binding.hotelImage.setImageBitmap(bitmap)
                    binding.btnInsert.visibility = View.INVISIBLE
                    binding.btnUpdate.visibility = View.VISIBLE
                    binding.btnDelete.visibility = View.VISIBLE
                    binding.hotelImage.visibility = View.VISIBLE


                }else{
                    Toast.makeText(this, "hotel not found", Toast.LENGTH_LONG).show()
                }
            }.addOnFailureListener{
                Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }

    fun showList(view: View){
        var i: Intent
        i = Intent(this, AdminViewHotels::class.java)
        hotelResultLauncher.launch(i)
    }

    fun updateData(view: View){
        val hotelName = binding.hotelName.text.toString()
        val address = binding.address.text.toString()
        val singleBedPrice = binding.singleBPrice.text.toString()
        val doubleBedPrice = binding.doubleBPrice.text.toString()
        db = FirebaseDatabase.getInstance().getReference("hotels")
        val hotel = hotelDs(hotelName,address,singleBedPrice,doubleBedPrice,sImage)

        db.child(nodeId).setValue(hotel).addOnSuccessListener {
            binding.hotelName.text.clear()
            binding.address.text.clear()
            binding.singleBPrice.text.clear()
            binding.doubleBPrice.text.clear()
            sImage = ""
            binding.btnUpdate.visibility = View.INVISIBLE
            binding.btnDelete.visibility = View.INVISIBLE
            binding.btnInsert.visibility = View.VISIBLE
            binding.showList.visibility = View.VISIBLE
            binding.hotelImage.visibility = View.INVISIBLE
            Toast.makeText(this, "Data updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Data not updated", Toast.LENGTH_SHORT).show()
        }
    }
    fun deleteData(view: View){
        db = FirebaseDatabase.getInstance().getReference("hotels")

        db.child(nodeId).removeValue().addOnSuccessListener {
            binding.hotelName.text.clear()
            binding.address.text.clear()
            binding.singleBPrice.text.clear()
            binding.doubleBPrice.text.clear()
            sImage = ""
            binding.btnUpdate.visibility = View.INVISIBLE
            binding.btnDelete.visibility = View.INVISIBLE
            binding.btnInsert.visibility = View.VISIBLE
            binding.showList.visibility = View.VISIBLE
            binding.hotelImage.visibility = View.INVISIBLE
            Toast.makeText(this, "Successfully deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "data not deleted", Toast.LENGTH_SHORT).show()
        }
    }

}
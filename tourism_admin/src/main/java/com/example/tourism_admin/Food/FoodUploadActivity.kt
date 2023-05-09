package com.example.tourism_admin.Food

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.tourism_admin.DataClass
import com.example.tourism_admin.FoodClass
import com.example.tourism_admin.R
import com.example.tourism_admin.databinding.ActivityFoodUploadBinding
import com.example.tourism_admin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat
import java.util.*

class FoodUploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodUploadBinding
    var imageURL: String? = null
    var uri: Uri? = null
    private lateinit var databaseRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val activityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == RESULT_OK) {
                val data = result.data
                uri = data!!.data
                binding.uploadImage.setImageURI(uri)
            } else {
                Toast.makeText(this@FoodUploadActivity, "No Image Selected", Toast.LENGTH_SHORT).show()
            }
        }
        binding.uploadImage.setOnClickListener {
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/*"
            activityResultLauncher.launch(photoPicker)
        }
        binding.saveButton.setOnClickListener {
            uploadData()

            /*val title = binding.uploadTitle.text.toString()
            val desc = binding.uploadDesc.text.toString()
            val priority = binding.uploadPriority.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val users = User(title,desc,priority)
            database.child(title).setValue(users).addOnSuccessListener {
                binding.uploadTitle.text.clear()
                binding.uploadDesc.text.clear()
                binding.uploadPriority.text.clear()

                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
*/
        }
    }
    /*private fun saveData(){
        val storageReference = FirebaseStorage.getInstance().reference.child("Task Images")
            .child(uri!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(this@UploadActivity)
        builder.setCancelable(false)
        builder.setView(R.layout.progress_layout)
        val dialog = builder.create()
        dialog.show()
        storageReference.putFile(uri!!).addOnSuccessListener { taskSnapshot ->
            val uriTask = taskSnapshot.storage.downloadUrl
            while (!uriTask.isComplete);
            val urlImage = uriTask.result
            imageURL = urlImage.toString()
            uploadData()
            dialog.dismiss()
        }.addOnFailureListener {
            dialog.dismiss()
        }
    }*/
    private fun uploadData(){
        val foodName = binding.uploadName.text.toString()
        val foodDesc = binding.uploadDesc.text.toString()


        databaseRef = FirebaseDatabase.getInstance().getReference("Food")

        //uniqe id for record
        var foodId = databaseRef.push().key!!

        //create object
        val dataClass =FoodClass(foodId ,foodName, foodDesc)
        val currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().time)

        //push to databse
        databaseRef.child(foodId )
            .setValue(dataClass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@FoodUploadActivity, "Saved", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.addOnFailureListener { e ->
                Toast.makeText(
                    this@FoodUploadActivity, e.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }
}
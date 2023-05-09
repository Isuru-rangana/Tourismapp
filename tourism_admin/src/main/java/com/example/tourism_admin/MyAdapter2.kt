package com.example.tourism_admin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tourism_admin.Food.FoodDeleteActivity
import com.example.tourism_admin.Food.FoodUpdateActivity
import com.example.tourism_admin.Food.FoodUploadActivity
import com.example.tourism_admin.model.DeleteActivity
import com.example.tourism_admin.model.DetailActivity
import com.example.tourism_admin.model.UploadActivity

class MyAdapter2(private val context: Context, private var foodList: List<FoodClass>) : RecyclerView.Adapter<MyViewHolder1>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder1 {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item2, parent, false)
        return MyViewHolder1(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder1, position: Int) {


        //Glide.with(context).load(locationList[position].locationImage)
        //  .into(holder.recImage)
        holder.recTitle.text = foodList[position].foodName
        holder.recDesc.text = foodList[position].foodDesc
        holder.recCard.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            //intent.putExtra("Image", locationList[holder.adapterPosition].locationImage)
            intent.putExtra("Description", foodList[holder.adapterPosition].foodDesc)
            intent.putExtra("Title", foodList[holder.adapterPosition].foodName)

            context.startActivity(intent)
        }
        holder.recUpdate.setOnClickListener{
            val intent1 = Intent(context, FoodUpdateActivity::class.java).also {
                it.putExtra("foodId", foodList[holder.adapterPosition].foodId)
                it.putExtra("foodName", foodList[holder.adapterPosition].foodName)
                it.putExtra("foodDesc", foodList[holder.adapterPosition].foodDesc)

            }
            context.startActivity(intent1)
            /*intent = Intent(applicationContext, DriverProfileUpdateActivity::class.java).also {
                it.putExtra("name", user.UserName)
                it.putExtra("email", user.UserEmail)
                it.putExtra("mobile", user.UserMobile)
                startActivity(it)*/
        }

        holder.recDelete.setOnClickListener{
            val intent2 = Intent(context, FoodDeleteActivity ::class.java)
            context.startActivity(intent2)
        }
    }


    override fun getItemCount(): Int {
        return foodList.size
    }
    fun searchDataList(searchList: List<FoodClass>) {
        foodList = searchList
        notifyDataSetChanged()
    }


}
class MyViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var recImage: ImageView
    var recTitle: TextView
    var recDesc: TextView

    var recCard: CardView
    var recUpdate: Button
    var recDelete: Button

    init {
        recImage = itemView.findViewById(R.id.recImage)
        recTitle = itemView.findViewById(R.id.recTitle)
        recDesc = itemView.findViewById(R.id.recDesc)

        recCard = itemView.findViewById(R.id.recCard)
        recUpdate = itemView.findViewById(R.id.recUpdate)
        recDelete = itemView.findViewById(R.id.recDelete)
    }

}
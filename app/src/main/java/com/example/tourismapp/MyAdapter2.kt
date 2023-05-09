package com.example.tourismapp

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

import com.example.tourismapp.model.DetailActivity

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
        holder.recPriority.text = foodList[position].foodPriority
        holder.recCard.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            //intent.putExtra("Image", locationList[holder.adapterPosition].locationImage)
            intent.putExtra("Description", foodList[holder.adapterPosition].foodDesc)
            intent.putExtra("Title", foodList[holder.adapterPosition].foodName)
            intent.putExtra("Priority", foodList[holder.adapterPosition].foodPriority)
            context.startActivity(intent)
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
    var recPriority: TextView
    var recCard: CardView
    var recUpdate: Button
    var recDelete: Button

    init {
        recImage = itemView.findViewById(R.id.recImage)
        recTitle = itemView.findViewById(R.id.recTitle)
        recDesc = itemView.findViewById(R.id.recDesc)
        recPriority = itemView.findViewById(R.id.recPriority)
        recCard = itemView.findViewById(R.id.recCard)
        recUpdate = itemView.findViewById(R.id.recUpdate)
        recDelete = itemView.findViewById(R.id.recDelete)
    }

}
package com.example.tourism_admin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tourism_admin.model.DetailActivity


class MyAdapter1(private val context: Context, private var locationList: List<DataClass>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Glide.with(context).load(locationList[position].locationImage)
        //  .into(holder.recImage)
        holder.recTitle.text = locationList[position].locationTitle
        holder.recDesc.text = locationList[position].locationDesc
        holder.recPriority.text = locationList[position].locationPriority
        holder.recCard.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            //intent.putExtra("Image", locationList[holder.adapterPosition].locationImage)
            intent.putExtra("Description", locationList[holder.adapterPosition].locationDesc)
            intent.putExtra("Title", locationList[holder.adapterPosition].locationTitle)
            intent.putExtra("Priority", locationList[holder.adapterPosition].locationPriority)
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return locationList.size
    }
    fun searchDataList(searchList: List<DataClass>) {
        locationList = searchList
        notifyDataSetChanged()
    }
}
class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var recImage: ImageView
    var recTitle: TextView
    var recDesc: TextView
    var recPriority: TextView
    var recCard: CardView
    init {
        recImage = itemView.findViewById(R.id.recImage)
        recTitle = itemView.findViewById(R.id.recTitle)
        recDesc = itemView.findViewById(R.id.recDesc)
        recPriority = itemView.findViewById(R.id.recPriority)
        recCard = itemView.findViewById(R.id.recCard)
    }
}
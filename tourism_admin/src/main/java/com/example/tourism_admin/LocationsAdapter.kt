package com.example.tourism_admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tourism_admin.DataClass
import com.example.tourism_admin.R

class LocationsAdapter(var mList: List<DataClass>) :
    RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {

    private lateinit var mListner : onItemClickListner

    //Setting up onClick listner interface
    interface onItemClickListner{
        fun onItemClick( position: Int)
    }

    fun setOnItemClickListner(listner: onItemClickListner){
        mListner = listner
    }

    inner class ViewHolder(itemView: View, listner: onItemClickListner) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.recTitle)
        val des: TextView = itemView.findViewById(R.id.recDesc)
        val priority: TextView = itemView.findViewById(R.id.recPriority)





        init{
            itemView.setOnClickListener {
                listner.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)



        return ViewHolder(view, mListner)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = mList[position].locationTitle
        holder.des.text = mList[position].locationDesc
        holder.priority.text = mList[position].locationPriority
    }


}
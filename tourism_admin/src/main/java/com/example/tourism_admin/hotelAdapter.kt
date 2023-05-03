package com.example.tourism_admin

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class hotelAdapter(private val hotellist:ArrayList<hotelDs>):RecyclerView.Adapter<hotelAdapter.hotelHolder>() {

    private lateinit var mListener:onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener
    }

    class hotelHolder(hotelView:View,listener: onItemClickListener):RecyclerView.ViewHolder(hotelView){
        val hotelname:EditText = hotelView.findViewById(R.id.view_hotel_name)
        val address: EditText = hotelView.findViewById(R.id.view_address)
        val sbPrice:EditText = hotelView.findViewById(R.id.view_single_b_price)
        val dbPrice:EditText = hotelView.findViewById(R.id.view_double_b_price)
        val hotelimg: ImageView = hotelView.findViewById(R.id.view_hotel_img)
        init{
            hotelView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): hotelHolder {
        val hotelView = LayoutInflater.from(parent.context).inflate(R.layout.admin_hotel,
        parent, false)
        return hotelHolder(hotelView,mListener)
    }

    override fun getItemCount(): Int {
        return hotellist.size
    }

    override fun onBindViewHolder(holder: hotelHolder, position: Int) {
        val currentHotel = hotellist[position]
        holder.hotelname.setText(currentHotel.hotelName.toString())
        holder.address.setText(currentHotel.address.toString())
        holder.sbPrice.setText(currentHotel.singleBedPrice.toString())
        holder.dbPrice.setText(currentHotel.doubleBedPrice.toString())
        val bytes = android.util.Base64.decode(currentHotel.hotelImg,
            android.util.Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
        holder.hotelimg.setImageBitmap(bitmap)
    }
}
package com.example.tourismapp

import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tourismapp.Booking

class TouristHotelAdapter(private val hotellist:ArrayList<hotelDs>):RecyclerView.Adapter<TouristHotelAdapter.hotelHolder>() {



    class hotelHolder(hotelView:View):RecyclerView.ViewHolder(hotelView){
        val hotelname:TextView = hotelView.findViewById(R.id.h_name)
        val address: TextView = hotelView.findViewById(R.id.des)
        val sbPrice:TextView= hotelView.findViewById(R.id.single_bed)
        val dbPrice:TextView = hotelView.findViewById(R.id.double_bed)
        val hotelimg: ImageView = hotelView.findViewById(R.id.h_image)
        //val btn: Button = hotelView.findViewById(R.id.book_btn)

        fun bind(data: hotelDs) {
            // Bind data to view

            val texts = arrayOf(
                data.hotelName,
                data.singleBedPrice,
                data.doubleBedPrice,

            )
            // Set up OnClickListener
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, Booking::class.java)
                intent.putExtra("message", texts)
                context.startActivity(intent)

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): hotelHolder {
        val hotelView = LayoutInflater.from(parent.context).inflate(R.layout.tourist_hotel,
            parent, false)
        return hotelHolder(hotelView)
    }

    override fun getItemCount(): Int {
        return hotellist.size
    }

    override fun onBindViewHolder(holder: hotelHolder, position: Int) {
        val currentHotel = hotellist[position]
        holder.bind(currentHotel)

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
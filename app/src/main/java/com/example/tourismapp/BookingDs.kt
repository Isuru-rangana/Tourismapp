package com.example.tourismapp

import android.provider.ContactsContract.CommonDataKinds.Phone

data class BookingDs(val name:String?=null,
                     val phone:String?=null,
                     val date:String?=null,
                     val roomType:String?=null,
                     val noOfRooms:String?=null,
                     val noOfGuests:String?=null,
                     val noOfDays:String?=null,
                     val totalAmount:String?=null,)

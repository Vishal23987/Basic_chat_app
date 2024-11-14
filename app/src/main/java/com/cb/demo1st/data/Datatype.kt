package com.cb.demo1st.data

import androidx.compose.runtime.Composable

 data class UserData  (
     var userId :String?="",
     var name :String?="",
     var number :String?="",
     var imageUrl :String?="",
     ){
     fun toMap () = mapOf(
         "userId" to userId,
         "name" to name,
         "number" to number,
         "imageUrl" to imageUrl,

         )

     }


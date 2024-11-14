package com.cb.demo1st.Screen

import androidx.navigation.NavDestination
import com.cb.demo1st.DestinationScreen
import com.cb.demo1st.R

enum class BottomNavigationItem(val icon : Int ,val navDestination : DestinationScreen) {


    CHATLIST(R.drawable.message,DestinationScreen.ChatList),
    STATUS(R.drawable.status,DestinationScreen.SingalStatus),
    PROFILE(R.drawable.account,DestinationScreen.Profile)

}
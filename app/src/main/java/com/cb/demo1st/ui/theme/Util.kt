package com.cb.demo1st.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.cb.demo1st.DestinationScreen
import com.cb.demo1st.viewmodel.LCViewModel

fun navigateTO (navController: NavController, route : String){

    navController.navigate(route){

        popUpTo(route)
        launchSingleTop =true
    }
}
@Composable
fun CommonProgressBar(){
    Row(
        modifier =
               Modifier.alpha(0.5f)
            .background(Color.LightGray)
            .clickable(enabled = false) {  }
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun CheckSignedIn(vm: LCViewModel, navController: NavController){
    val SignIn = vm.signIn.value
    val alreadySigIn = remember { mutableStateOf(false) }
    if (SignIn && !alreadySigIn.value){
        alreadySigIn.value = true
        navController.navigate(DestinationScreen.ChatList.route)
    }
}








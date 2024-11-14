package com.cb.demo1st

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cb.demo1st.DestinationScreen.SignUp.route
import com.cb.demo1st.Screen.ChatListScreen
import com.cb.demo1st.Screen.LoginScreen
import com.cb.demo1st.Screen.SignUpScreen
import com.cb.demo1st.ui.theme.Demo1stTheme
import com.cb.demo1st.viewmodel.LCViewModel
import dagger.hilt.android.AndroidEntryPoint

sealed class DestinationScreen(var route: String){
    object SignUp : DestinationScreen ("signup")
    object Login : DestinationScreen ("login")
    object Profile : DestinationScreen ("profile")
    object ChatList : DestinationScreen ("chatlist")
    object SingalChat : DestinationScreen ("singalChat/{chatId}"){
        fun createRoute(id:String) = "SingalChat/$id"
    }

    object SingalStatus : DestinationScreen ("singalStatus/{userId}"){
        fun createRoute(userId:String) = "SingalStatus/$userId"
    }


}
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Demo1stTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChatAppNavigation(
                        
                    )
                }
            }
        }
    }
}
@Composable
fun ChatAppNavigation() {


    val navController = rememberNavController()
    var vm = hiltViewModel<LCViewModel>()
    NavHost(navController = navController, startDestination = DestinationScreen.SignUp.route) {
        composable(DestinationScreen.SignUp.route) {
            SignUpScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.Login.route) {
            LoginScreen(navController=navController, vm = vm)

        }
        composable(DestinationScreen.ChatList.route) {
            ChatListScreen()

        }



    }
}


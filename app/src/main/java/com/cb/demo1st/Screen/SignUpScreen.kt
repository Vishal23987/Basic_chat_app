package com.cb.demo1st.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cb.demo1st.DestinationScreen
import com.cb.demo1st.R
import com.cb.demo1st.ui.theme.CheckSignedIn
import com.cb.demo1st.ui.theme.CommonProgressBar
import com.cb.demo1st.viewmodel.LCViewModel
import com.cb.demo1st.ui.theme.navigateTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController : NavController,
                 vm : LCViewModel
)

{
    CheckSignedIn(vm=vm,navController=navController)

    val focusManager = LocalFocusManager.current


    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .verticalScroll(
                    rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,


        )
        {
            val namestate = remember {
                mutableStateOf(TextFieldValue()) }
            val numberstate = remember {
                mutableStateOf(TextFieldValue()) }
            val emailstate = remember {
                mutableStateOf(TextFieldValue()) }
            val passwordstate = remember {
                mutableStateOf(TextFieldValue()) }




            Image(painter =
         painterResource(id = R.drawable.chat),
             contentDescription = null,
             modifier = Modifier
                 .width(200.dp)
                 .padding(8.dp)
                 .padding(top = 16.dp)


         )
      Text(text = "SignUp",
          fontSize = 30.sp,
          fontFamily = FontFamily.SansSerif,
          fontWeight =   FontWeight.Bold,
          modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = namestate.value,
                onValueChange = {
                namestate.value = it

             },
                label = {Text("Name")},
                modifier = Modifier.padding(8.dp),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
                )
            OutlinedTextField(
                value = numberstate.value,
                onValueChange = {
                    numberstate.value = it

                },
                label = {Text("Number")},
                modifier = Modifier.padding(8.dp),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                value = emailstate.value,
                onValueChange = {
                    emailstate.value = it

                },
                label = {Text("Email")},
                modifier = Modifier.padding(8.dp),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                value = passwordstate.value,
                onValueChange = {
                    passwordstate.value = it

                },
                label = {Text("Password")},
                modifier = Modifier.padding(8.dp),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )



            Button(onClick =   { vm.signup(
             namestate.value.text,
                numberstate.value.text,
                emailstate.value.text,
                passwordstate.value.text,

                ) },
                modifier = Modifier.padding(8.dp)) {
        Text(text = "Sign Up")
            }

            Text(text = "Already a User ? Go to Login - >",
                color = Color.Blue ,
                fontFamily = FontFamily.SansSerif,
                fontWeight =  FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
                    .clickable {
                        navigateTO(navController,DestinationScreen.Login.route)
                    }
                      )


        }
        if (vm.inprocess.value){
            CommonProgressBar()
        }



}}
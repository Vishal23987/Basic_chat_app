package com.cb.demo1st.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cb.demo1st.data.Event
import com.cb.demo1st.data.USER_NODE
import com.cb.demo1st.data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LCViewModel @Inject constructor(
    val auth : FirebaseAuth,
    val db : FirebaseFirestore
) : ViewModel() {

    var inprocess = mutableStateOf( false)
    val eventMutableState = mutableStateOf<Event<String>?>(null)
    var signIn = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)

    init {
        val currentUser = auth.currentUser
        signIn.value = currentUser!=null
        currentUser?.uid?.let {
            getUserData(it)
        }
    }

    fun signup(name:String,number:String,email:String,password:String)
    {
        inprocess.value= true
        if (name.isEmpty()or number.isEmpty()or email.isEmpty()or password.isEmpty()){
            handleExcepation(customMessage = "Please Fill All field")
            return
        }
        inprocess.value
        db.collection(USER_NODE).whereEqualTo("number",number).get().addOnSuccessListener {
            if (it.isEmpty){
                handleExcepation(customMessage = "Number Already Exist")
                inprocess.value = false
            }
        }
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
            if (it.isSuccessful) {
                signIn.value = true
                createOrUpdateProfile(name,number)
                println("signUp : User Logged IN")
            }else
            {
                println("signUp : failed")
            }
        }

    }
    fun createOrUpdateProfile(name: String? =null,number: String?=null,
                              imageurl:String?=null){
        val uid =auth.currentUser?.uid
        val userData =UserData(
            userId = uid,
            name = name?:userData.value?.name,
            number= number?:userData.value?.number,
            imageUrl= imageurl?:userData.value?.imageUrl,

            )

        uid?.let {
            inprocess.value = true
            db.collection(USER_NODE)
            .document(uid)
            .get().addOnSuccessListener {
                    if (it.exists()) {
                        //  Update user Data
                    } else {
                        db.collection(USER_NODE)
                            .document(uid)
                            .set(userData)
                        inprocess.value = false
                        getUserData(uid)
                    }
                }
                .addOnFailureListener{
                    handleExcepation(it,"Cannot Retrieve User")

        }
        }


    }

    private fun getUserData(uid:String) {
        inprocess.value = true
        db.collection(USER_NODE).document(uid).addSnapshotListener{
            value,error->

            if(error!=null){
                handleExcepation(error,"Can not Retrieve User")
            }
            if (value != null){
                var user = value.toObject<UserData>()
                userData.value = user
                inprocess.value = false
                
            }
        }

    }

    fun handleExcepation(exception: Exception?=null,customMessage:String="")
    {
        Log.e("demo1st","demo1st excepation:",exception)
        exception?.printStackTrace()
        val errormsg = exception?.localizedMessage?:""
        val message =if (customMessage.isNullOrEmpty())
            errormsg else customMessage

        eventMutableState.value =Event(message)
        inprocess.value = false

    }


 fun loginIn(email: String,password: String){
     if (email.isEmpty() or password.isEmpty()){
         handleExcepation(customMessage = "Please Fill the all fields")
         return
     }else
     {
         inprocess.value = true
         auth.signInWithEmailAndPassword(email,password)
             .addOnCompleteListener{
             if (it.isSuccessful){
                 signIn.value=true
                 inprocess.value = false
                 auth.currentUser?.uid?.let {
                     getUserData(it)
                 }
             }else{
                 handleExcepation(exception = it.exception, customMessage = "Login failed")
             }
         }
     }
 }
}
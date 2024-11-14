package com.cb.demo1st.data

import dagger.hilt.android.internal.Contexts
import kotlin.contracts.Returns

open class Event<out T> ( val content: T){
    var hasBeenHandle=false
    fun getContentorNull():T?{
       return if(hasBeenHandle)null
        else {
            hasBeenHandle=true
            content
        }
    }

}
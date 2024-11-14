package com.cb.demo1st

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
@Provides
    fun ProvideAuthentication():FirebaseAuth = Firebase.auth

    @Provides
    fun ProvideFirestore():FirebaseFirestore = Firebase.firestore
}
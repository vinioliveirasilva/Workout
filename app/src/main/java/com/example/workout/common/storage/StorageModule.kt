package com.example.workout.common.storage

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.database.database
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

sealed interface StorageType {
    data object Firebase : StorageType
    data object SharedPref : StorageType
}

val StorageModule = module {
    /*
    single<Storage>(named<StorageType.Firebase>()) {
        FirebaseStorageWrapper(
            firebaseDatabase = Firebase.database.getReference()
        )
    }

     */

    single<Storage> {
        SharedPreferencesStorage(
            sharedPreferences = androidContext().getSharedPreferences("TESTe", Context.MODE_PRIVATE)
        )
    }
}
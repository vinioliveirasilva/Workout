package com.example.workout.common.storage

import com.google.firebase.Firebase
import com.google.firebase.database.database
import org.koin.core.qualifier.named
import org.koin.dsl.module

sealed interface StorageType {
    data object Firebase : StorageType
}

val StorageModule = module {
    single<Storage>(named<StorageType.Firebase>()) {
        FirebaseStorageWrapper(
            firebaseDatabase = Firebase.database.getReference()
        )
    }
}
package com.example.workout

import com.example.workout.common.storage.StorageModule
import com.example.workout.home.HomeModule
import org.koin.dsl.module

val FeatureModules = module {
    includes(
        StorageModule,
        HomeModule,
    )
}
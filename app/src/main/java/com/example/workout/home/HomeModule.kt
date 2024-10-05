package com.example.workout.home

import com.example.workout.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val HomeModule = module {
    viewModelOf(::HomeViewModel)
}
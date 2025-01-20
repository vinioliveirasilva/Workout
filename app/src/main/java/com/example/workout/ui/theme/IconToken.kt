package com.example.workout.ui.theme

import androidx.annotation.DrawableRes
import com.example.workout.R

enum class IconToken(val id: String, @DrawableRes val icon: Int) {
    Fitness("fitness", R.drawable.fitness_center);

    companion object {
        fun get(id: String) = entries.first { it.id == id }.icon
    }
}
package com.example.workout.common.storage

interface Storage {
    suspend fun <T> getNullableValue() : T?
    suspend fun <T> getValue(default: T) : T
    suspend fun <T> setValue(value: T)
}
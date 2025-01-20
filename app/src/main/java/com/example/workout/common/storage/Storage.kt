package com.example.workout.common.storage

import kotlinx.coroutines.flow.Flow

interface Storage {
    fun <T> getNullableValue(key: String) : Flow<T?>
    fun <T> getValue(key: String) : Flow<T>
    suspend fun <T> setValue(key: String, value: T)
}
package com.example.workout.common.storage

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable

class SharedPreferencesStorage(
    private val sharedPreferences: SharedPreferences,
    private val gsonProvider: Gson,
) : Storage {

    private val editor by lazy {
        sharedPreferences.edit()
    }

    override fun <T> getNullableValue(key: String): Flow<T?> = flow {
        val stringResult = sharedPreferences.getString(key, null) ?: return@flow
        val type = object : TypeToken<T>() {}
        val result = gsonProvider.fromJson(stringResult, type.rawType)
        emit(result as T?)
    }

    override fun <T> getValue(key: String): Flow<T> = flow {
        val stringResult = sharedPreferences.getString(key, null) ?: throw Exception("valor nulo")
        val type = object : TypeToken<T>() {}
        val result = gsonProvider.fromJson(stringResult, type.rawType)
        emit(result as T)
    }

    override suspend fun <T> setValue(key: String, value: T) {
        editor.putString(key, gsonProvider.toJson(value)).commit()
    }
}
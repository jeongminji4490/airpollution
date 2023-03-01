package com.example.airpollution

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class StoreDistrictName (private val context: Context) {

    // to make sure there's only one instance
    companion object {
        private val Context.datastore by preferencesDataStore(name = "store_district_name")
        private val districtKey = stringPreferencesKey("district_key")
        private val moveKey = stringPreferencesKey("move_key")
        private val settingKey = booleanPreferencesKey("setting_key")
    }

    val districtName: Flow<String> = context.datastore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[districtKey] ?: ""
        }

    suspend fun setDistrictName(districtName: String) {
        context.datastore.edit { preferences ->
            preferences[districtKey] = districtName
        }
    }

    val moveName: Flow<String> = context.datastore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[moveKey] ?: ""
        }

    suspend fun setMoveName(moveName: String) {
        context.datastore.edit { preferences ->
            preferences[moveKey] = moveName
        }
    }
}
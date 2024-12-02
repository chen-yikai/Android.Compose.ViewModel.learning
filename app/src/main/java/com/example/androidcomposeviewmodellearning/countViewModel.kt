package com.example.androidcomposeviewmodellearning

import android.content.Context
import android.provider.Settings.Global.getString
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import java.util.prefs.Preferences

class countViewModel : ViewModel() {
    var count by mutableStateOf(0)
        private set


    fun increment() {
        count++
    }
}
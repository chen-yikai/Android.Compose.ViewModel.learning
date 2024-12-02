package com.example.androidcomposeviewmodellearning

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract.ProfileSyncState.set
import android.provider.Settings.Global.putInt
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import com.example.androidcomposeviewmodellearning.ui.theme.AndroidComposeViewModellearningTheme
import java.util.prefs.Preferences

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<countViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Main(viewModel)
        }
    }
}

@Composable
fun greeting(viewModel: countViewModel) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("test", Context.MODE_PRIVATE)
    var countSaved = sharedPref.getInt("count", 0)
    var count by rememberSaveable { mutableStateOf(countSaved) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(count.toString(), fontSize = 50.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            count++
            sharedPref.edit().putInt("count", count).apply()
        }) {
            Text("Click me")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                count = 0
                sharedPref.edit().putInt("count", count).apply()
            }
        ) {
            Text("clear data")
        }
    }
}

@Composable
fun signUp() {
    Column {
        Text("Sign Up", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(30.dp))

    }
}

@Composable
fun Main(viewModel: countViewModel) {
//    greeting(viewModel)
    signUp()
}
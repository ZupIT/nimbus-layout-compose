package br.com.zup.nimbus.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.zup.com.nimbus.compose.ProvideNimbus
import br.com.zup.nimbus.store.navigation.BottomNavigator
import br.com.zup.nimbus.store.navigation.NavigationGraph
import br.com.zup.nimbus.store.nimbus.BottomTabNavigation
import br.com.zup.nimbus.store.nimbus.nimbus
import br.com.zup.nimbus.store.ui.theme.NimbusStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NimbusStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ProvideNimbus(nimbus) {
                        MainContent()
                    }
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    val navController = rememberNavController()

    BottomTabNavigation.SideEffect()

    Scaffold(
        bottomBar = { BottomNavigator(navController = navController) }
    ) {
        Column(modifier = Modifier.padding((it))) {
            NavigationGraph(navController = navController)
        }
    }
}

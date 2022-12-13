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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.com.zup.nimbus.compose.ProvideNimbus
import br.com.zup.nimbus.compose.layout.extensions.imageProvider
import br.com.zup.nimbus.core.deserialization.AnyServerDrivenData
import br.com.zup.nimbus.store.navigation.BottomNavigator
import br.com.zup.nimbus.store.navigation.NavigationGraph
import br.com.zup.nimbus.store.navigation.navigate
import br.com.zup.nimbus.store.nimbus.CachedImageProvider
import br.com.zup.nimbus.store.nimbus.nimbus
import br.com.zup.nimbus.store.nimbus.storeUI
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
                    ProvideNimbus(nimbus.imageProvider(CachedImageProvider())) {
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

    LaunchedEffect(navController) {
        storeUI.addAction("changeBottomNavigatorRoute") {
            val route = AnyServerDrivenData(it.action.properties).get("route").asStringOrNull()
            route?.let { navigate(navController, route) }
        }
    }

    Scaffold(
        bottomBar = { BottomNavigator(navController = navController) }
    ) {
        Column(modifier = Modifier.padding((it))) {
            NavigationGraph(navController = navController)
        }
    }
}

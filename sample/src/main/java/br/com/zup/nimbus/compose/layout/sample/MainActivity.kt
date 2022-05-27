package br.com.zup.nimbus.compose.layout.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import br.com.zup.nimbus.compose.layout.layoutComponents
import br.com.zup.nimbus.compose.layout.sample.components.CustomError
import br.com.zup.nimbus.compose.layout.sample.components.customComponents
import br.com.zup.nimbus.compose.layout.sample.theme.AppTheme
import br.zup.com.nimbus.compose.Nimbus
import br.zup.com.nimbus.compose.NimbusConfig
import br.zup.com.nimbus.compose.NimbusNavigator

class MainActivity : ComponentActivity() {
    private val config = NimbusConfig(
        baseUrl = BASE_URL,
        components = customComponents + layoutComponents,
        logger = AppLogger(),
        errorView = { throwable: Throwable, retry: () -> Unit ->
            CustomError(throwable = throwable, retry = retry)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Nimbus(config = config) {
                            NimbusNavigator(json = TOUCHABLE_JSON)
                        }
                    }

                }
            }
        }
    }
}



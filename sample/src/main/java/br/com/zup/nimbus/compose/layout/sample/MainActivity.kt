package br.com.zup.nimbus.compose.layout.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import br.com.zup.nimbus.compose.layout.extensions.imageProvider
import br.com.zup.nimbus.compose.layout.layoutActions
import br.com.zup.nimbus.compose.layout.layoutComponents
import br.com.zup.nimbus.compose.layout.sample.components.CustomError
import br.com.zup.nimbus.compose.layout.sample.components.customComponents
import br.com.zup.nimbus.compose.layout.sample.theme.AppTheme
import br.zup.com.nimbus.compose.Nimbus
import br.zup.com.nimbus.compose.NimbusNavigator
import br.zup.com.nimbus.compose.ProvideNimbus
import com.zup.nimbus.core.network.ViewRequest

class MainActivity : ComponentActivity() {
    private val nimbus = Nimbus(
        actions = layoutActions,
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
                    ProvideNimbus(nimbus.imageProvider(DefaultImageProvider())) {
                        NimbusNavigator(ViewRequest("/11"))
                    }
                }
            }
        }
    }
}



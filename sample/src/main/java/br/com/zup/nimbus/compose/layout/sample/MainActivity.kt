package br.com.zup.nimbus.compose.layout.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import br.com.zup.nimbus.compose.layout.extensions.imageProvider
import br.com.zup.nimbus.compose.layout.layoutComponents
import br.com.zup.nimbus.compose.layout.sample.components.CustomError
import br.com.zup.nimbus.compose.layout.sample.components.customComponents
import br.com.zup.nimbus.compose.layout.sample.theme.AppTheme
import br.zup.com.nimbus.compose.Nimbus
import br.zup.com.nimbus.compose.NimbusConfig
import br.zup.com.nimbus.compose.NimbusNavigator
import com.zup.nimbus.core.network.ViewRequest

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
                    Nimbus(config = config.imageProvider(DefaultImageProvider())) {
                        NimbusNavigator(json = """{
  "_:component": "layout:stack",
  "children": [
    {
      "_:component": "layout:positioned",
      "children": [{
        "_:component": "layout:row",
        "children" : [{
          "_:component": "material:text",
          "properties": {
            "text": "r"
          }
        }]
      }],
      "properties": {
        "backgroundColor": "#FF0000",
        "width": 50.0,
        "height": 50.0
      }
    },
    {
      "_:component": "layout:positioned",
      "children": [{
        "_:component": "layout:row",
        "children" : [{
          "_:component": "material:text",
          "properties": {
            "text": "b"
          }
        }]
      }],
      "properties": {
        "backgroundColor": "#0000FF",
        "width": 50.0,
        "height": 50.0,
        "x": 25.0,
        "y": 25.0
      }
    }],
  "properties": {
    "backgroundColor": "#CCCCCCFF",
    "width": 75.0,
    "height": 75.0
  }
}""")
                    }
                }
            }
        }
    }
}



package br.com.zup.nimbus.compose.layout.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import br.com.zup.nimbus.compose.layout.extensions.imageProvider
import br.com.zup.nimbus.compose.layout.layoutUI
import br.com.zup.nimbus.compose.layout.sample.components.CustomError
import br.com.zup.nimbus.compose.layout.sample.components.materialComponents
import br.com.zup.nimbus.compose.layout.sample.theme.AppTheme
import br.zup.com.nimbus.compose.Nimbus
import br.zup.com.nimbus.compose.NimbusNavigator
import br.zup.com.nimbus.compose.ProvideNimbus

const val JSON = """{
  "_:component": "layout:column",
  "children": [
    {
      "_:component": "layout:column",
      "children": [{
        "_:component": "layout:text",
        "properties": {
          "text": "r"
        }
      }],
      "properties": {
        "height": "expand",
        "backgroundColor": "#FF0000"
      }
    },
    {
      "_:component": "layout:column",
      "children": [{
        "_:component": "layout:text",
        "properties": {
          "text": "g"
        }
      }],
      "properties": {
        "height": "expand",
        "backgroundColor": "#00FF00"
      }
    },
    {
      "_:component": "layout:column",
      "children": [{
        "_:component": "layout:text",
        "properties": {
          "text": "b"
        }
      }],
      "properties": {
        "height": "expand",
        "backgroundColor": "#0000FF"
      }
    }],
  "properties": {
    "height": 150.0,
    "backgroundColor": "#CCCCCCFF"
  }
}"""

const val JSON_LAZY = """{
  "_:component": "layout:lazyRow",
  "children": [
    {
      "_:component": "layout:column",
      "children": [{
        "_:component": "layout:text",
        "properties": {
          "text": "r"
        }
      }],
      "properties": {
        "height": 150.0,
        "width": 100.0,
        "backgroundColor": "#FF0000"
      }
    },
    {
      "_:component": "layout:column",
      "children": [{
        "_:component": "layout:text",
        "properties": {
          "text": "g"
        }
      }],
      "properties": {
        "height": 150.0,
        "width": 100.0,
        "backgroundColor": "#00FF00"
      }
    },
    {
      "_:component": "layout:column",
      "children": [{
        "_:component": "layout:text",
        "properties": {
          "text": "b"
        }
      }],
      "properties": {
        "height": 150.0,
        "width": 100.0,
        "backgroundColor": "#0000FF"
      }
    }],
  "properties": {
    "width": 150.0,
    "backgroundColor": "#CCCCCCFF"
  }
}"""

class MainActivity : ComponentActivity() {
    private val nimbus = Nimbus(
        baseUrl = BASE_URL,
        ui = listOf(layoutUI, materialComponents),
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
                        NimbusNavigator(JSON_LAZY)
                    }
                }
            }
        }
    }
}



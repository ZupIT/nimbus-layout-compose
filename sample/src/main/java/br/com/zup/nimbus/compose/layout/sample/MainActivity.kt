/*
 * Copyright 2023 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.zup.nimbus.compose.layout.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import br.com.zup.nimbus.compose.Nimbus
import br.com.zup.nimbus.compose.NimbusNavigator
import br.com.zup.nimbus.compose.ProvideNimbus
import br.com.zup.nimbus.compose.layout.extensions.imageProvider
import br.com.zup.nimbus.compose.layout.layoutUI
import br.com.zup.nimbus.compose.layout.sample.components.CustomError
import br.com.zup.nimbus.compose.layout.sample.components.materialComponents
import br.com.zup.nimbus.compose.layout.sample.theme.AppTheme

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
                    ProvideNimbus(nimbus.imageProvider(LocalImageProvider())) {
                        NimbusNavigator(JSON_LAZY)
                    }
                }
            }
        }
    }
}

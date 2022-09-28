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
  "_:component":"layout:screen",
  "state":{
    "id":"products",
    "value":{
      "isLoading":true,
      "data":[
        
      ]
    }
  },
  "children":[
    {
      "_:component":"layout:lifecycle",
      "children":[
        {
          "_:component":"layout:column",
          "children":[
            {
              "_:component":"if",
              "children":[
                {
                  "_:component":"then",
                  "children":[
                    {
                      "_:component":"layout:column",
                      "children":[
                        {
                          "_:component":"layout:text",
                          "properties":{
                            "text": "Loading..."
                          }
                        }
                      ],
                      "properties":{
                        "width":"expand",
                        "height":"expand",
                        "mainAxisAlignment":"center",
                        "crossAxisAlignment":"center"
                      }
                    }
                  ]
                },
                {
                  "_:component":"else",
                  "children":[
                    {
                      "_:component":"layout:scrollView",
                      "children":[
                        {
                          "_:component":"forEach",
                          "children":[
                            {
                              "_:component":"layout:column",
                              "children":[
                                {
                                  "_:component":"layout:text",
                                  "properties":{
                                    "text":"@{product.description}"
                                  }
                                }
                              ],
                              "properties":{
                                "marginBottom":20
                              }
                            }
                          ],
                          "properties":{
                            "iteratorName":"product",
                            "items":"@{products.data}"
                          }
                        }
                      ]
                    }
                  ]
                }
              ],
              "properties":{
                "condition":"@{products.isLoading}"
              }
            }
          ],
          "properties":{
            "backgroundColor":"#EEEEEE"
          }
        }
      ],
      "properties":{
        "onInit":[
          {
            "_:action":"sendRequest",
            "properties":{
              "onError":[
                {
                  "_:action":"log",
                  "properties":{
                    "message":"@{onError.message}",
                    "level":"Error"
                  }
                }
              ],
              "onSuccess":[
                {
                  "_:action":"setState",
                  "properties":{
                    "path":"products.data",
                    "value":"@{onSuccess.data}"
                  }
                }
              ],
              "onFinish":[
                {
                  "_:action":"setState",
                  "properties":{
                    "path":"products.isLoading",
                    "value":false
                  }
                }
              ],
              "url":"http://10.0.2.2:3000/data/products"
            }
          }
        ]
      }
    }
  ],
  "properties":{
    "title":"Products"
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
                    ProvideNimbus(nimbus) {
                        NimbusNavigator(JSON)
                    }
                }
            }
        }
    }
}



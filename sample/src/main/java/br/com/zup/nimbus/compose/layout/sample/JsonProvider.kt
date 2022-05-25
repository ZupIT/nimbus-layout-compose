package br.com.zup.nimbus.compose.layout.sample

const val TOUCHABLE_JSON = """{
  "_:component": "layout:container",
  "children": [
  {
      "_:component": "layout:touchable",
      "children": [{
          "_:component": "material:text",
          "properties": {
            "text": "Go to screen 2"
          }
        }],
          "properties": {
            "onPress": [{
              "_:action": "push",
              "properties": {
                "url": "/screen2.json"
              }
            }],
            "accessibility" : {
                "label": "This is a clickable element",
                "isHeader": true
            }
          }
  }]
}"""

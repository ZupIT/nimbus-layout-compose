package br.com.zup.nimbus.compose.layout.sample

const val TOUCHABLE_JSON = """{
  "_:component": "layout:container",
  "children": [
  {
      "_:component": "layout:touchable",
      "children": [{
          "_:component": "material:text",
          "properties": {
            "text": "Go to screen two"
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
  },
  {
      "_:component": "layout:touchable",
      "children": [{
          "_:component": "material:text",
          "properties": {
            "text": "Go to screen two. Only Label."
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
                "label": "This is a clickable element. Not Title."            
            }
          }
  },
  {
      "_:component": "layout:touchable",
      "children": [{
          "_:component": "material:text",
          "properties": {
            "text": "Go to screen two. Default accessibility."
          }
        }],
          "properties": {
            "onPress": [{
              "_:action": "push",
              "properties": {
                "url": "/screen2.json"
              }
            }]
          }
  }]
}"""

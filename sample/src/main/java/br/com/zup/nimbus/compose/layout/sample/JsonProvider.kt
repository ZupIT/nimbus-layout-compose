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

const val ROW_JSON = """{
  "_:component": "layout:row",
  "children": [
    {
      "_:component": "layout:row",
      "children": [{
        "_:component": "material:text",
        "properties": {
          "text": "r"
        }
      }],
      "properties": {
        "flex":2,
        "backgroundColor": "#ff0000"
      }
    },
    {
      "_:component": "layout:row",
      "children": [{
        "_:component": "material:text",
        "properties": {
          "text": "g"
        }
      }],
      "properties": {
        "flex":1,
        "backgroundColor": "#00ff00"
      }
    },
    {
      "_:component": "layout:row",
      "children": [{
        "_:component": "material:text",
        "properties": {
          "text": "b"
        }
      }],
      "properties": {
        "flex":1,
        "backgroundColor": "#0000ff"
      }
    }]
}"""

const val COLUMN_JSON = """{
  "_:component": "layout:column",
  "children": [
    {
      "_:component": "layout:column",
      "children": [{
        "_:component": "material:text",
        "properties": {
          "text": "r"
        }
      }],
      "properties": {
        "flex":2,
        "backgroundColor": "#ff0000"
      }
    },
    {
      "_:component": "layout:column",
      "children": [{
        "_:component": "material:text",
        "properties": {
          "text": "g"
        }
      }],
      "properties": {
        "flex":1,
        "backgroundColor": "#00ff00"
      }
    },
    {
      "_:component": "layout:column",
      "children": [{
        "_:component": "material:text",
        "properties": {
          "text": "b"
        }
      }],
      "properties": {
        "flex":1,
        "backgroundColor": "#0000ff"
      }
    }],
      "properties": {
        "height": 150.0
      }
}"""

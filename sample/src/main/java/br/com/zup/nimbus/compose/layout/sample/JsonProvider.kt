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
//1. Row: 3 elementos de tamanhos iguais que ocupam todo o espaço disponível (flex: 1).
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
        "flex":1,
        "backgroundColor": "#ff0000",
        "height": 50.0
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
        "backgroundColor": "#00ff00",
        "height": 50.0
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
        "backgroundColor": "#0000ff",
        "height": 50.0
      }
    }],
      "properties": {
        "backgroundColor": "#FFCCCCCC"      
      }
}"""
//2.Igual ao anterior, mas com margin-left de 10 entre cada item.
const val ROW_MARGIN_JSON = """{
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
        "flex":1,
        "backgroundColor": "#ff0000",
        "height": 50.0,
        "marginStart": 10
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
        "backgroundColor": "#00ff00",
        "height": 50.0,
        "marginStart": 10
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
        "backgroundColor": "#0000ff",
        "height": 50.0,
        "marginStart": 10
      }
    }],
      "properties": {
        "backgroundColor": "#FFCCCCCC"      
      }
}"""

//3.Igual ao anterior, mas com padding-left de 10 entre cada item.
const val ROW_PADDING_JSON = """{
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
        "flex":1,
        "backgroundColor": "#ff0000",
        "height": 50.0,
        "paddingStart": 10
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
        "backgroundColor": "#00ff00",
        "height": 50.0,
        "paddingStart": 10
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
        "backgroundColor": "#0000ff",
        "height": 50.0,
        "paddingStart": 10
      }
    }],
      "properties": {
        "backgroundColor": "#FFCCCCCC"      
      }
}"""

//4. Igual ao primeiro, mas o primeiro ocupa o dobro de espaço que os demais (flex 2).
const val ROW_FLEX_2_JSON = """{
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
        "backgroundColor": "#ff0000",
        "height": 50.0
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
        "backgroundColor": "#00ff00",
        "height": 50.0
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
        "backgroundColor": "#0000ff",
        "height": 50.0
      }
    }],
      "properties": {
        "backgroundColor": "#FFCCCCCC"      
      }
}"""

//5. Igual ao anterior, mas o primeiro tem margin top e bottom de 10 e o último padding e bottom de 10.
const val ROW_MARGIN_TOP_BOTTOM_JSON = """{
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
        "backgroundColor": "#ff0000",
        "height": 50.0,
        "marginTop": 10,
        "marginBottom": 10
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
        "backgroundColor": "#00ff00",
        "height": 50.0
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
        "backgroundColor": "#0000ff",
        "height": 50.0,
        "paddingTop": 10,
        "paddingBottom": 10
      }
    }],
      "properties": {
        "backgroundColor": "#FFCCCCCC"      
      }
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
        "flex":1,
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
        "height": 150.0,
        "backgroundColor": "#FFCCCCCC"      
      }
}"""

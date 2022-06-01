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
const val EXAMPLE_1 = """{
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
        "backgroundColor": "#FF0000",
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
        "backgroundColor": "#00FF00",
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
        "backgroundColor": "#0000FF",
        "height": 50.0
      }
    }],
      "properties": {
        "backgroundColor": "#FFCCCCCC"      
      }
}"""
//2.Igual ao anterior, mas com margin-left de 10 entre cada item.
const val EXAMPLE_2 = """{
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
        "backgroundColor": "#FF0000",
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
        "backgroundColor": "#00FF00",
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
        "backgroundColor": "#0000FF",
        "height": 50.0,
        "marginStart": 10
      }
    }],
      "properties": {
        "backgroundColor": "#FFCCCCCC"      
      }
}"""

//3.Igual ao anterior, mas com padding-left de 10 entre cada item.
const val EXAMPLE_3 = """{
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
        "backgroundColor": "#FF0000",
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
        "backgroundColor": "#00FF00",
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
        "backgroundColor": "#0000FF",
        "height": 50.0,
        "paddingStart": 10
      }
    }],
      "properties": {
        "backgroundColor": "#FFCCCCCC"      
      }
}"""

//4. Igual ao primeiro, mas o primeiro ocupa o dobro de espaço que os demais (flex 2).
const val EXAMPLE_4 = """{
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
        "backgroundColor": "#FF0000",
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
        "backgroundColor": "#00FF00",
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
        "backgroundColor": "#0000FF",
        "height": 50.0
      }
    }],
      "properties": {
        "backgroundColor": "#FFCCCCCC"      
      }
}"""

//5. Igual ao anterior, mas o primeiro tem margin top e bottom de 10 e o último padding e bottom de 10.
const val EXAMPLE_5 = """{
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
        "backgroundColor": "#FF0000",
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
        "backgroundColor": "#00FF00",
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
        "backgroundColor": "#0000FF",
        "height": 50.0,
        "paddingTop": 10,
        "paddingBottom": 10
      }
    }],
      "properties": {
        "backgroundColor": "#FFCCCCCC"      
      }
}"""

//6. Imagem com 100 de largura à esquerda de um componente que ocupa o resto do espaço disponível.
// Margem de 20 entre a imagem e o componente. O Retangulo deve ocupar a
// altura da imagem de maneira implícita.
const val EXAMPLE_6 = """{
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
        "backgroundColor": "#FF0000",
        "height": 100.0,
        "width": 100.0
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
        "backgroundColor": "#00FF00",
        "marginStart": 20.0
      }
    }],
      "properties": {
        "backgroundColor": "#FFCCCCCC",  
        "crossAxisAlignment": "stretch"
      }
}"""

//6.1 Imagem com 100 de largura à esquerda de um componente que ocupa o resto do espaço disponível.
// Margem de 20 entre a imagem e o componente. O Retangulo deve ocupar a
// altura da imagem de maneira implícita.
const val EXAMPLE_6_1 = """{
  "_:component": "layout:column",
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
        "backgroundColor": "#FF0000",
        "height": 100.0,
        "width": 100.0
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
        "backgroundColor": "#00FF00",
        "marginTop": 20.0
      }
    }],
      "properties": {
        "backgroundColor": "#FFCCCCCC",  
        "crossAxisAlignment": "stretch"
      }
}"""

//1. Row: 3 elementos de tamanhos iguais que ocupam todo o espaço disponível (flex: 1).
const val EXAMPLE_7_1 = """{
  "_:component": "layout:row",
  "children": [
    {
      "_:component": "layout:row",
      "children": [{
        "_:component": "material:text",
        "properties": {
          "text": "flex-end"
        }
      }],
      "properties": {
        "backgroundColor": "#0000FF",
        "height": 100.0,
        "width": 100.0
      }
    },
    {
      "_:component": "layout:row",
      "children": [{
        "_:component": "material:text",
        "properties": {
          "text": ""
        }
      }],
      "properties": {
        "backgroundColor": "#FF0000",
        "height": 100.0,
        "width": 100.0
      }
    }],
      "properties": {
        "backgroundColor": "#FFCCCCCC",   
        "mainAxisAlignment": "end",
        "height": 250.0,
        "width": 250.0
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
        "backgroundColor": "#FF0000"
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
        "backgroundColor": "#00FF00"
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
        "backgroundColor": "#0000FF"
      }
    }],
      "properties": {
        "height": 150.0,
        "backgroundColor": "#FFCCCCCC"      
      }
}"""

package br.com.zup.nimbus.compose.layout

val jsonFactory = mapOf(
    "1" to """{
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
        "backgroundColor": "#FF0000"
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
        "backgroundColor": "#00FF00"
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
        "backgroundColor": "#0000FF"
      }
    }]
}""",
   "2" to """{
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
        "backgroundColor": "#FF00FF",
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
)
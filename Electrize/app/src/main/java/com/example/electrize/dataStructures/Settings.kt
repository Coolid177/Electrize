package com.example.electrize.dataStructures

enum class theme(val value: Boolean){
    DARK(true),
    LIGHT(false)
}

class Settings(language: String, theme: theme) {
    var _language = language
    var _theme = theme
}
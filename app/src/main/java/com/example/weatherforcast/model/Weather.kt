package com.example.weatherforcast.model

data class Weather(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,//city
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: WeatherObject,
    val wind: Wind
)
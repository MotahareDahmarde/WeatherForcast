package com.example.weatherforcast.repository

import com.example.weatherforcast.data.DataOrException
import com.example.weatherforcast.model.Weather
import com.example.weatherforcast.model.WeatherObject
import com.example.weatherforcast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api:WeatherApi){
    suspend fun getWeather(cityQuery : String): DataOrException<Weather, Boolean, Exception>{
        val response = try{
            api.getWeather(query = cityQuery)
        }catch (e : Exception){
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}
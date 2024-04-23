package com.example.weatherforcast.screens.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforcast.data.DataOrException
import com.example.weatherforcast.model.Weather
import com.example.weatherforcast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository)
    :ViewModel() {
        val data : MutableState<DataOrException<Weather, Boolean, Exception>> =
            mutableStateOf(DataOrException(null, true, Exception("")))

    suspend fun getWeatherData(city:String): DataOrException<Weather,Boolean,Exception>{
        return repository.getWeather(cityQuery = city)
    }
    /*
    init {
        loadWeather()
    }

    private fun loadWeather(){
        getWeather("Scattle")
    }

    private fun getWeather(city : String){
        viewModelScope.launch {
            if (city.isEmpty()) return@launch
            data.value.loading = true
            data.value = repository.getWeather(cityQuery = city)
            if (data.value.toString().isNotEmpty())
                data.value.loading = false
        }
    } */
    }
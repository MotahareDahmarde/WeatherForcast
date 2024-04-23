package com.example.weatherforcast.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.weatherforcast.data.DataOrException
import com.example.weatherforcast.model.Weather
import com.example.weatherforcast.util.formatDate
import com.example.weatherforcast.util.formatDecimal
import com.example.weatherforcast.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavController,
               mainViewModel: MainViewModel = hiltViewModel()){

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(initialValue =
    DataOrException(loading = true)){
        value = mainViewModel.getWeatherData(city = "Seatle")
    }.value

    if (weatherData.loading == true){
        CircularProgressIndicator()
    }
    else if (weatherData.data != null){
        MainScaffold(weather = weatherData.data!!, navController)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(weather : Weather, navController: NavController){
    Scaffold(topBar = {
        WeatherAppBar(title = weather.name + ",${weather.sys.country}",
            icon = Icons.Default.ArrowBack,
            navController = navController,
            elevation = 5.dp){

        }
    }) {
        MainContent(data = weather)
    }
}

@Composable
fun MainContent(data: Weather) {

    val imageUrl = "https://openweathermap.org/img/wn/${data.weather.icon}.png"
    Column (modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = formatDate(data.dt), //Wed,Nov 30
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp))

        Surface (modifier = Modifier
            .padding(4.dp)
            .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ){
            Column (verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ){
                //Image
                WeatheStateImage(imageUrl = imageUrl)

                Text(text = formatDecimal(data.main.temp), style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold)
                Text(text = data.weather.main, fontStyle = FontStyle.Italic)
            }
        }
    }
}

@Composable
fun WeatheStateImage(imageUrl: String) {
    Image(painter = rememberImagePainter(imageUrl),
        contentDescription = "image icon",
        modifier = Modifier.size(80.dp))
}

package com.example.jetweatherapp.screens.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetweatherapp.navigation.WeatherScreens
import com.example.jetweatherapp.widgets.WeatherAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController){
    Scaffold(topBar = {
        WeatherAppBar(navController = navController , icon = Icons.Default.ArrowBack , title = "Search",isMainScreen = false){
            navController.popBackStack()
        }
    }) {
        Surface() {
            Column(verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally) {
            SearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)){city->
               navController.navigate(WeatherScreens.MainScreen.name + "/$city")
            }
            }
        }
        
    }

}



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier,onSearch : (String) -> Unit = {}){
Column() {
    val searchQueryState = rememberSaveable {
        mutableStateOf("")
    }
    val keyBoardController = LocalSoftwareKeyboardController.current
    val isValid = remember( searchQueryState.value ){
        searchQueryState.value.trim().isNotBlank()
    }
    CommonTextField(valueState  = searchQueryState , placeHolder = "Seattle" , onAction = KeyboardActions {
        if (!isValid) return@KeyboardActions
        onSearch(searchQueryState.value.trim())
        searchQueryState.value = ""
        keyBoardController?.hide()
    })
}
}

@Composable
fun CommonTextField(
    valueState: MutableState<String>,
    placeHolder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = placeHolder) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = onAction ,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor =  Color.Blue ,
            cursorColor = Color.Black ,

        ),
        shape = RoundedCornerShape(15.dp),
        modifier =  Modifier.padding(start = 10.dp , end = 10.dp).fillMaxWidth()



    )
}

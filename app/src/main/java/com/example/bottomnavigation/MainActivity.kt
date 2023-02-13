package com.example.bottomnavigation

import android.annotation.SuppressLint
import android.icu.text.IDNA.Info
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation.ui.theme.BottomNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BottomNavigationApp()
                }
            }
        }
    }
}

@Composable
fun BottomNavigationApp() {
    val items = listOf(
        TabItem("Home", Icons.Filled.Home, route = "Home"),
        TabItem("Favourite", Icons.Filled.Favorite, route = "Favourites"),
        TabItem("Info", Icons.Filled.Info, route = "Info"),
    )
    BasicLayout(items)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BasicLayout(items: List<TabItem>) {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopAppBar { Text(text = "My app")

        }},
        content = { MyNavController(navController = navController)},
        bottomBar = {MyBottomNavigation(items, navController)}

    )
}

@Composable
fun MyBottomNavigation(items: List<TabItem>, navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    BottomNavigation {
        items.forEachIndexed{index,item ->
            BottomNavigationItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)
                          },
                icon = {Icon(item.icon, contentDescription = null)},
                label = { Text(item.label)}
            )
        }

    }

}

@Composable
fun MainScreen() {
    Text(text = "Main Screen")
}

@Composable
fun FavouritesScreen() {
    Text(text = "Favourites Screen")
}

@Composable
fun InfoScreen() {
    Text(text = "Info Screen")
}

@Composable
fun MyNavController(navController: NavHostController) {

    NavHost(
        navController= navController,
        startDestination = "Home"
    ) {
        composable(route = "Home") {
            MainScreen()
        }
        composable(route = "Favourites") {
            FavouritesScreen()
        }
        composable(route = "Info") {
            InfoScreen()
        }
    }
}
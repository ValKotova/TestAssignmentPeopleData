package com.valkotova.testassignmentpeopledata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.valkotova.testassignmentpeopledata.ui.fragments.PeopleList.PeoplesListFragment
import com.valkotova.testassignmentpeopledata.ui.fragments.UserDetail.UserDetails
import com.valkotova.testassignmentpeopledata.ui.theme.TestAssignmentPeopleDataTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAssignmentPeopleDataTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "list",
                    ) {
                        composable("list") {
                            PeoplesListFragment(navController)
                        }
                        composable("user/{user}") {
                            UserDetails(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
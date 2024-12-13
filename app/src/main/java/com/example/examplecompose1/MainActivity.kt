package com.example.examplecompose1
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "login")
            {
                composable("login") { LoginScreen(navController) }
                composable("dashboard") { DashboardScreen(navController) }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var showError by remember { mutableStateOf(false) }
    var showEmptyError by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Enter Login Credentials", fontSize = 30.sp, color = Color.Blue)
        Spacer(modifier = Modifier.height(26.dp))
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()

        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                showError = false
                showEmptyError = false
                if (username.isEmpty()||password.isEmpty()) {
                    showEmptyError = true // Show error message
                } else if (username == "admin" && password == "password") {
                    navController.navigate("dashboard")
                    "Login Successful"
                } else {
                    showError = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login", fontSize = 20.sp)
        }
        if(showError==false && showEmptyError == false) {
            Text(text = "", fontSize = 20.sp, color = Color.Red)
        } else if (showError == true){
            Text(text= "Invalid credentials", fontSize = 20.sp, color = Color.Red)
        } else if (showEmptyError == true) {
            Text(text= "Fields cannot be empty", fontSize = 20.sp, color = Color.Red)
        }
    }
}



@Composable
fun DashboardScreen(navController: NavController) {
    Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Welcome to Dashboard", fontSize = 30.sp, color = Color.Blue)
        Spacer(modifier = Modifier.height(26.dp))
        Button(
            onClick = {
                navController.navigate("login")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}

@Preview(showBackground = true, name = "Login Screen Preview")
@Composable
fun LoginScreenPreview() {
    val mockNavController = rememberNavController() // Mock NavController for preview
    LoginScreen(navController = mockNavController)
}


@Preview(showBackground = true, name = "Dashboard Screen Preview")
@Composable
fun DashboardScreenPreview() {
    val mockNavController = rememberNavController() // Mock NavController for preview
    DashboardScreen(navController = mockNavController)
}




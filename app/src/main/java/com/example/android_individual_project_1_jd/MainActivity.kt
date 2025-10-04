package com.example.android_individual_project_1_jd
// All the imports required for the app.

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_individual_project_1_jd.ui.theme.Android_Individual_Project_1_JDTheme

// Main activity for the application
// The main activity for the app will start off at the splash screen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_Individual_Project_1_JDTheme {
                MyApp(modifier = Modifier.fillMaxSize())
                
            }
        }
    }
}



// Composable function added to add a picture of the logo to the
// splash screen
@Composable
fun PlantPicture() {
    Image(painter = painterResource(id = R.drawable.plant),
          contentDescription = null  )
}

// Splash screen that shows up before the login and register page
@Composable
fun OnboardingScreen(
    // After you click continue you will go to the login screen
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Make the font larger
        Text("Long Island Plant and Tree Identifier",
            // Center the words on the splash screen
            textAlign = TextAlign.Center,

            // makes the font larger
            fontSize = 30.sp,

            // Make the font bold
            fontWeight = FontWeight.Bold)

        // Add a picture of the logo
        PlantPicture()

        // Adds a button
        Button(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .fillMaxWidth(),
            onClick = onContinueClicked,

        ) {
            Text("Continue")
        }
    }
}

// Function to display the splash screen
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    Android_Individual_Project_1_JDTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

// Function to display the login screen
@Composable
fun Login(nav: NavHostController, modifier: Modifier = Modifier) {
    // needed for the text boxes
    var text by remember { mutableStateOf("")}


    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        // Column for the login page
        Column(
            modifier = Modifier.fillMaxSize(),

            // pushes all the horizontal alignment of the textboxes into the center
            horizontalAlignment = Alignment.CenterHorizontally,

            // Vertical padding between all the elements in the column
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            PlantPicture()

            Text("Sign in",
                // Center the words on the splash screen
                textAlign = TextAlign.Center,

                // makes the font larger
                fontSize = 30.sp,

                // Make the font bold
                fontWeight = FontWeight.Bold)

            TextField(
                value = text,
                onValueChange = { text = it},
                label = { Text("Username or Email")}
            )

            TextField(
                value = text,
                onValueChange = { text = it},
                label = { Text("Password")}
            )
            // Button for login
            ElevatedButton(
                onClick = {  }
            ) {
                Text("Login")
            }
            // Button to go to the register account page
            ElevatedButton(
                onClick = { nav.navigate("register") }
            ) {
                Text("Don't have an account? Register")
            }

        }

    }
}

@Preview
@Composable
fun MyAppPreview() {
    Android_Individual_Project_1_JDTheme {
        MyApp(Modifier.fillMaxSize())
    }
}

@Composable
fun RegisterScreen(nav: NavHostController){
    var text by remember { mutableStateOf("")}

    // column for login page
    Column(
        modifier = Modifier.fillMaxSize(),

        // pushes all the horizontal alignment of the textboxes into the center
        horizontalAlignment = Alignment.CenterHorizontally,

        // Vertical padding between all the elements in the column
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        PlantPicture()

        Text("Sign up",
            // Center the words on the splash screen
            textAlign = TextAlign.Center,
            // makes the font larger
            fontSize = 30.sp,
            // Make the font bold
            fontWeight = FontWeight.Bold)

        TextField(
            value = text,
            onValueChange = { text = it},
            label = { Text("First Name")}
        )

        TextField(
            value = text,
            onValueChange = { text = it},
            label = { Text("Last Name")}
        )

        TextField(
            value = text,
            onValueChange = { text = it},
            label = { Text("Date of Birth")}
        )

        TextField(
            value = text,
            onValueChange = { text = it},
            label = { Text("Email")}
        )
        TextField(
            value = text,
            onValueChange = { text = it},
            label = { Text("Password")}
        )

        // Button to be able to create your account
        ElevatedButton(
            onClick = { /* Implement this later */ }
        ) {
            Text("Create account")
        }

        // Button to go back to the log in screen if you already have an account
        ElevatedButton(
            onClick = { nav.popBackStack() }
        ) {
            Text("Already have an account? Login")
        }

    }
}


// Main app function to allow for navigation
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    // Needed to create a navigation controller
    val nav = rememberNavController()

    Surface(modifier) {
        NavHost(
            navController = nav,
            // makes the app start at the login screen
            startDestination = "onboarding"
        ) {
            composable("onboarding") {

                /*
                If the button on the splash screen is clicked,
                it will go to the login screen.

                If the button on the login screen is clicked, it will go
                to the register screen.

                If the button on the register screen is clicked, it will go onto the
                login screen.
                 */

                OnboardingScreen(
                    onContinueClicked = {
                        nav.navigate("login") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    }
                )
            }
            // Route for the login screen
            composable("login") { Login(nav) }

            // route for the register screen
            composable("register") { RegisterScreen(nav) }
        }
    }
}

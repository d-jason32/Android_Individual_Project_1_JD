package com.example.android_individual_project_1_jd
// All the imports required for the app.

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
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
import androidx.compose.ui.graphics.Color
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

    Surface(
        color = Color.LightGray,
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Make the font larger
            Text(
                "Long Island Plant and Tree Identifier",
                // Center the words on the splash screen
                textAlign = TextAlign.Center,

                // makes the font larger
                fontSize = 30.sp,

                // Make the font bold
                fontWeight = FontWeight.Bold
            )

            // Add a picture of the logo
            PlantPicture()

            // Adds a button
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                ),
                modifier = Modifier
                    .padding(vertical = 24.dp),
                onClick = onContinueClicked,

                ) {
                Text("Continue")
            }
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
    val context = LocalContext.current
    // needed for the text boxes
    var email by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}

    Surface(
        color = Color.LightGray,
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
                value = email,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { email = it},
                label = { Text("Email")}
            )

            TextField(
                value = password,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { password = it},
                label = { Text("Password")}
            )
            // Button for login
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                ),
                onClick = {
                    // All fields must be filled in
                    if (email.isBlank() || password.isBlank()){
                        Toast.makeText(context, "Fill in all the fields", Toast.LENGTH_SHORT).show()
                    }
                    // Password must be greater than 6 and less than 30 characters
                    else if (password.length < 6 || password.length > 30){
                        Toast.makeText(context, "Password must be greater than 6 and less than 30 characters.", Toast.LENGTH_SHORT).show()
                    }
                    // Email must be valid using regex.
                    else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex())){
                        Toast.makeText(context, "Please enter a valid email", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(context, "Successful sign on", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("Login")
            }
            // Button to go to the register account page
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                ),
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
    val context = LocalContext.current
    // needed for the text boxes
    var firstName by remember { mutableStateOf("")}
    var lastName by remember { mutableStateOf("")}
    var dob by remember { mutableStateOf("")}
    var email by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}

    Surface(
        color = Color.LightGray,
    ) {


        // column for login page
        Column(
            // Add scrolling state
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),

            // pushes all the horizontal alignment of the textboxes into the center
            horizontalAlignment = Alignment.CenterHorizontally,

            // Vertical padding between all the elements in the column
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            PlantPicture()

            Text(
                "Sign up",
                // Center the words on the splash screen
                textAlign = TextAlign.Center,
                // makes the font larger
                fontSize = 30.sp,

                // Make the font bold
                fontWeight = FontWeight.Bold
            )

            TextField(
                value = firstName,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { firstName = it },
                label = { Text("First Name") }
            )

            TextField(
                value = lastName,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { lastName = it },
                label = { Text("Last Name") }
            )

            TextField(
                value = dob,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { dob = it },
                label = { Text("Date of Birth") }
            )

            TextField(
                value = email,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { email = it },
                label = { Text("Email") }
            )
            TextField(
                value = password,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { password = it },
                label = { Text("Password") }
            )

            // Button to be able to create your account
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                ),
                onClick = {
                    if (email.isBlank() || password.isBlank() || dob.isBlank() || firstName.isBlank() || lastName.isBlank()) {
                        Toast.makeText(context, "Fill in all the fields", Toast.LENGTH_SHORT).show()
                    }
                    // Password must be greater than 6 and less than 30 characters
                    else if (password.length < 6 || password.length > 30) {
                        Toast.makeText(
                            context,
                            "Password must be greater than 6 and less than 30 characters.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    // Email must be valid using regex.
                    else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex())) {
                        Toast.makeText(context, "Please enter a valid email", Toast.LENGTH_SHORT)
                            .show()
                    }

                    // First name must be greater than 3 and less than 30 characters
                    else if (firstName.length < 3 || firstName.length > 30) {
                        Toast.makeText(
                            context,
                            "First name must be greater than 3 and less than 30 characters.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    // Last name must be greater than 3 and less than 30 characters
                    else if (lastName.length < 3 || lastName.length > 30) {
                        Toast.makeText(
                            context,
                            "Last name must be greater than 3 and less than 30 characters.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    // Date of birth must be valid using regex.
                    else if (!dob.matches("^(0[1-9]|1[0-2])/([0][1-9]|[12][0-9]|3[01])/\\d{4}$".toRegex())) {
                        Toast.makeText(
                            context,
                            "Please enter a valid date of birth MM/DD/YYYY",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Successful account creation, go back to log in!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ) {
                Text("Create account")
            }

            // Button to go back to the log in screen if you already have an account
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                ),
                onClick = { nav.popBackStack() }
            ) {
                Text("Already have an account? Login")
            }

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

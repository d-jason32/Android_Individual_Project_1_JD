package com.example.android_individual_project_1_jd

import android.R.attr.padding
import android.R.attr.text
import android.R.id.bold
import android.graphics.Picture
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.android_individual_project_1_jd.ui.theme.Android_Individual_Project_1_JDTheme

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

@Composable
fun MyApp(modifier: Modifier = Modifier) {

    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Login()
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


@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    Android_Individual_Project_1_JDTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Composable
fun Login(modifier: Modifier = Modifier) {
    // needed for the text
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
                label = { Text("Username")}
            )

            TextField(
                value = text,
                onValueChange = { text = it},
                label = { Text("Password")}
            )

            ElevatedButton(
                onClick = {  }
            ) {
                Text("Login")
            }

            ElevatedButton(
                onClick = {  }
            ) {
                Text("Don't have an account? Register")
            }

        }

    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    Android_Individual_Project_1_JDTheme {
        Login()
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
fun RegisterScreen(){
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

        ElevatedButton(
            onClick = {  }
        ) {
            Text("Register")
        }

        ElevatedButton(
            onClick = {  }
        ) {
            Text("Already have an account? Login")
        }

    }
}


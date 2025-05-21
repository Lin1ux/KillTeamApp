package com.example.killteam.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.killteam.firebase.SignInState
import com.example.killteam.firebase.UserData

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
)
{
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError)
    {
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize().padding(15.dp),
        contentAlignment = Alignment.Center
    )
    {
        Button(onClick = onSignInClick)
        {
            Text("Sign in")
        }
    }
}

@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOutClick: () -> Unit
)
{
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        if(userData?.profilePictureUrl != null)
        {
            AsyncImage(
                model = userData.profilePictureUrl,
                contentDescription = "Profile picture",
                modifier = Modifier.size(150.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
        if(userData?.username != null)
        {
            Text(text = userData.username,style = TextStyle(fontSize = 30.sp),  textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(15.dp))
        }
        Button(onClick = onSignOutClick)
        {
            Text("Sign Out")
        }
    }

}
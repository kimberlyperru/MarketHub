package com.perru.markethub.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.OptIn
import androidx.navigation.compose.rememberNavController
import com.perru.markethub.R
import com.perru.markethub.navigation.ROUT_HOME
import com.perru.markethub.navigation.ROUT_REGISTER

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    Column(
        Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally


    ) {
        Image(
            painter = painterResource(id = R.drawable.log),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Welcome back",
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.SemiBold,
            color = Color.Red,
            letterSpacing = 0.5.sp,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(40.dp))

        //variables
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        //email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.width(350.dp),
            leadingIcon = {Icon(imageVector = Icons.Default.Email, contentDescription = "Email")},
            label = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.Gray,
                unfocusedLeadingIconColor = Color.Red


            )
        )
        Spacer(modifier = Modifier.height(20.dp))

        //password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.width(350.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password") },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.Gray,
                unfocusedLeadingIconColor = Color.Red
            )
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { navController.navigate(ROUT_HOME) },
            modifier = Modifier.width(350.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "Login", color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Don't have an account? Register",
            modifier = Modifier.clickable { navController.navigate(ROUT_REGISTER) },
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}


@Preview(showBackground = true)

@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}
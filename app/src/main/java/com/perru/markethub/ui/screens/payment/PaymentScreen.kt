package com.perru.markethub.ui.screens.payment


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.perru.markethub.ui.theme.newyellow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(@Suppress("UNUSED_PARAMETER") navController: NavController){

    val mContext = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {

        //TopAppBar

        TopAppBar(
            title = { Text(text = "Payment") },
            navigationIcon = {
                IconButton(onClick = {})  {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
            },

            actions = {

                IconButton(onClick = {})  {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Cart"
                    )
                }


                IconButton(onClick = {})  {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications"
                    )
                }


            },

            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = newyellow,
                navigationIconContentColor = Color.White,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White
            )

        )


        //End of TopAppbar

        Spacer(modifier = Modifier.height(20.dp))

        //STK
        Button(
            onClick = {
                val simToolKitLaunchIntent =
                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                simToolKitLaunchIntent?.let { mContext.startActivity(it) }

            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(newyellow),
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(text = "Mpesa")
        }

        Spacer(modifier = Modifier.height(20.dp))

        //Call
        Button(
            onClick = {
                val callIntent= Intent(Intent.ACTION_DIAL)
                callIntent.data="tel:0725766883".toUri()
                mContext.startActivity(callIntent)
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(newyellow),
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(text = "Call")
        }

        Spacer(modifier = Modifier.height(20.dp))

        //SMS
        Button(
            onClick = {
                val smsIntent=Intent(Intent.ACTION_SENDTO)
                smsIntent.data="sms to:0725766883".toUri()
                smsIntent.putExtra("sms_body","Hello ,how was your day?")
                mContext.startActivity(smsIntent)
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(newyellow),
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(text = "Send Message")
        }

        Spacer(modifier = Modifier.height(20.dp))

        //Email
        Button(
            onClick = {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("perru@gmail.com"))
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "subject")
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello, this is the email body")
                mContext.startActivity(shareIntent)
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(newyellow),
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(text = "Email Us")
        }

        Spacer(modifier = Modifier.height(20.dp))

        //SHARE
        Button(
            onClick = {
                val shareIntent=Intent(Intent.ACTION_SEND)
                shareIntent.type="text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://github.com/kimberlyperru")
                mContext.startActivity(Intent.createChooser(shareIntent, "Share"))
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(newyellow),
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(text = "Share")
        }

        Spacer(modifier = Modifier.height(20.dp))

        //CAMERA
        Button(
            onClick = {
                val cameraIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (cameraIntent.resolveActivity(mContext.packageManager)!=null){
                    mContext.startActivity(cameraIntent)
                }else{
                    println("Camera app is not available")
                }
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(newyellow),
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(text = "Camera")
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

// Separate functions for button logic

fun mpesa(context: Context) {
    val simToolKitLaunchIntent = context.packageManager.getLaunchIntentForPackage("com.android.stk")
    simToolKitLaunchIntent?.let { context.startActivity(it) }
}

fun call(context: Context) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:0700000000")
    context.startActivity(intent)
}

fun sms(context: Context) {
    val uri = Uri.parse("smsto:0700000000")
    val intent = Intent(Intent.ACTION_SENDTO, uri)
    intent.putExtra("sms_body", "Hello, I have a query...")
    context.startActivity(intent)
}

fun email(context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:support@markethub.com")
        putExtra(Intent.EXTRA_SUBJECT, "Support Inquiry")
    }
    context.startActivity(intent)
}

fun share(context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "Check out MarketHub!")
    }
    context.startActivity(Intent.createChooser(intent, "Share via"))
}

fun camera(context: Context) {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    context.startActivity(intent)
}


@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview(){
    PaymentScreen(rememberNavController())
}
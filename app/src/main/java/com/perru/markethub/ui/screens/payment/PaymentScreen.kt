package com.perru.markethub.ui.screens.payment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// Premium red color palette implementation
val MainRed = Color(0xFFD32F2F)
val BgLight = Color(0xFFFBF9FA)
val TextDark = Color(0xFF1A1A2E)
val TextGray = Color(0xFF7D7D8E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController) {
    val mContext = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Checkout", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = TextDark,
                    navigationIconContentColor = TextDark
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BgLight)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // SECTION 1: Order Summary Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(text = "Order Summary", fontSize = 14.sp, color = TextGray, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Total Payable Amount", fontSize = 16.sp, color = TextDark, fontWeight = FontWeight.SemiBold)
                        Text(text = "KES 4,500.00", fontSize = 22.sp, color = MainRed, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // SECTION 2: Payment Selector Option Groups
            Text(
                text = "Select Payment Method",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            PaymentMethodTile(
                title = "M-Pesa Express (STK)",
                subtitle = "Pay directly via Sim Toolkit",
                icon = Icons.Default.PhoneAndroid,
                onClick = { mpesa(mContext) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            PaymentMethodTile(
                title = "Credit or Debit Card",
                subtitle = "Visa, Mastercard, Google Pay",
                icon = Icons.Default.CreditCard,
                onClick = { /* Handle Payment Gateways integration */ }
            )

            Spacer(modifier = Modifier.height(28.dp))

            // SECTION 3: Bottom Order Assistance Tray (The intents clean relocation)
            Text(
                text = "Need assistance with your order?",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextGray,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SupportIconAction(icon = Icons.Default.Call, label = "Call", onClick = { call(mContext) })
                SupportIconAction(icon = Icons.Default.MailOutline, label = "Email", onClick = { email(mContext) })
                SupportIconAction(icon = Icons.Default.Share, label = "Share", onClick = { share(mContext) })
                SupportIconAction(icon = Icons.Default.PhotoCamera, label = "Receipt", onClick = { camera(mContext) })
            }
        }
    }
}

@Composable
fun PaymentMethodTile(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(MainRed.copy(alpha = 0.08f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = title, tint = MainRed, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextDark)
                Text(text = subtitle, fontSize = 12.sp, color = TextGray)
            }
            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Select", tint = MainRed.copy(alpha = 0.3f))
        }
    }
}

@Composable
fun SupportIconAction(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = label, tint = TextDark, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = TextDark)
    }
}

// System Intent Logic Wrapper implementations

fun mpesa(context: Context) {
    val simToolKitLaunchIntent = context.packageManager.getLaunchIntentForPackage("com.android.stk")
    simToolKitLaunchIntent?.let { context.startActivity(it) }
}

fun call(context: Context) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = "tel:0725766883".toUri()
    }
    context.startActivity(intent)
}

fun email(context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_EMAIL, arrayOf("perru@gmail.com"))
        putExtra(Intent.EXTRA_SUBJECT, "MarketHub Payment Inquiry")
        putExtra(Intent.EXTRA_TEXT, "Hello, writing to verify my receipt status...")
    }
    context.startActivity(intent)
}

fun share(context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "https://github.com/kimberlyperru")
    }
    context.startActivity(Intent.createChooser(intent, "Share Store Link"))
}

fun camera(context: Context) {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen(rememberNavController())
}
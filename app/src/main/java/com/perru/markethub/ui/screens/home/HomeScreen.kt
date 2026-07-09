package com.perru.markethub.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.perru.markethub.R
import com.perru.markethub.navigation.ROUT_ABOUT
import com.perru.markethub.navigation.ROUT_PAYMENT
import com.perru.markethub.navigation.ROUT_SERVICES

// Data models for structural sanity
data class Product(val id: Int, val name: String, val price: String, val imageRes: Int)
data class Category(val name: String, val iconRes: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    // Mock Data for modern feed (Replace with your actual viewmodel data later)
    val banners = listOf(R.drawable.heels, R.drawable.flat, R.drawable.open)
    val categories = listOf(
        Category("Heels", R.drawable.heels),
        Category("Flats", R.drawable.flat),
        Category("Open Shoes", R.drawable.open),
        Category("Kitten", R.drawable.kitten),
        Category("Boots", R.drawable.heels),
        Category("Sneakers", R.drawable.flat)
    )
    val products = listOf(
        Product(1, "Luxury Red Heels", "$120.00", R.drawable.heels),
        Product(2, "Casual Summer Flats", "$45.00", R.drawable.flat),
        Product(3, "Elegant Open Sandals", "$60.00", R.drawable.open),
        Product(4, "Classic Kitten Heels", "$85.00", R.drawable.kitten),
        Product(5, "Premium Leather Boots", "$150.00", R.drawable.heels),
        Product(6, "Sporty Run Sneakers", "$95.00", R.drawable.flat)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "MarketHub",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate(ROUT_ABOUT) }) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "About")
                    }
                    IconButton(onClick = { navController.navigate(ROUT_SERVICES) }) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "Services")
                    }
                    IconButton(onClick = { navController.navigate(ROUT_PAYMENT) }) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color(0xFF1A1A1A),
                    actionIconContentColor = Color(0xFF1A1A1A)
                )
            )
        }
    ) { innerPadding ->

        // This is your master "NestedScrollView" setup
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FA)) // Subtle off-white modern backdrop
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {

            // SECTION 1: Promotional Banners (Horizontal Scroll)
            item {
                Text(
                    text = "Special Offers",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 12.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp)
                ) {
                    banners.forEach { banner ->
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .width(300.dp)
                                .height(160.dp)
                                .padding(end = 12.dp)
                        ) {
                            Image(
                                painter = painterResource(banner),
                                contentDescription = "Promo Banner",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }

            // SECTION 2: Trending Categories (Grid View)
            item {
                Text(
                    text = "Trending Categories",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 12.dp)
                )

                // Using a horizontal grid layout for smooth categories exploration
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(2),
                    modifier = Modifier
                        .height(180.dp)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(categories) { category ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                                .clickable { /* Filter Logic */ }
                                .padding(end = 16.dp)
                                .fillMaxHeight()
                        ) {
                            Image(
                                painter = painterResource(category.iconRes),
                                contentDescription = category.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(RoundedCornerShape(12.dp))
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = category.name,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            // SECTION 3: Vertical Infinite Feed Label
            item {
                Text(
                    text = "Explore Products",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 28.dp, bottom = 12.dp)
                )
            }

            // SECTION 4: Infinite Scroll Product Grid Items
            // Chunks product items into rows of 2 for a clean grid layout inside the LazyColumn
            val chunkedProducts = products.chunked(2)
            items(chunkedProducts) { pair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    for (product in pair) {
                        ProductGridItem(
                            product = product,
                            modifier = Modifier.weight(1f),
                            onProductClick = { /* Go to details */ }
                        )
                    }
                    if (pair.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun ProductGridItem(
    product: Product,
    modifier: Modifier = Modifier,
    onProductClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.clickable { onProductClick() }
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(product.imageRes),
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                )
                // Modern Heart/Favorite Overlap Icon
                IconButton(
                    onClick = { /* Favorite Action */ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(32.dp)
                        .background(Color.White.copy(alpha = 0.8f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.Black,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = product.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.price,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFFE53935) // Deep Red Accented Pricing
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
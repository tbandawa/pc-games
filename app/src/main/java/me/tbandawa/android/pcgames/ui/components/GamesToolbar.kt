package me.tbandawa.android.pcgames.ui.components

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesToolbar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior
) {

    TopAppBar(
        modifier = Modifier.background(color = Color.White),
        title = {
            Text(
                text = title.replaceFirstChar(Char::titlecase)
            )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = Color.White
        ),
        scrollBehavior = scrollBehavior
    )

}
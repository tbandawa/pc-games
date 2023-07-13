package me.tbandawa.android.pcgames.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import me.tbandawa.android.pcgames.ui.viewmodels.GamesViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    navController: NavController,
    viewModel: GamesViewModel = koinViewModel()
) {

    val uriHandler = LocalUriHandler.current
    val scrollState = rememberScrollState()
    val gameState by viewModel.game.collectAsState()

    val gameImage = rememberImagePainter(
        data = gameState?.thumbnail,
        builder = {
            crossfade(true)
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(start = 16.dp, top = 56.dp, end = 16.dp, bottom = 5.dp)
        ) {

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = gameState?.title!!,
                style = TextStyle(
                    color = Color(0xff024040),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),
                modifier = Modifier.padding(end = 5.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))
            Image(
                painter = gameImage,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${gameState?.genre!!} - ",
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(start = 5.dp)
                )
                Text(
                    text = gameState?.platform!!,
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.weight(1.0f))
                Text(
                    text = gameState?.release_date!!,
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(end = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Description",
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                )
                Text(
                    text = gameState?.short_description!!,
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Publisher",
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                )
                Text(
                    text = gameState?.publisher!!,
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Developer",
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                )
                Text(
                    text = gameState?.developer!!,
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        uriHandler.openUri(gameState?.game_url!!)
                    }
            ) {
                Text(
                    text = "Game Url",
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                )
                Text(
                    text = gameState?.game_url!!,
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        uriHandler.openUri(gameState?.freetogame_profile_url!!)
                    }
            ) {
                Text(
                    text = "FreeToGame Profile",
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                )
                Text(
                    text = gameState?.freetogame_profile_url!!,
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                )
            }

        }
    }
}

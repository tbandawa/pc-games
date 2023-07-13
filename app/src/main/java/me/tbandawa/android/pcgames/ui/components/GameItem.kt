package me.tbandawa.android.pcgames.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import me.tbandawa.android.pcgames.data.models.Game

@Composable
fun GameItem(
    game: Game
) {

    val gameImage = rememberImagePainter(
        data = game.thumbnail,
        builder = {
            crossfade(true)
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffe6e8e8))
    ) {

        Image(
            painter = gameImage,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = game.title,
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 5.dp, top = 5.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = game.genre,
                        style = TextStyle(
                            color = Color(0xff024040),
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp
                        ),
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    Spacer(modifier = Modifier.weight(1.0f))
                    Text(
                        text = game.release_date,
                        style = TextStyle(
                            color = Color(0xff024040),
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp
                        ),
                        modifier = Modifier.padding(end = 5.dp)
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun GameItemPreview() {
    val game = Game(
        id = 521,
        title = "Diablo Immortal",
        thumbnail = "https://www.freetogame.com/g/521/thumbnail.jpg",
        short_description = "Built for mobile and also released on PC, Diablo Immortal fills in the gaps between Diablo II and III in an MMOARPG environment.",
        game_url = "https://www.freetogame.com/open/diablo-immortal",
        genre = "MMOARPG",
        platform = "PC (Windows)",
        publisher = "Blizzard Entertainment",
        developer = "Blizzard Entertainment",
        release_date = "2022-06-02",
        freetogame_profile_url = "https://www.freetogame.com/diablo-immortal"
    )
    GameItem(game = game)
}
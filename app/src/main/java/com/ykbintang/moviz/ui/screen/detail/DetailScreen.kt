package com.ykbintang.moviz.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.ykbintang.moviz.R
import com.ykbintang.moviz.ui.theme.MovizTheme

@Composable
fun DetailContent(
    title: String,
    release: String,
    duration: String,
    synopsis: String,
    originalTitle: String,
    genres: String,
    languages: String,
    posterPath: String,
    backdropPath: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Box(
                modifier = modifier.height(400.dp)
            ) {

                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.ay),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = modifier
                                .height(300.dp)
                                .fillMaxWidth()
                        )
                    }

                    Row(
                        modifier = modifier.align(Alignment.BottomStart)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ax),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = modifier
                                .height(200.dp)
                                .width(150.dp)
                                .padding(start = 16.dp)
                                .clip(RoundedCornerShape(16.dp))


                        )

                        Column {
                            Text(
                                text = title,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.ExtraBold
                                ),
                                modifier = modifier.offset( y = 100.dp)
                                    .padding(start = 16.dp, top = 16.dp)
                            )

                            Text(
                                text = release,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Normal
                                ),
                                modifier = modifier.offset( y = 100.dp)
                                    .padding(start = 16.dp)
                            )
                        }
                    }
            }

            Text(
                text = "Synopsis",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = modifier
                    .padding(top = 16.dp, start = 16.dp)
            )

            Text(
                text = synopsis,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier
                    .padding(top = 4.dp, start = 16.dp)
            )

            Text(
                text = "More Information",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = modifier
                    .padding(top = 8.dp, start = 16.dp)
            )

            Text(
                text = "Original Title",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = modifier
                    .padding(top = 4.dp, start = 16.dp)
            )

            Text(
                text = originalTitle,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier
                    .padding(top = 2.dp, start = 16.dp)
            )

            Text(
                text = "Duration",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = modifier
                    .padding(top = 4.dp, start = 16.dp)
            )

            Text(
                text = duration,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier
                    .padding(top = 2.dp, start = 16.dp)
            )

            Text(
                text = "Genre",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = modifier
                    .padding(top = 4.dp, start = 16.dp)
            )

            Text(
                text = genres,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier
                    .padding(top = 2.dp, start = 16.dp)
            )

            Text(
                text = "Language",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = modifier
                    .padding(top = 4.dp, start = 16.dp)
            )

            Text(
                text = languages,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier
                    .padding(top = 2.dp, start = 16.dp)
            )


        }
    }
}




@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    MovizTheme {
        DetailContent(
            title = "Harry Potter",
            release = "Kamis, 11 Oktober 2023",
            duration = "100 Menit",
            synopsis = "Lorem ipsum dolor sit amet proques, ena fote amane lois horris nan vet on laq dja mu siqua sehopks",
            originalTitle = "Harry Potter and The Chamber of secret",
            genres = "Horror, Adventure",
            languages = "English, Indonesia",
            posterPath = "https://alternativemovieposters.com/wp-content/uploads/2023/01/Julian-De-Lio_Azkaban.jpg",
            backdropPath = "https://w0.peakpx.com/wallpaper/344/757/HD-wallpaper-harry-potter-hogwarts-castle.jpg"
        )
    }
}
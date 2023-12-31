package com.rivaldofez.moviz.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.rivaldofez.moviz.R
import com.rivaldofez.moviz.ui.theme.MovizTheme
import com.rivaldofez.moviz.ui.theme.Shapes

@Composable
fun MovieItem(
    image: String,
    title: String,
    release: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = if (image.isEmpty()) painterResource(id = R.drawable.img_poster_placeholder) else rememberImagePainter(
                data = image
            ),
            contentDescription = stringResource(id = R.string.cd_movie_item),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(170.dp)
                .clip(Shapes.medium)
        )
        Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )

        Text(
            text = release,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MovieItemPreview() {
    MovizTheme {
        MovieItem(image = "", title = "GTA V", release = "2023-05-11")
    }
}
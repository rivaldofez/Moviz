package com.rivaldofez.moviz.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rivaldofez.moviz.R
import com.rivaldofez.moviz.ui.components.TopBarComponent

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),

        ) {
        TopBarComponent(title = stringResource(id = R.string.title_profile))

        Image(
            painter = painterResource(R.drawable.img_profile),
            contentDescription = stringResource(id = R.string.cd_profile_picture),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(top = 24.dp)
                .size(150.dp)
                .clip(CircleShape),
        )

        Text(
            text = stringResource(id = R.string.developer_name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier.padding(top = 16.dp)
        )
        
        Text(
            text = stringResource(id = R.string.developer_username),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Normal
            ),
        )

        Text(
            text = stringResource(id = R.string.developer_email),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal
            ),
            color = MaterialTheme.colorScheme.secondary
        )
    }

}
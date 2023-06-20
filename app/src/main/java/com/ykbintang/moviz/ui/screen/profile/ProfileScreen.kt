package com.ykbintang.moviz.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ykbintang.moviz.R
import com.ykbintang.moviz.ui.components.TopBarComponent

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),

        ) {
        TopBarComponent(title = stringResource(id = R.string.title_profile))

        Image(
            painter = painterResource(R.drawable.img_profile),
            contentDescription = stringResource(id = R.string.cd_profile_picture),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .height(250.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Text(
            text = stringResource(id = R.string.developer_name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold
            ),
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
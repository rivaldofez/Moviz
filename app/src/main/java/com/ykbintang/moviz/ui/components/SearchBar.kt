package com.ykbintang.moviz.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.ykbintang.moviz.R
import com.ykbintang.moviz.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onSubmit: () -> Unit,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = { onSubmit() }
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = stringResource(id = R.string.cd_icon_search),
                modifier = modifier.clickable {
                    onSubmit()
                }
            )
        },
        singleLine = true,
        shape = Shapes.medium,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(stringResource(id = R.string.search_movie))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 30.dp)
            .padding(horizontal = 16.dp)
            .shadow(4.dp)

    )
}
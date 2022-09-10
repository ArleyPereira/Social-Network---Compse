package com.example.socialnetwork.presenter.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.theme.ColorBackgroundApp

@ExperimentalMaterial3Api
@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String = "",
    onBackPressed: (() -> Unit)? = null
) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        modifier = modifier.shadow(5.dp),
        navigationIcon = {
            onBackPressed?.let { action ->
                IconButton(onClick = action) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Localized description"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = ColorBackgroundApp
        )
    )

}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewTopAppBar() {
    Toolbar(title = "Title")
}
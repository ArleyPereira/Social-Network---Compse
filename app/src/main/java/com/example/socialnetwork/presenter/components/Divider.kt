package com.example.socialnetwork.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialnetwork.ui.theme.ColorSecondaryDark

@Composable
fun Divider(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(
                color = ColorSecondaryDark.copy(alpha = 0.2f),
                shape = RoundedCornerShape(16.dp)
            )
    ) {}

}

@Preview(showBackground = true)
@Composable
fun DividerPreview() {
    Divider()
}
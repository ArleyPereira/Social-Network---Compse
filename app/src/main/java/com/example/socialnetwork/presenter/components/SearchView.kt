package com.example.socialnetwork.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.theme.ColorPrimaryLight
import com.example.socialnetwork.ui.theme.ColorSecondaryDark
import com.example.socialnetwork.ui.theme.ColorTextHint

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    hintText: Int,
    text: String = "",
    onTextChange: (String) -> Unit,
    onClearText: () -> Unit,
) {

    var textValue by remember { mutableStateOf(text) }

    BasicTextField(
        value = textValue,
        onValueChange = {
            textValue = it
            onTextChange(it)
        },
        modifier = modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words
        ),
        singleLine = true,
        maxLines = 1,
        cursorBrush = SolidColor(ColorPrimaryLight),
        decorationBox = { innerTextField ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(10.dp)),
                shape = RoundedCornerShape(10.dp),
                elevation = 15.dp
            ) {
                Box(
                    Modifier
                        .background(Color.White)
                        .padding(14.dp)
                        .fillMaxWidth()
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_search_line),
                            contentDescription = stringResource(
                                id = R.string.text_hint_search_view_friends_screen
                            ),
                            tint = ColorPrimaryLight
                        )

                        Box(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .fillMaxWidth()
                        ) {
                            if (textValue.isEmpty()) {
                                Text(
                                    text = stringResource(id = hintText),
                                    modifier = Modifier
                                        .align(Alignment.CenterStart),
                                    color = ColorPrimaryLight
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 24.dp)
                            ) {
                                innerTextField()
                            }

                            if (textValue.isNotEmpty()) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_close),
                                    contentDescription = stringResource(
                                        id = R.string.text_hint_search_view_friends_screen
                                    ),
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .clickable {
                                            onClearText()
                                            textValue = ""
                                        },
                                    tint = ColorSecondaryDark
                                )
                            }
                        }

                    }

                }
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        SearchView(
            hintText = R.string.text_hint_search_view_friends_screen,
            onTextChange = {},
            onClearText = {}
        )
    }
}
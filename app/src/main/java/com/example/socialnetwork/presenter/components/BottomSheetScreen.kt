package com.example.socialnetwork.presenter.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.theme.ColorSecondaryDark
import com.example.socialnetwork.ui.theme.ColorTextDark

@Composable
fun BottomSheetScreen(
    modifier: Modifier = Modifier,
    title: String? = null,
    message: String? = null,
    textBtnOk: String? = null,
    textBtnCancel: String? = null,
    raiseHeight: Boolean = false,
    btnCancelIsVisibled: Boolean = false,
    onClickOk: () -> Unit,
    onClickCancel: () -> Unit,
) {

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = if (raiseHeight) 70.dp else 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title ?: stringResource(id = R.string.text_title_bottom_sheet))

            Divider()

            Text(
                text = message ?: stringResource(id = R.string.message_error_generic),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            ButtonDefault(
                modifier = Modifier
                    .fillMaxWidth(),
                text = textBtnOk ?: stringResource(id = R.string.text_button_ok_bottom_sheet),
                textStyle = TextStyle(color = Color.White),
                backgroundColor = ColorSecondaryDark,
                clickAction = { onClickOk() }
            )

            if (btnCancelIsVisibled) {
                ButtonDefault(
                    modifier = Modifier
                        .wrapContentWidth(),
                    text = textBtnCancel
                        ?: stringResource(id = R.string.text_button_cancel_bottom_sheet),
                    textStyle = TextStyle(color = ColorTextDark),
                    backgroundColor = Color.Transparent,
                    clickAction = { onClickCancel() }
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomSheetScreen(
            message = "message",
            onClickOk = {},
            onClickCancel = {},
        )
    }
}
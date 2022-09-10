package com.example.socialnetwork.presenter.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.theme.ColorBackgroundApp
import com.example.socialnetwork.ui.theme.ColorPrimaryDark

@Composable
fun ButtonSocialLogin(
    @DrawableRes iconId: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    clickAction: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFDEECFD))
            .clickable { clickAction() },
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 32.dp)
                .size(32.dp)
        )

    }
}

@Composable
fun ButtonDefault(
    text: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    clickAction: () -> Unit
) {

    Button(
        onClick = { clickAction() },
        modifier = modifier,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        ),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
    ) {
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
    }

}

@Preview
@Composable
fun PreviewButtons() {
    Column(
        modifier = Modifier
            .background(ColorBackgroundApp)
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {

            ButtonSocialLogin(iconId = R.drawable.ic_google) {

            }

            Spacer(modifier = Modifier.width(16.dp))

            ButtonSocialLogin(iconId = R.drawable.ic_facebook) {

            }

        }

        ButtonDefault(
            text = stringResource(id = R.string.text_btn_login_login_screen),
            backgroundColor = ColorPrimaryDark,
            modifier = Modifier
                .fillMaxWidth()
        ) {}
    }
}
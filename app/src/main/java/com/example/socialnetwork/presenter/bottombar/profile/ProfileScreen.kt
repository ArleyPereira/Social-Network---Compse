package com.example.socialnetwork.presenter.bottombar.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialnetwork.R
import com.example.socialnetwork.presenter.auth.login.events.LoginEvent
import com.example.socialnetwork.presenter.components.ButtonDefault
import com.example.socialnetwork.presenter.components.TypeButton
import com.example.socialnetwork.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.person_1),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .aspectRatio(1f, matchHeightConstraintsFirst = true)
                    .weight(3f)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.normal))

            ProfileStatisticSection(
                modifier = Modifier
                    .weight(7f),
                postValue = "120",
                postDescription = "Posts",
                friendsValue = "1.6M",
                friendsDescription = "Friends",
                followingValue = "906",
                followingDescription = "Following"
            )

        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

        ProfileDescription(
            userName = "Arley Santana",
            nickName = "arley.santana",
            description = "Desenvolvedor Android Senior. Criando aplicativos para simplicação da vida de todos os brasileiros."
        )

    }
}

@Composable
fun ProfileStatisticItem(
    modifier: Modifier = Modifier,
    value: String,
    description: String
) {

    Column(
        modifier = modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = ColorTextDark,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = description,
            color = ColorTextDark,
            fontSize = 12.sp
        )
    }

}

@Composable
fun ProfileStatisticSection(
    modifier: Modifier = Modifier,
    postValue: String,
    postDescription: String,
    friendsValue: String,
    friendsDescription: String,
    followingValue: String,
    followingDescription: String
) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ProfileStatisticItem(
            value = postValue,
            description = postDescription
        )

        Row(
            modifier = Modifier
                .width(2.dp)
                .height(15.dp)
                .background(ColorTextLight)
        ) {}

        ProfileStatisticItem(
            value = friendsValue,
            description = friendsDescription
        )

        Row(
            modifier = Modifier
                .width(2.dp)
                .height(15.dp)
                .background(ColorTextLight)
        ) {}

        ProfileStatisticItem(
            value = followingValue,
            description = followingDescription
        )

    }

}

@Composable
fun ProfileDescription(
    modifier: Modifier = Modifier,
    userName: String,
    nickName: String,
    description: String
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = userName,
            color = ColorTextDark,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = nickName,
            color = ColorTextLight,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description,
            color = ColorTextDark,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonDefault(
            text = "Editar perfil",
            textStyle = TextStyle(color = Color.White),
            backgroundColor = ColorSecondaryDark,
            typeButton = TypeButton.Small,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

        }
    }

}

@Preview
@Composable
fun ProfileScreenPreview() {
    Column(
        modifier = Modifier
            .background(ColorBackgroundApp)
            .fillMaxSize()
    ) {
        ProfileScreen(EmptyDestinationsNavigator)
    }
}
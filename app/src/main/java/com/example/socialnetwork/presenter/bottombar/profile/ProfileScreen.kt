package com.example.socialnetwork.presenter.bottombar.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialnetwork.R
import com.example.socialnetwork.presenter.bottombar.profile.state.ProfileState
import com.example.socialnetwork.presenter.bottombar.profile.state.ProfileState.*
import com.example.socialnetwork.presenter.components.ButtonDefault
import com.example.socialnetwork.presenter.components.TypeButton
import com.example.socialnetwork.presenter.destinations.FeedScreenDestination
import com.example.socialnetwork.presenter.destinations.FollowingScreenDestination
import com.example.socialnetwork.presenter.destinations.FriendsScreenDestination
import com.example.socialnetwork.ui.theme.ColorBackgroundApp
import com.example.socialnetwork.ui.theme.ColorSecondaryDark
import com.example.socialnetwork.ui.theme.ColorTextDark
import com.example.socialnetwork.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator,
    viewModel: ProfileViewModel = hiltViewModel(),
) {

    Column(
        modifier = Modifier
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.person_1),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(2.dp, ColorTextDark, RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Text(
            text = "@Arley.Santana",
            color = ColorTextDark,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        ProfileStatisticSection(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            postValue = "120",
            postDescription = "Publicações",
            friendsValue = "1.6M",
            friendsDescription = "Seguidores",
            followingValue = "906",
            followingDescription = "Seguindo"
        ) { profileState ->
            when (profileState) {
                NavFollowingScreen -> {
                    navigator.navigate(FollowingScreenDestination)
                }
                NavFriendsScreen -> {
                    navigator.navigate(FriendsScreenDestination)
                }
                NavPostsScreen -> {

                }
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

        ProfileDescription(
            description = "Desenvolvedor Android Senior. Criando aplicativos para simplicação da vida de todos os brasileiros."
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
    followingDescription: String,
    clicked: (ProfileState) -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ProfileStatisticItem(
            modifier = Modifier
                .weight(3f),
            value = postValue,
            description = postDescription
        ) { clicked(NavPostsScreen) }

        ProfileStatisticItem(
            modifier = Modifier
                .weight(3f),
            value = friendsValue,
            description = friendsDescription
        ) { clicked(NavFriendsScreen) }

        ProfileStatisticItem(
            modifier = Modifier
                .weight(3f),
            value = followingValue,
            description = followingDescription
        ) { clicked(NavFollowingScreen) }

    }

}

@Composable
fun ProfileStatisticItem(
    modifier: Modifier = Modifier,
    value: String,
    description: String,
    clicked: () -> Unit
) {

    Column(
        modifier = modifier
            .padding(8.dp)
            .clickable { clicked() },
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
fun ProfileDescription(
    modifier: Modifier = Modifier,
    description: String
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

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
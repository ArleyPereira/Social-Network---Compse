package com.example.socialnetwork.presenter.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.socialnetwork.presenter.bottombar.profile.ProfileStatisticSection
import com.example.socialnetwork.presenter.bottombar.profile.state.ProfileState
import com.example.socialnetwork.presenter.components.ButtonDefault
import com.example.socialnetwork.presenter.components.Toolbar
import com.example.socialnetwork.presenter.components.TypeButton
import com.example.socialnetwork.presenter.destinations.FollowingScreenDestination
import com.example.socialnetwork.presenter.destinations.FriendsScreenDestination
import com.example.socialnetwork.ui.theme.ColorBackgroundApp
import com.example.socialnetwork.ui.theme.ColorSecondaryDark
import com.example.socialnetwork.ui.theme.ColorTextDark
import com.example.socialnetwork.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun ProfileUserScreen(
    navigator: DestinationsNavigator,
    viewModel: ProfileUserViewModel = hiltViewModel(),
    userId: Long? = 0
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Toolbar(title = "@arley.santana") { navigator.popBackStack() }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
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
                text = "Arley Santana",
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
                    ProfileState.NavFollowingScreen -> {
                        navigator.navigate(FollowingScreenDestination)
                    }
                    ProfileState.NavFriendsScreen -> {
                        navigator.navigate(FriendsScreenDestination)
                    }
                    ProfileState.NavPostsScreen -> {

                    }
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

            Text(
                text = "Desenvolvedor Android Senior. Criando aplicativos para simplicação da vida de todos os brasileiros.",
                color = ColorTextDark,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            ButtonDefault(
                text = "Seguir",
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

}

@Preview
@Composable
fun ProfileUserScreenPreview() {
    Column(
        modifier = Modifier
            .background(ColorBackgroundApp)
            .fillMaxSize()
    ) {
        ProfileUserScreen(EmptyDestinationsNavigator)
    }
}
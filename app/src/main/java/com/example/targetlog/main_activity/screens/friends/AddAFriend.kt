package com.example.targetlog.main_activity.screens.friends

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.targetlog.R
import com.example.targetlog.commons.getTopLineShape
import com.example.targetlog.main_activity.screens.common_components.TopBar
import com.example.targetlog.main_activity.screens.personal.PersonalScreenViewModel
import com.example.targetlog.ui.theme.Blue76
import com.example.targetlog.ui.theme.DarkGreen833
import com.example.targetlog.ui.theme.DarkLight
import com.example.targetlog.ui.theme.GreenLight


@OptIn(ExperimentalFoundationApi::class)
//@Preview
@Composable
fun AddAFriend(
    onClickNavigate: (String) -> Unit = { _ -> },
    onBackClickNavigate: () -> Unit = {},
    viewModel: PersonalScreenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "ADD A FRIEND",
                backNavigate = true,
                onBackClickNavigate = onBackClickNavigate,
                onBluetoothButtonClick = onClickNavigate
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, innerPadding.calculateTopPadding(), 0.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val pagerState = rememberPagerState(
                pageCount = { 2 }
            )

            var selectedTabIndex by remember {
                mutableIntStateOf(0)
            }


            LaunchedEffect(selectedTabIndex) {
                pagerState.animateScrollToPage(selectedTabIndex)
            }
            LaunchedEffect(pagerState.currentPage) {
                selectedTabIndex = pagerState.currentPage
            }

            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Blue76,
                divider = {},
                indicator = { tabPositions ->
                    if (selectedTabIndex < tabPositions.size) {
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                            color = GreenLight
                        )
                    }
                },
            ) {
                Tab(
                    selected = 0 == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = 0
                    },
                    text = {
                        Text(text = "CONTACTS", color = Color.White)
                    },
                    selectedContentColor = GreenLight,
                    unselectedContentColor = Color.Blue
                )
                Tab(
                    selected = 1 == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = 1
                    },
                    text = {
                        Text(text = "USERNAME", color = Color.White)
                    },
                    selectedContentColor = GreenLight,
                )
            }


            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {page->
                when(page){
                    0-> ContactsTab()
                    1-> UserNameTab()
                }

            }

            /*Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp, 30.dp, 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }*/
        }


    }
}

//@Preview
@Composable
fun ContactsTab(){

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        item(){
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "4 CONTACTS FOUND",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth(.6f)
                        .border(4.dp, GreenLight, RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonColors(GreenLight, Color.Black, GreenLight, GreenLight)
                ) {
                    Text(text = "Add All")
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
        items(4) {
            Box(
                modifier = Modifier
                    .background(Color.Transparent)
                    .border(1.dp, DarkGreen833, getTopLineShape(1.dp))
                    .padding(8.dp, 12.dp)
            ) {
                FriendsCardWithIcon(R.drawable.add_user)
            }
        }

        item(){
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "INVITE CONTACTS ",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
        items(2) {
            Box(
                modifier = Modifier
                    .background(Color.Transparent)
                    .border(1.dp, DarkGreen833, getTopLineShape(1.dp))
                    .padding(8.dp, 12.dp)
            ) {
                FriendsCardWithIcon(R.drawable.invite_friend)
            }
        }
    }
}

@Preview
@Composable
fun UserNameTab(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(0.dp, 30.dp, 0.dp, 15.dp),
            value = "Enter Username...",
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors().copy(
                focusedContainerColor = DarkLight,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = DarkLight,
                unfocusedContainerColor = DarkLight,
                unfocusedIndicatorColor = DarkLight
            ),
            readOnly = true,
            onValueChange = {   },
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable {   },
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        )
    }

}
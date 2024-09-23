package com.example.targetlog.main_activity.screens.add_friend

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.provider.ContactsContract
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.targetlog.R
import com.example.targetlog.commons.getTopLineShape
import com.example.targetlog.main_activity.screens.common_components.ContactInvitationCard
import com.example.targetlog.main_activity.screens.common_components.FriendsCardWithIcon
import com.example.targetlog.main_activity.screens.common_components.TopBar
import com.example.targetlog.model.User
import com.example.targetlog.model.service.ContactDTO
import com.example.targetlog.ui.theme.Blue76
import com.example.targetlog.ui.theme.DarkGreen833
import com.example.targetlog.ui.theme.DarkLight
import com.example.targetlog.ui.theme.GreenLight
import dagger.hilt.android.qualifiers.ApplicationContext


@OptIn(ExperimentalFoundationApi::class)
//@Preview
@Composable
fun AddAFriend(
    onClickNavigate: (String) -> Unit = { _ -> },
    onBackClickNavigate: () -> Unit = {},
    viewModel: AddFriendViewModel = hiltViewModel()
) {
    val userList by viewModel.users.collectAsState()  // Observe the list of users from the ViewModel
    val potentialFriends by viewModel.potentialFriends.collectAsState()  // Observe the list of users from the ViewModel

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
                    0-> ContactsTab(
                        userList=potentialFriends,
                        isPermissionAlreadyGranted = viewModel.checkPermissionForContact(Manifest.permission.READ_CONTACTS),
                        onClick = { friendId->
                            viewModel.addUserToFriendList(friendId)
                        }
                    )
                    1-> UserNameTab(userList)
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
@SuppressLint("Range")
@Composable
fun ContactsTab(
    userList:List<User>,
    isPermissionAlreadyGranted:Boolean,
    onClick:(String)->Unit
){

    val context = LocalContext.current
    val contactList = remember {
        mutableStateListOf<ContactDTO>()
    }

    val contacts = context.contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null
    )

    contacts?.let {
        while (it.moveToNext()){
            contactList.add(
                ContactDTO(
                    name = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    pnNumber = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                )
            )
        }
    }
    contacts?.close()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        item(){
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "${userList.size} User FOUND",
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
        items(userList) { user->
            Box(
                modifier = Modifier
                    .background(Color.Transparent)
                    .border(1.dp, DarkGreen833, getTopLineShape(1.dp))
                    .padding(8.dp, 12.dp)
            ) {
                FriendsCardWithIcon(
                    user = user ,
                    rId = R.drawable.add_user,
                    onIconClick = { user->
                        onClick(user.id)
                    }
                )
            }
        }

        //ask contact permission
        item{
            //variables
            var isContactPermissionGranted by remember {
                mutableStateOf(isPermissionAlreadyGranted)
            }

            val permissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = { isGranted->
                    if (isGranted){
                        isContactPermissionGranted = true
                    }
                }
            )


            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(intrinsicSize = IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "INVITE CONTACTS ",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )

                if (!isContactPermissionGranted){
                    Button(
                        onClick = { permissionLauncher.launch(Manifest.permission.READ_CONTACTS) },
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 5.dp),
                    ) {
                        Text(text = "GRANT CONTACT PERMISSION",
                            modifier = Modifier.fillMaxWidth(.7f) ,
                            maxLines = 2,
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(30.dp))
        }

        //get contacts

        items(contactList) { contact->
            Box(
                modifier = Modifier
                    .background(Color.Transparent)
                    .border(1.dp, DarkGreen833, getTopLineShape(1.dp))
                    .padding(8.dp, 12.dp)
            ) {
                ContactInvitationCard(contact = contact)
            }
        }
    }
}

//@Preview
@Composable
fun UserNameTab(
    userList:List<User>
){
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.fillMaxWidth(),
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
                    onValueChange = { },
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.clickable { },
                            painter = painterResource(id = R.drawable.baseline_search_24),
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                )
            }
        }
    ) { pad->

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(pad)
        ) {
            items(userList) { user->
                Box(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .border(1.dp, DarkGreen833, getTopLineShape(1.dp))
                        .padding(8.dp, 12.dp)
                ) {
                    FriendsCardWithIcon(
                        user = user ,
                        rId = R.drawable.add_user,
                        onIconClick = { user->

                        }
                    )
                }
            }
        }
    }
}


//@Preview
@Composable
fun ContactsTabPreview(){

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
                FriendsCardWithIcon(User(),R.drawable.add_user)
            }
        }

        item{
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(intrinsicSize = IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "INVITE CONTACTS ",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )


                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 5.dp),
                ) {
                    Text(text = "GRANT CONTACT PERMISSION",
                         modifier = Modifier.fillMaxWidth(.7f) ,
                        maxLines = 2,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
        items(2) {
            Box(
                modifier = Modifier
                    .background(Color.Transparent)
                    .border(1.dp, DarkGreen833, getTopLineShape(1.dp))
                    .padding(8.dp, 12.dp)
            ) {
                FriendsCardWithIcon(User(),R.drawable.invite_friend)
            }
        }
    }
}

//@Preview
@Composable
fun UserNameTabPreview(){
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
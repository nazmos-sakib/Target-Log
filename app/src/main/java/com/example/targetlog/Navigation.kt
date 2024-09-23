package com.example.targetlog

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.targetlog.commons.ACCOUNT_CENTER_SCREEN
import com.example.targetlog.commons.ADD_FRIENDS_SCREEN
import com.example.targetlog.commons.ANALYTICS_GRAPH_SCREEN
import com.example.targetlog.commons.BLUETOOTH_SCREEN
import com.example.targetlog.commons.BOTTOM_NAV_HOME_SCREEN
import com.example.targetlog.commons.BOTTOM_NAV_PROFILE_SCREEN
import com.example.targetlog.commons.BOTTOM_NAV_TRAINING_SCREEN
import com.example.targetlog.commons.CHANGE_DISPLAY_NAME_SCREEN
import com.example.targetlog.commons.CHANGE_EMAIL_SCREEN
import com.example.targetlog.commons.FIND_MY_TARGET_SCREEN
import com.example.targetlog.commons.FRIENDS_PROFILE_SCREEN
import com.example.targetlog.commons.FRIENDS_SCREEN
import com.example.targetlog.commons.PERSONAL_SCREEN
import com.example.targetlog.commons.SIGN_IN_SCREEN
import com.example.targetlog.commons.SIGN_UP_SCREEN
import com.example.targetlog.commons.SPLASH_SCREEN
import com.example.targetlog.commons.WORKOUT_HISTORY
import com.example.targetlog.main_activity.screens.analytics.AnalyticScreen
import com.example.targetlog.main_activity.screens.bluetooth.BluetoothScreen
import com.example.targetlog.main_activity.screens.add_friend.AddAFriend
import com.example.targetlog.main_activity.screens.friend_profile.FriendProfileScreen
import com.example.targetlog.main_activity.screens.friends.FriendsScreen
import com.example.targetlog.main_activity.screens.bottom_nav_home.HomeScreen
import com.example.targetlog.main_activity.screens.personal.ChangeDisplayNameScreen
import com.example.targetlog.main_activity.screens.personal.ChangeEmailScreen
import com.example.targetlog.main_activity.screens.personal.PersonalScreen
import com.example.targetlog.main_activity.screens.bottom_nav_profile.ProfileScreen
import com.example.targetlog.main_activity.screens.bottom_nav_training.BottomNavTrainingScreen
import com.example.targetlog.main_activity.screens.find_my_target.FindMyTargetScreen
import com.example.targetlog.main_activity.screens.sign_in.SignInScreen
 import com.example.targetlog.main_activity.screens.sign_up.SignUpScreen
import com.example.targetlog.main_activity.screens.splash.SplashScreen
import com.example.targetlog.main_activity.screens.workout_history.Workout_History
import com.example.targetlog.ui.theme.TargetLogTheme

@Composable
fun AppEntry(
    startDestination:String
){

    TargetLogTheme {
        Surface(color = MaterialTheme.colorScheme.background) {

            val appState = rememberAppState()
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    //bottom doesnot need to show when it is in splash screen
                    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
                    var showBottomSheet by remember {
                        mutableStateOf(true)
                    }
                    LaunchedEffect(navBackStackEntry) {
                        val currentRoue = navBackStackEntry?.destination?.route
                        // do something with currentRoute
                        if (currentRoue != null) {
                            showBottomSheet = !(currentRoue.equals(SPLASH_SCREEN) || currentRoue.equals(SIGN_IN_SCREEN) || currentRoue.equals(SIGN_UP_SCREEN))
                        }
                    }
                    if (showBottomSheet){
                        BottomNavMenu(
                            modifier = Modifier
                                .padding(0.dp)
                                .height(intrinsicSize = IntrinsicSize.Min),
                            items = getBottomNavItems(),
                            navController = appState.navController,
                            onItemClick = {
                                appState.navigate(it.route)
                            }
                        )

                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = appState.navController,
                    startDestination = startDestination,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    notesGraph(appState)
                }
            }
        }

    }
}




@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
         AppState(navController)
    }


//extended function of navHost
fun NavGraphBuilder.notesGraph(appState:  AppState) {
    composable(SPLASH_SCREEN) {
        //SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
        SplashScreen(openAndPopUp = { route, popUp -> appState.clearAndNavigate(route) })
    }


    composable(BOTTOM_NAV_HOME_SCREEN){
        HomeScreen(onClickGotoBluetoothScreen = { route -> appState.navigate (route) })
    }

    composable(BOTTOM_NAV_TRAINING_SCREEN){
        BottomNavTrainingScreen()
        /*Workout_History(
            onClickGotoBluetoothScreen = { route -> appState.navigate (route) },
            onBackClickNavigate = {appState.popUp()}
        )*/
    }

    composable(WORKOUT_HISTORY){
        //BottomNavTrainingScreen()
        Workout_History(
            onClickGotoBluetoothScreen = { route -> appState.navigate (route) },
            onBackClickNavigate = {
                appState.popUp()
                appState.navigate(BOTTOM_NAV_PROFILE_SCREEN)
            }
        )
    }
    composable(BLUETOOTH_SCREEN){
        BluetoothScreen()
    }

    composable(FIND_MY_TARGET_SCREEN){
        FindMyTargetScreen(
            onBackClickNavigate = {appState.popUp()}
        )
    }
    composable(BOTTOM_NAV_PROFILE_SCREEN){
        ProfileScreen(
            onClickNavigate = { route -> appState.navigate (route) },
        )
    }
    composable(PERSONAL_SCREEN){
        PersonalScreen(
            onClickNavigate = { route -> appState.navigate (route) },
            onBackClickNavigate = {appState.popUp()}
        )
    }
    composable(CHANGE_EMAIL_SCREEN){
        ChangeEmailScreen(
            onClickNavigate = { route -> appState.navigate (route) },
            onBackClickNavigate = {appState.popUp()}
        )
    }
    composable(CHANGE_DISPLAY_NAME_SCREEN){
        ChangeDisplayNameScreen(
            onClickNavigate = { route -> appState.navigate (route) },
            onBackClickNavigate = {appState.popUp()}
        )
    }
    composable(FRIENDS_SCREEN){
        FriendsScreen(
            onClickNavigate = { route -> appState.navigate (route) },
            onBackClickNavigate = {appState.popUp()}
        )
    }
    composable(ANALYTICS_GRAPH_SCREEN){
        AnalyticScreen(
            onClickGotoBluetoothScreen = { route -> appState.navigate (route) },
            onBackClickNavigate = {appState.popUp()}
        )
    }
    composable(FRIENDS_PROFILE_SCREEN){
        FriendProfileScreen(
            onClickNavigate = { route -> appState.navigate (route) },
            onBackClickNavigate = {appState.popUp()}
        )
    }
    composable(ADD_FRIENDS_SCREEN){
        AddAFriend(
            onClickNavigate = { route -> appState.navigate (route) },
            onBackClickNavigate = {appState.popUp()}
        )
    }


    composable(SIGN_IN_SCREEN) {
        //SignInScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
        SignInScreen(
            onClickNavigate = { route->appState.navigate(route)}
        )
    }

    composable(SIGN_UP_SCREEN) {
        SignUpScreen(
            onBackClickNavigate = {appState.popUp()},
            onClickNavigate = { route->appState.navigate(route)}
        )
        //SignUpScreen()
    }


    composable(ACCOUNT_CENTER_SCREEN) {
        //AccountCenterScreen(restartApp = { route -> appState.clearAndNavigate(route) })
    }
}
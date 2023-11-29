package no.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import no.model.SurveyViewModel
import no.surveyscreen.backgroundscreen.BackgroundPage
import no.surveyscreen.homescreen.HomePage
import no.surveyscreen.surveycasescreen.SurveyPageOne
import no.surveyscreen.surveycasescreen.SurveyPageTwo
import no.surveyscreen.userinfoscreen.UserInfoPage

@Composable
fun SurveyNavHost(
    navController: NavHostController,
    viewModel: SurveyViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SurveyDestinations.HOME_ROUTE,
        modifier = modifier
    ) {
        composable(SurveyDestinations.HOME_ROUTE) {
            HomePage(viewModel = viewModel, onNavigateToUserInfo = {
                if (viewModel.participantID.isNotEmpty()) {
                    navController.navigate(SurveyDestinations.USER_INFO_ROUTE)
                } else {
                    // Handle the case where participant ID is not generated
                }
            })
        }
        composable(SurveyDestinations.USER_INFO_ROUTE) {
            UserInfoPage(
                viewModel = viewModel,
                onNavigateBack = { navController.navigate(SurveyDestinations.HOME_ROUTE)},
                onNavigateNext = {navController.navigate(SurveyDestinations.BACKGROUND_ROUTE)}) {
            }
        }
        composable(SurveyDestinations.BACKGROUND_ROUTE) {
            BackgroundPage(
                onNavigateBack = { navController.navigate(SurveyDestinations.USER_INFO_ROUTE)},
                onNavigateNext = { navController.navigate(SurveyDestinations.SURVEYPAGEONE_ROUTE)}) {
            }
        }
        composable(SurveyDestinations.SURVEYPAGEONE_ROUTE) {
            SurveyPageOne(
                viewModel=viewModel,
                onNavigateBack = { navController.navigate(SurveyDestinations.BACKGROUND_ROUTE) },
                onNavigateNext = { navController.navigate(SurveyDestinations.SURVEYPAGETWO_ROUTE) }) {
            }
        }
        composable(SurveyDestinations.SURVEYPAGETWO_ROUTE) {
            SurveyPageTwo(
                viewModel=viewModel,
                onNavigateBack = { navController.navigate(SurveyDestinations.SURVEYPAGEONE_ROUTE) }) {
            }
        }
    }
}


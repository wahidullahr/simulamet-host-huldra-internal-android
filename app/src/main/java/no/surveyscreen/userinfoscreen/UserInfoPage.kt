package no.surveyscreen.userinfoscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import no.huldreonlinesurvey.R
import no.model.SurveyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoPage(
    viewModel: SurveyViewModel,
    onNavigateBack: () -> Unit,
    onNavigateNext: () -> Unit,
    function: () -> Unit,
) {
    // Assume all input fields are stored in the ViewModel
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        TextField(
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = viewModel.country,
            onValueChange = { viewModel.country = it },
            label = { Text("Country") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))

        // For the radio buttons for the degree
        Column {
            Text("Degree")
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                // This Row will contain Columns for each RadioButton and its label, spaced out
                DegreeOption(viewModel, SurveyViewModel.Degree.BSc, "BSc")
                DegreeOption(viewModel, SurveyViewModel.Degree.MSc, "MSc")
                DegreeOption(viewModel, SurveyViewModel.Degree.PhD, "PhD")
                DegreeOption(viewModel, SurveyViewModel.Degree.Other, "Other")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Checkbox for agreeing to participate
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = viewModel.agreeToParticipate,
                onCheckedChange = { viewModel.agreeToParticipate = it }
            )
            Text("Do you agree to participate fairly in this survey?")
        }
        Spacer(modifier = Modifier.weight(1f)) // This pushes the buttons to the bottom

        // Bottom Row for Back and Next buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(colors = ButtonDefaults.buttonColors(colorResource(R.color.yellow)),onClick = onNavigateBack) {
                Text("Back", color = Color.White)
            }
            Button(colors = ButtonDefaults.buttonColors(colorResource(R.color.yellow)),onClick = {
                if (viewModel.areFieldsValid()) {
                    onNavigateNext()
                } else {
                }
            }) {
                Text("Next")
            }
        }
    }
}

@Composable
fun DegreeOption(viewModel: SurveyViewModel, degree: SurveyViewModel.Degree, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        RadioButton(
            selected = viewModel.selectedDegree == degree,
            onClick = { viewModel.selectedDegree = degree },
        )
        Text(label)
    }
}

@Composable
fun ShowErrorDialog() {
    // You can call this composable to show an error dialog
    AlertDialog(
        onDismissRequest = { /* dismiss logic */ },
        title = { Text("Error") },
        text = { Text("Please fill in all the required fields.") },
        confirmButton = {
            Button(onClick = { /* dismiss logic */ }) {
                Text("OK")
            }
        }
    )
}
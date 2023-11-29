package no.surveyscreen.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import no.huldreonlinesurvey.R
import no.model.SurveyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(viewModel: SurveyViewModel, onNavigateToUserInfo: () -> Unit) {
    var showParticipantIdDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Huldra: Sample Title", style = MaterialTheme.typography.titleSmall)
        Text("This is a sample subtitle or introduction text.", style = MaterialTheme.typography.titleSmall)

        OutlinedTextField(
            value = viewModel.participantID, // Bind this to the participant ID in the viewModel
            onValueChange = { viewModel.participantID = it }, // Update the viewModel's participant ID
            label = { Text("Participant ID") },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                colorResource(R.color.black),
                focusedIndicatorColor = colorResource(R.color.soft_blue), // Soft blue border when focused
                unfocusedIndicatorColor = Color.LightGray
            ),
        )

        Button(colors = ButtonDefaults.buttonColors(colorResource(R.color.soft_blue)),onClick = {
            if (viewModel.participantID.isEmpty()) {
                showParticipantIdDialog = true // Show dialog if ID is not generated
            } else {
                onNavigateToUserInfo() // Navigate to the next screen if ID is generated
            }
        }) {
            Text("Start survey", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("If you don't have a participant ID, you can have one by clicking the button below.")

        Button(colors = ButtonDefaults.buttonColors(colorResource(R.color.soft_blue)),onClick = { viewModel.generateParticipantID() }) { // Generate a new ID
            Text("Get participant ID",color = Color.Black)
        }

        Text("Please view this application in full screen mode.", style = MaterialTheme.typography.titleSmall)
    }

    // Show dialog if participant ID is not generated
    if (showParticipantIdDialog) {
        AlertDialog(
            onDismissRequest = { showParticipantIdDialog = false },
            title = { Text("Participant ID Missing") },
            text = { Text("Please generate a participant ID before starting the survey.") },
            confirmButton = {
                Button(onClick = { showParticipantIdDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}


package no.surveyscreen.backgroundscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import no.huldreonlinesurvey.R

@Composable
fun BackgroundPage(onNavigateBack: () -> Unit, onNavigateNext: () -> Unit, function: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Survey Background",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Your text in the middle of the page
        Text(
            text = "This is some background information about the survey for participant",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.weight(1f)) // This pushes the buttons to the bottom

        // Bottom Row for Back and Next buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(colors = ButtonDefaults.buttonColors(colorResource(R.color.soft_blue)),onClick = onNavigateBack) {
                Text("Back", color = Color.Black)
            }
            Button(colors = ButtonDefaults.buttonColors(colorResource(R.color.soft_blue)),onClick = onNavigateNext) {
                Text("Next", color = Color.Black)
            }
        }
    }
}

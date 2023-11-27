package no.surveyscreen.surveycasescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import no.huldreonlinesurvey.R
import no.model.SurveyViewModel

@Composable
fun SurveyPageOne(
    viewModel: SurveyViewModel,
    onNavigateBack: () -> Unit,
    onNavigateNext: () -> Unit,
) {
    // This would be your screen state that manages the Likert scale selections
    var opinionSelection by remember { mutableIntStateOf(0) } // Starts from 0 if nothing is selected
    var themeSelection by remember { mutableIntStateOf(0) }

    // Define your labels for the 5-point and 7-point Likert scales
    val labelsFor5PointScale = listOf(
        "Strongly Disagree",
        "Disagree",
        "Neutral",
        "Agree",
        "Strongly Agree")
    val labelsFor7PointScale = listOf(
        "Strongly Disagree",
        "Disagree",
        "Somewhat Disagree",
        "Neutral",
        "Somewhat Agree",
        "Agree",
        "Strongly Agree"
    )


    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Text("Feedback Sample Label Second 1/5", style = MaterialTheme.typography.bodySmall)
            Text("Feedback sample text second", style = MaterialTheme.typography.titleSmall)
            // Video placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text("Video Placeholder")
            }
            // Optionally add a video player here if you are implementing video playback
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Text("Please answer the questions below!", style = MaterialTheme.typography.titleSmall)

            Spacer(modifier = Modifier.height(5.dp))

            LikertScale(
                labels = labelsFor5PointScale,
                question = "Opinion on the subject in the video (optional)",
                selectedOption = opinionSelection,
                onOptionSelected = { opinionSelection = it }
            )

            Spacer(modifier = Modifier.height(8.dp))

            LikertScale(
                labels = labelsFor7PointScale,
                question = "Question about opinion on the theme (optional)",
                selectedOption = themeSelection,
                onOptionSelected = { themeSelection = it }
            )
        }

        // Bottom bar with Previous/Next buttons
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(colors = ButtonDefaults.buttonColors(colorResource(R.color.yellow)),onClick = onNavigateBack) {
                    Text("Back")
                }
                Button(colors = ButtonDefaults.buttonColors(colorResource(R.color.yellow)),onClick = onNavigateNext) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
fun LikertScale(
    labels: List<String>, // Pass the labels as a list of strings
    question: String,
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit
) {
    Column {
        Text(question, style = MaterialTheme.typography.titleSmall)
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            labels.forEachIndexed { index, label ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    RadioButton(
                        selected = selectedOption == index + 1,
                        onClick = { onOptionSelected(index + 1) }
                    )
                    // Split the label into two lines if it has two words
                    Text(
                        text = label,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(IntrinsicSize.Min)
                    )
                    Spacer(modifier = Modifier.height(4.dp)) // Adjust the height as needed
                }
            }
        }
    }
}






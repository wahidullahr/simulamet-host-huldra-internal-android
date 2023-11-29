package no.surveyscreen.surveycasescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import no.huldreonlinesurvey.R
import no.model.SurveyViewModel

@Composable
fun SurveyPageTwo(
    viewModel: SurveyViewModel,
    onNavigateBack: () -> Unit,
    onNavigateNext: () -> Unit,
    function: () -> Unit,
) {
    // This would be your screen state that manages the Likert scale selections
    var fivePointScaleValue by remember { mutableFloatStateOf(0f) }
    var sevenPointScaleValue by remember { mutableFloatStateOf(0f) }

    // Define your labels for the 5-point and 7-point Likert scales

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Text("Feedback Sample Label Second 2/4", style = MaterialTheme.typography.bodySmall)
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
            Text(
                "Please answer the questions below!",
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            FivePointLikertScale(
                question = "Opinion on the subject in the video (optional)",
                chosenValue = fivePointScaleValue,
                onValueChange = { newValue -> fivePointScaleValue = newValue }
            )

            Spacer(modifier = Modifier.height(8.dp))

            SevenPointHorizontalContinuousLikertScale(
                question = "Question about opinion on the theme (optional)",
                chosenValue = sevenPointScaleValue,
                onValueChange = { newValue -> sevenPointScaleValue = newValue }
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
                Button(
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.soft_blue)),
                    onClick = onNavigateBack
                ) {
                    Text("Back", color = Color.Black)
                }
                Button(
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.soft_blue)),
                    onClick = onNavigateNext
                ) {
                    Text("Next", color = Color.Black)
                }
            }
        }
    }
}


@Composable
fun FivePointLikertScale(
    question: String,
    chosenValue: Float,
    onValueChange: (Float) -> Unit
) {
    Column {
        Text(question, style = MaterialTheme.typography.titleSmall)

        Slider(
            value = chosenValue,
            onValueChange = onValueChange,
            valueRange = 1f..5f,
            steps = 0 // Making it 0 for a continuous slider
        )

        // Display the chosen value
        Text(
            "Chosen value: %.1f".format(chosenValue),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Labels for the slider
        NumericLabelsForSlider(points = 5)
    }
}

@Composable
fun SevenPointHorizontalContinuousLikertScale(
    question: String,
    chosenValue: Float,
    onValueChange: (Float) -> Unit
) {
    Column {
        Text(question, style = MaterialTheme.typography.titleSmall)

        Slider(
            value = chosenValue,
            onValueChange = onValueChange,
            valueRange = 1f..7f,
            steps = 0 // Making it 0 for a continuous slider
        )

        // Display the chosen value
        Text(
            "Chosen value: %.1f".format(chosenValue),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Labels for the slider
        NumericLabelsForSlider(points = 7)
    }
}

@Composable
fun NumericLabelsForSlider(points: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        (1..points).forEach { i ->
            Text(text = i.toString())
        }
    }
}



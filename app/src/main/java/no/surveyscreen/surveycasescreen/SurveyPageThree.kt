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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import no.huldreonlinesurvey.R
import no.model.SurveyViewModel

@Composable
fun SurveyPageThree(
    viewModel: SurveyViewModel,
    onNavigateBack: () -> Unit,
    onNavigateNext: () -> Unit,
) {
    var fivePointScaleValue by remember { mutableStateOf(1f) } // Initialized to 1f
    var sevenPointScaleValue by remember { mutableStateOf(1f) } // Initialized to 1f

    Column(modifier = Modifier.fillMaxSize()) {
        // Video placeholder section
        Column(modifier = Modifier.padding(10.dp)) {
            Text("Feedback Sample Label Second 3/4", style = MaterialTheme.typography.bodySmall)
            Text("Feedback sample text second", style = MaterialTheme.typography.titleSmall)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text("Video Placeholder")
            }
        }

        // Likert scales and navigation buttons in a LazyColumn
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(10.dp)) // Additional spacer for separation
                LikertScaleWithLabelAndValue(
                    question = "Opinion on the subject in the video (optional)",
                    points = 5,
                    chosenValue = fivePointScaleValue,
                    onValueChange = { newValue -> fivePointScaleValue = newValue }
                )
                Spacer(modifier = Modifier.height(10.dp)) // Spacer between scales
            }

            item {
                LikertScaleWithLabelAndValue(
                    question = "Question about opinion on the theme (optional)",
                    points = 7,
                    chosenValue = sevenPointScaleValue,
                    onValueChange = { newValue -> sevenPointScaleValue = newValue }
                )
            }

            item {
                // Navigation buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
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
}

@Composable
fun LikertScaleWithLabelAndValue(
    question: String,
    points: Int,
    chosenValue: Float,
    onValueChange: (Float) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(question, style = MaterialTheme.typography.bodyLarge)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Slider(
                value = chosenValue,
                onValueChange = onValueChange,
                valueRange = 1f..points.toFloat(),
                steps = 0, // Continuous slider
                modifier = Modifier
                    .rotate(-90f) // Rotate the slider for vertical orientation
                    .height(100.dp) // Adjusted height for smaller size
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Chosen: %.1f".format(chosenValue), modifier = Modifier.padding(start = 8.dp))
                NumericLabelsForVerticalSlider(points)
            }
        }
    }
}

@Composable
fun NumericLabelsForVerticalSlider(points: Int) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.height(200.dp)
    ) {
        (1..points).forEach { i ->
            Text(text = i.toString())
        }
    }
}


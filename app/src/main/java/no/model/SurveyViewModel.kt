package no.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.UUID

class SurveyViewModel : ViewModel() {
    // Participant ID
    var participantID by mutableStateOf("")

    // User information fields
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var country by mutableStateOf("")
    var comments by mutableStateOf("")
    var selectedDegree by mutableStateOf(Degree.None)
    var agreeToParticipate by mutableStateOf(false)
    var participateInFutureSurveys by mutableStateOf(false)

    // Enum to represent degree options
    enum class Degree {
        None, BSc, MSc, PhD, Other
    }

    // Function to generate a new participant ID on the home page
    fun generateParticipantID() {
        participantID = UUID.randomUUID().toString()
    }

    fun areFieldsValid(): Boolean {
        return name.isNotBlank() &&
                email.isNotBlank() &&
                country.isNotBlank() && agreeToParticipate && selectedDegree != Degree.None
        // Add other checks for other fields as necessary
    }
}


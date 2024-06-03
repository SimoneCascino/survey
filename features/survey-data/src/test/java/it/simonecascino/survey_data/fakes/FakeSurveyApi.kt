package it.simonecascino.survey_data.fakes

import it.simonecascino.survey_data.models.SubmitRequest
import it.simonecascino.survey_data.models.SurveyResponse
import it.simonecascino.survey_data.service.SurveyApi
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class FakeSurveyApi: SurveyApi {

    override suspend fun getSurvey(): List<SurveyResponse> {

        delay(2.seconds)

        return listOf(
            SurveyResponse(
                id = 1,
                question = "What is your favourite colour?"
            )
        )
    }

    override suspend fun submitAnswer(request: SubmitRequest) {
        delay(2.seconds)
    }
}
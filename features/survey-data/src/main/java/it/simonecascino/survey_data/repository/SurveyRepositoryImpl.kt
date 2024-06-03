package it.simonecascino.survey_data.repository

import it.simonecascino.survey_data.models.SubmitRequest
import it.simonecascino.survey_data.models.surveyResponseToDomain
import it.simonecascino.survey_data.service.SurveyApi
import it.simonecascino.survey_domain.repository.SurveyRepository
import it.simonecascino.utils.apiEmptyResponseFlow
import it.simonecascino.utils.apiResponseFlow
import javax.inject.Inject

/**
 * this repository is actually simple. I had the idea to use a StateFlow to store the survey and
 * the answers, as well as the states of any single answer while submitted, but since in the requirement
 * was asked to reset the survey when the user click back, I thought that such things could
 * be handled in the viewmodel, which is automatically cleared going back in the navigation graph
 */
class SurveyRepositoryImpl @Inject constructor(
    private val surveyApi: SurveyApi
): SurveyRepository {

    override suspend fun getSurvey() = apiResponseFlow(
        mapper = surveyResponseToDomain,
        call = { surveyApi.getSurvey() }
    )

    override suspend fun submitAnswer(id: Int, answer: String) = apiEmptyResponseFlow(
        call = {
            surveyApi.submitAnswer(
                request = SubmitRequest(id, answer)
            )
        }
    )


}
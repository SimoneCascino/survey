package it.simonecascino.survey_data.models

import it.simonecascino.survey_domain.models.SurveyDomain
import it.simonecascino.utils.Mapper

val surveyResponseToDomain: Mapper<List<SurveyResponse>, List<SurveyDomain>> = { surveyResponses ->
    surveyResponses.map {
        SurveyDomain(
            id = it.id,
            text = it.question
        )
    }
}
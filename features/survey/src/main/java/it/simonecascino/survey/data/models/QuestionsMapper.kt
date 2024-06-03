package it.simonecascino.survey.data.models

import it.simonecascino.survey_domain.models.SurveyDomain
import it.simonecascino.utils.Mapper
import it.simonecascino.utils.PresentationState

val surveyDomainToQuestions: Mapper<List<SurveyDomain>, List<Question>> = { surveyDomain ->
    surveyDomain.map {
        Question(
            id = it.id,
            text = it.text,
            answer = "",
            submitStatus = PresentationState.Idle
        )
    }
}
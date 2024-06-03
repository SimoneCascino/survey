package it.simonecascino.survey.data.models

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import it.simonecascino.survey_domain.models.SurveyDomain
import it.simonecascino.utils.PresentationState
import org.junit.After
import org.junit.Before
import org.junit.Test

class QuestionsMapperTest {

    private val domain = arrayListOf<SurveyDomain>()

    @Before
    fun setup(){
        domain.add(SurveyDomain(id = 1, text = "What is your favourite colour?"))
    }

    @After
    fun teardown(){
        domain.clear()
    }

    @Test
    fun `mapper maps the response model to the domain model`(){

        val questions = surveyDomainToQuestions(domain)

        val expectedQuestion = listOf(
            Question(
                id = 1,
                text = "What is your favourite colour?",
                answer = "",
                submitStatus = PresentationState.Idle
            )
        )

        assertThat(questions).isEqualTo(expectedQuestion)

    }

}
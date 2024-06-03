package it.simonecascino.survey_data.model

import it.simonecascino.survey_data.models.SurveyResponse
import it.simonecascino.survey_data.models.surveyResponseToDomain
import org.junit.After
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import it.simonecascino.survey_domain.models.SurveyDomain

class SurveyReposeToDomainMapperTest {

    private val response = arrayListOf<SurveyResponse>()

    @Before
    fun setup(){
        response.add(SurveyResponse(id = 1, question = "What is your favourite colour?"))
    }

    @After
    fun teardown(){
        response.clear()
    }

    @Test
    fun `mapper maps the response model to the domain model`(){

        val domain = surveyResponseToDomain(response)

        val expectedDomain = listOf(SurveyDomain(id = 1, text = "What is your favourite colour?"))

        assertThat(domain).isEqualTo(expectedDomain)

    }

}
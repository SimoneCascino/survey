package it.simonecascino.survey_data.service

import it.simonecascino.survey_data.models.SubmitRequest
import it.simonecascino.survey_data.models.SurveyResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface SurveyApi {

    @GET("questions")
    suspend fun getSurvey(): List<SurveyResponse>

    @Headers("Content-Type: application/json")
    @POST("question/submit")
    suspend fun submitAnswer(
        @Body request: SubmitRequest
    )

}
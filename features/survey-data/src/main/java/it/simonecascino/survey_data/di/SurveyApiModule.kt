package it.simonecascino.survey_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.simonecascino.survey_data.service.SurveyApi
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object SurveyApiModule {

    @Provides
    fun provideSurveyApi(retrofit: Retrofit) = retrofit.create(SurveyApi::class.java)

}
package it.simonecascino.survey_data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.simonecascino.survey_data.repository.SurveyRepositoryImpl
import it.simonecascino.survey_domain.repository.SurveyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SurveyRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSurveyRepository(impl: SurveyRepositoryImpl): SurveyRepository

}
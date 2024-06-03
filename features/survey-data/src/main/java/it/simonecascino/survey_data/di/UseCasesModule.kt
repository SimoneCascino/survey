package it.simonecascino.survey_data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.simonecascino.survey_data.usecases.GetSurveyUseCaseImpl
import it.simonecascino.survey_data.usecases.SubmitAnswerUseCaseImpl
import it.simonecascino.survey_domain.usecases.GetSurveyUseCase
import it.simonecascino.survey_domain.usecases.SubmitAnswerUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {

    @Binds
    @Singleton
    abstract fun bindSurveyUseCase(useCaseImpl: GetSurveyUseCaseImpl): GetSurveyUseCase

    @Binds
    @Singleton
    abstract fun bindSubmitUseCase(useCaseImpl: SubmitAnswerUseCaseImpl): SubmitAnswerUseCase

}
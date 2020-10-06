package com.gk.app.android.testingviewmodels.ui.di

import com.gk.app.android.testingviewmodels.domain.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(ApplicationComponent::class)
object DomainModule {

    @Provides
    fun provideMainUseCase(uiNavigationGateway: UiNavigationGateway): MainUseCase {
        return MainUseCaseImpl.Factory.get(uiNavigationGateway)
    }

    @Provides
    fun provideDetailUseCase(uiNavigationGateway: UiNavigationGateway): DetailUseCase {
        return DetailUseCaseImpl.Factory.get(uiNavigationGateway)
    }
}
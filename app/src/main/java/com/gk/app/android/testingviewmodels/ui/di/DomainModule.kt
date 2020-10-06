package com.gk.app.android.testingviewmodels.ui.di

import com.gk.app.testingviewmodels.domain.detail.DetailUseCase
import com.gk.app.testingviewmodels.domain.main.MainUseCase
import com.gk.app.testingviewmodels.domain.navigation.UiNavigationGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object DomainModule {

    @Provides
    fun provideMainUseCase(uiNavigationGateway: UiNavigationGateway): MainUseCase {
        return MainUseCase.Factory.get(uiNavigationGateway)
    }

    @Provides
    fun provideDetailUseCase(uiNavigationGateway: UiNavigationGateway): DetailUseCase {
        return DetailUseCase.Factory.get(uiNavigationGateway)
    }
}
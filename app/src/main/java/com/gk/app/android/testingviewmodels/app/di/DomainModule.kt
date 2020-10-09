package com.gk.app.android.testingviewmodels.app.di

import com.gk.app.testingviewmodels.domain.detail.DetailUseCase
import com.gk.app.testingviewmodels.domain.home.HomeUseCase
import com.gk.app.testingviewmodels.domain.home.ItemsGateway
import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object DomainModule {

    @Provides
    fun provideHomeUseCase(
        screensGateway: ScreensGateway,
        itemsGateway: ItemsGateway
    ): HomeUseCase {
        return HomeUseCase.Factory.get(screensGateway, itemsGateway)
    }

    @Provides
    fun provideDetailUseCase(
        screensGateway: ScreensGateway,
        itemsGateway: ItemsGateway
    ): DetailUseCase {
        return DetailUseCase.Factory.get(screensGateway, itemsGateway)
    }
}
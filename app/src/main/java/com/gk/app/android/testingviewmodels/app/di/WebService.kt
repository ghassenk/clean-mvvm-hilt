package com.gk.app.android.testingviewmodels.app.di

import com.gk.app.android.webservice.home.ItemsGatewayImpl
import com.gk.app.testingviewmodels.domain.home.HomeUseCase
import com.gk.app.testingviewmodels.domain.home.ItemsGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object WebService {

    @Provides
    fun provideItemsGateway(): ItemsGateway {
        return ItemsGatewayImpl()
    }
}
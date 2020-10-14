package com.gk.app.android.testingviewmodels.app.di

import com.gk.app.android.webservice.WebService
import com.gk.app.testingviewmodels.domain.detail.DetailsGateway
import com.gk.app.testingviewmodels.domain.items.ItemsGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object WebServiceModule {

    @Provides
    fun provideItemsGateway(): ItemsGateway {
        return WebService.Factory.getItemsGateway()
    }

    @Provides
    fun provideDetailsGateway(): DetailsGateway {
        return WebService.Factory.getDetailsGateway()
    }

}
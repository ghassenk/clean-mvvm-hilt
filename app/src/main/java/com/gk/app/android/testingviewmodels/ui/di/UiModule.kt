package com.gk.app.android.testingviewmodels.ui.di

import com.gk.app.android.testingviewmodels.domain.UiNavigationGateway
import com.gk.app.android.testingviewmodels.ui.UiNavigationGatewayImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(ApplicationComponent::class)
object UiModule {

    private var uiNavigationGateway: UiNavigationGateway? = null

    @Provides
    fun provideUiNavigationGateway(): UiNavigationGateway {
        return uiNavigationGateway ?: UiNavigationGatewayImpl()
    }
}
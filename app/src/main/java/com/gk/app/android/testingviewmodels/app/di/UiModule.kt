package com.gk.app.android.testingviewmodels.app.di

import android.content.Context
import com.gk.app.testingviewmodels.domain.navigation.UiNavigationGateway
import com.gk.app.android.testingviewmodels.ui.navigation.UiNavigationGatewayImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object UiModule {

    private var uiNavigationGateway: UiNavigationGateway? = null

    @Provides
    fun provideUiNavigationGateway(@ApplicationContext appContext: Context): UiNavigationGateway {
        return uiNavigationGateway ?: UiNavigationGatewayImpl(appContext)
    }
}
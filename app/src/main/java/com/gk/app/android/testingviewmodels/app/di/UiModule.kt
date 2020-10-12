package com.gk.app.android.testingviewmodels.app.di

import android.content.Context
import com.gk.app.android.testingviewmodels.ui.navigation.gateway.ScreensGatewayNavGraphImpl
import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object UiModule {

    private var screensGateway: ScreensGateway? = null

    @Provides
    fun provideUiNavigationGateway(@ApplicationContext appContext: Context): ScreensGateway {
        // Use based navigation on NavGraph (single/double pane)
        return ScreensGatewayNavGraphImpl(appContext)
    }
}
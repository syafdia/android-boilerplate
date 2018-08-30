package com.github.syafdia.androidboilerplate.feature.information

import com.github.syafdia.androidboilerplate.feature.information.presentation.InformationViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class InformationModule {

    @Provides
    fun provideInformationViewModelFactory(): InformationViewModelFactory {
        return InformationViewModelFactory()
    }
}
package com.github.syafdia.androidboilerplate.feature.information

import dagger.Module
import dagger.Provides

@Module
class InformationModule {

    @Provides
    fun provideInformationViewModelFactory(): InformationViewModelFactory {
        return InformationViewModelFactory()
    }
}
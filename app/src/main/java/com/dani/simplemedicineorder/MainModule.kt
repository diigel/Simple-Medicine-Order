package com.dani.simplemedicineorder

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object MainModule {

    @Provides
    fun provideService(): Service {
        return Service.create()
    }

    @Provides
    fun provideRepository(service: Service) : MedicineOrderRepository {
        return MedicineOrderRepositoryImpl(service)
    }
}
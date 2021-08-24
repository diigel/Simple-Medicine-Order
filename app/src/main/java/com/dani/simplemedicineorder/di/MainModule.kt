package com.dani.simplemedicineorder.di

import com.dani.simplemedicineorder.data.Service
import com.dani.simplemedicineorder.repository.MedicineOrderRepository
import com.dani.simplemedicineorder.repository.MedicineOrderRepositoryImpl
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
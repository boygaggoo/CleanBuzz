package co.zsmb.example.cleanbuzz.di.application

import co.zsmb.example.cleanbuzz._2_domain.BuzzRepository
import co.zsmb.example.cleanbuzz._3_data.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class BuzzDataModule {

    @Provides @Singleton
    fun provideBuzzRepository(memoryDataSource: BuzzDataSourceMemory,
                              networkDataSource: BuzzDataSourceNetwork): BuzzRepository
            = BuzzRepositoryImpl(memoryDataSource, networkDataSource)

    @Provides @Singleton
    fun provideBuzzDataSourceNetwork(retrofit: Retrofit) = BuzzDataSourceNetwork(retrofit)

    @Provides @Singleton
    fun provideBuzzDataSourceMemory() = BuzzDataSourceMemory()

}

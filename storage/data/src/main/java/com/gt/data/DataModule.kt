package com.gt.data

import android.content.Context
import androidx.room.Room
import com.gt.data.database.AppDatabase
import com.gt.data.database.ProductDao
import com.gt.data.mappers.ProductMapper
import com.gt.data.remote.RetrofitService
import com.gt.domain.product.ProductRepository
import com.gt.domain.usecases.FetchRemoteDataUseCase
import com.gt.domain.usecases.FetchRemoteItemDetailsUseCase
import com.gt.domain.usecases.SaveDataToLocalDatabaseUseCase
import com.gt.domain.usecases.SearchInDatabaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private const val BASE_URL = "https://api.restful-api.dev/"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(
            ctx,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration(false)
            .build()

    @Provides
    @Singleton
    fun provideProductDao(db: AppDatabase): ProductDao =
        db.productDao()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): RetrofitService =
        retrofit.create(RetrofitService::class.java)

    @Provides
    @Singleton
    fun provideProductRepository(
        service: RetrofitService,
        dao: ProductDao,
        mapper: ProductMapper
    ): ProductRepository =
        ProductRepositoryImpl(dao, service, mapper)

    @Provides
    @Singleton
    fun provideProductMapper(): ProductMapper =
        ProductMapper()

    @Provides
    @Singleton
    fun provideFetchRemoteDataUseCase(repo: ProductRepository) = FetchRemoteDataUseCase(repo)

    @Provides
    @Singleton
    fun provideFetchRemoteItemDetailsUseCase(repo: ProductRepository) =
        FetchRemoteItemDetailsUseCase(repo)


    @Provides
    @Singleton
    fun provideSaveDataToLocalDatabaseUseCase(repo: ProductRepository) =
        SaveDataToLocalDatabaseUseCase(repo)

    @Provides
    @Singleton
    fun provideSearchInDatabaseUseCase(repo: ProductRepository) = SearchInDatabaseUseCase(repo)
}

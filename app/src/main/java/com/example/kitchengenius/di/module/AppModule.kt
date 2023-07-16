package com.example.kitchengenius.di.module

import com.example.kitchengenius.common.BASE_URL
import com.example.kitchengenius.data.remote.api.RecipeApi
import com.example.kitchengenius.data.repository.AuthRepository
import com.example.kitchengenius.data.repository.AuthRepositoryImpl
import com.example.kitchengenius.data.repository.RecipeDataSource
import com.example.kitchengenius.domain.repository.RecipeRepository
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()
    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth):AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideRecipeApi(): RecipeApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        ).addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(RecipeApi::class.java)

    @Provides
    @Singleton
    fun provideRecipeRepository(recipeApi: RecipeApi): RecipeRepository =
        RecipeDataSource(recipeApi)
}
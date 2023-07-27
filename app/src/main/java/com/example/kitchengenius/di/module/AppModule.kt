package com.example.kitchengenius.di.module

import com.example.kitchengenius.common.BASE_URL
import com.example.kitchengenius.data.remote.api.RecipeApi
import com.example.kitchengenius.data.remote.api.UserApi
import com.example.kitchengenius.data.repository.AuthRepository
import com.example.kitchengenius.data.repository.AuthRepositoryImpl
import com.example.kitchengenius.data.repository.RecipeDataSource
import com.example.kitchengenius.data.repository.UserDataSource
import com.example.kitchengenius.domain.repository.RecipeRepository
import com.example.kitchengenius.domain.repository.UserRepository
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
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth, userDataSource : UserDataSource):AuthRepository {
        return AuthRepositoryImpl(firebaseAuth, userDataSource)
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
    fun provideUserApi(): UserApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        ).addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(recipeApi: RecipeApi,userApi: UserApi): UserRepository =
        UserDataSource(recipeApi,userApi)
    @Provides
    @Singleton
    fun provideRecipeRepository(recipeApi: RecipeApi): RecipeRepository =
        RecipeDataSource(recipeApi)

    @Provides
    @Singleton
    fun provideUserDataSource(recipeApi: RecipeApi,userApi: UserApi): UserDataSource =
        UserDataSource(recipeApi,userApi)
}
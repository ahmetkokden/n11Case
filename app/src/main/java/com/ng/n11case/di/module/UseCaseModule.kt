package com.ng.n11case.di.module

import com.ng.n11case.domain.usecase.UserDetailUseCase
import com.ng.n11case.domain.usecase.UserDetailUseCaseImpl
import com.ng.n11case.domain.usecase.UserSearchUseCase
import com.ng.n11case.domain.usecase.UserSearchUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideUserSearchUseCase(userSearchUseCaseImpl: UserSearchUseCaseImpl): UserSearchUseCase

    @Binds
    abstract fun provideUserDetailUseCase(userDetailUseCaseImpl: UserDetailUseCaseImpl): UserDetailUseCase

}